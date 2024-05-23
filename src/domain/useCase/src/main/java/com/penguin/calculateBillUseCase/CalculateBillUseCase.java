package com.penguin.calculateBillUseCase;

import com.penguin.gateways.DomainEventRepository;
import com.penguin.generic.CommandUseCase;
import com.penguin.model.provider.Provider;
import com.penguin.model.provider.commands.CalculateBillCommand;
import com.penguin.model.provider.dto.LiteraryWork;
import com.penguin.model.provider.entity.Copy;
import com.penguin.model.provider.events.BookSaved;
import com.penguin.model.provider.factory.CopyFactory;
import com.penguin.model.provider.mapper.Mapper;
import com.penguin.model.provider.values.copy.*;
import com.penguin.model.provider.values.identities.ProviderId;
import com.penguin.model.generic.DomainEvent;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class CalculateBillUseCase extends CommandUseCase<CalculateBillCommand> {

    private final DomainEventRepository repository;
    private final CopyFactory copyFactory;
    private final Mapper mapper;

    public CalculateBillUseCase(DomainEventRepository repository, CopyFactory copyFactory, Mapper mapper) {
        this.repository = repository;
        this.copyFactory = copyFactory;
        this.mapper = mapper;
    }

    @Override
    public Flux<DomainEvent> apply(Mono<CalculateBillCommand> calculateBillCommandMono) {
        return calculateBillCommandMono
                .flatMapMany(command -> repository.findById(command.getBookStoreQuoteId())
                .filter(domain -> domain instanceof BookSaved)
                .collectMap((domainEvent) ->  domainEvent.uuid.toString(),domainEvent -> domainEvent)
                .map(domainMap -> {
                    Map<DomainEvent, Integer> domains = new HashMap<>();
                    command.getItems().forEach(bookItem -> {
                        if(domainMap.containsKey(bookItem.getBookId())) {
                            domains.put(domainMap.get(bookItem.getBookId()),bookItem.getQuantity());
                        } else {
                            throw new IllegalArgumentException("Book not found with That ID: " + bookItem.getBookId());
                        }
                    });
                    return domains;
                })
                .flatMapIterable(events -> {

                    ProviderId providerId = ProviderId.of(command.getBookStoreQuoteId());
                    if(events.isEmpty()) {
                        throw new IllegalArgumentException("List is empty");
                    }

                    Provider provider = Provider.from(providerId, new ArrayList<>(events.keySet()));
                    Map<LiteraryWork, Integer> copyStock = new HashMap<>();


                    events.forEach((domainEvent, quantity) -> {
                        String uuid = domainEvent.uuid.toString();
                        BookSaved bookSaved = (BookSaved) domainEvent;


                        Copy copy = copyFactory.createCopy(
                                new BookType(bookSaved.getType()),
                                new Title(bookSaved.getTitle()),
                                new Author(bookSaved.getAuthor()),
                                new AreaOfKnowledge(bookSaved.getAreaOfKnowledge()),
                                new NumOfPages(bookSaved.getNumOfPages()),
                                new CopiesOfTheBook(bookSaved.getCopiesOfTheBook()),
                                new Price(bookSaved.getPrice())
                        );


                        LiteraryWork literaryWork = mapper.toLiteraryWork(copy,uuid,copy.getType());



                        copyStock.put(literaryWork, quantity);
                    });


                    /*List<Copy> copies =  events.values()
                    .stream()
                    .map(
                        domainEvent -> (BookSaved) domainEvent
                    ).map(
                        bookSaved -> copyFactory.createCopy(
                                bookSaved.getType(),
                                new Title(bookSaved.getTitle()),
                                new Author(bookSaved.getAuthor()),
                                new Stock(bookSaved.getStock()),
                                new PublicationYear(bookSaved.getPublicationYear()),
                                new Price(bookSaved.getPrice())
                        )
                    ).toList();*/

                    provider.addCopiesStock(copyStock, command.getRegisteredAt());
                    /*events.forEach( event -> {
                        provider.addCopy(
                                new Title(((BookSaved) event).getTitle()),
                                new Author(((BookSaved) event).getAuthor()),
                                new Stock(((BookSaved) event).getStock()),
                                new PublicationYear(((BookSaved) event).getPublicationYear()),
                                new Price(((BookSaved) event).getPrice()),
                                new Type(((BookSaved) event).getType())
                        );
                    });*/



                    return provider.getUncommittedChanges();
                })
                .flatMap(repository::saveEvent)


        );
    }
}
