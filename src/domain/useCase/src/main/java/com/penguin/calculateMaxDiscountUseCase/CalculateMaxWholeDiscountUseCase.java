package com.penguin.calculateMaxDiscountUseCase;

import com.penguin.gateways.DomainEventRepository;
import com.penguin.generic.CommandUseCase;
import com.penguin.model.generic.DomainEvent;
import com.penguin.model.provider.Provider;
import com.penguin.model.provider.commands.CalculateMaxWholeDiscountCommand;
import com.penguin.model.provider.dto.LiteraryWork;
import com.penguin.model.provider.events.BookSaved;
import com.penguin.model.provider.factory.CopyFactory;
import com.penguin.model.provider.mapper.Mapper;
import com.penguin.model.provider.values.copy.*;
import com.penguin.model.provider.values.identities.ProviderId;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

public class CalculateMaxWholeDiscountUseCase extends CommandUseCase<CalculateMaxWholeDiscountCommand> {

    private final DomainEventRepository repository;
    private final CopyFactory copyFactory;
    private final Mapper mapper;

    private String uuid;

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getUuid() {
        return uuid;
    }
    public CalculateMaxWholeDiscountUseCase(DomainEventRepository repository, CopyFactory copyFactory, Mapper mapper) {
        this.repository = repository;
        this.copyFactory = copyFactory;
        this.mapper = mapper;
    }

    @Override
    public Flux<DomainEvent> apply(Mono<CalculateMaxWholeDiscountCommand> calculateMaxWholeDiscountCommandMono) {
        return calculateMaxWholeDiscountCommandMono
                .flatMapMany(command -> repository.findById(command.getBookStoreQuoteId())
                        .filter(domain -> domain instanceof BookSaved)
                        .collectMap((domainEvent) ->  domainEvent.uuid.toString(),domainEvent -> domainEvent)
                        .map(domainMap -> {
                            List<DomainEvent> domains = new ArrayList<>();
                            command.getBooks().forEach(bookItem -> {
                                if(domainMap.containsKey(bookItem.getBookId())) {
                                    domains.add(domainMap.get(bookItem.getBookId()));
                                } else {
                                    throw new IllegalArgumentException("Book not found with That ID: " + bookItem.getBookId());
                                }
                            });
                            return domains;
                        }).flatMapIterable(domainEvents -> {

                            ProviderId providerId = ProviderId.of(command.getBookStoreQuoteId());

                            if(domainEvents.isEmpty()) {
                                throw new IllegalArgumentException("List is empty");
                            }

                            Provider provider = Provider.from(providerId, domainEvents);
                             List<LiteraryWork> copies =  domainEvents
                                .stream()
                                .map(domainEvent -> {
                                    setUuid(domainEvent.uuid.toString());
                                    return (BookSaved) domainEvent;

                                }).map(bookSaved -> copyFactory.createCopy(
                                        new BookType(bookSaved.getType()),
                                        new Title(bookSaved.getTitle()),
                                        new Author(bookSaved.getAuthor()),
                                        new AreaOfKnowledge(bookSaved.getAreaOfKnowledge()),
                                        new NumOfPages(bookSaved.getNumOfPages()),
                                        new CopiesOfTheBook(bookSaved.getCopiesOfTheBook()),
                                        new Price(bookSaved.getPrice())
                                )
                                ).map(
                                        copy ->  mapper.toLiteraryWork(copy, this.getUuid() ,copy.getType())
                                     ).toList();

                             provider.calculateMaxWholeSale(copies,command.getBudget(),command.getRegisteredAt());


                             return provider.getUncommittedChanges();
                        })
                ).flatMap(repository::saveEvent);
    }
}
