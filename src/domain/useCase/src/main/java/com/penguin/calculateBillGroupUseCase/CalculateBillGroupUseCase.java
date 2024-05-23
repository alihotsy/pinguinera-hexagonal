package com.penguin.calculateBillGroupUseCase;

import com.penguin.gateways.DomainEventRepository;
import com.penguin.generic.CommandUseCase;
import com.penguin.model.generic.DomainEvent;
import com.penguin.model.provider.Provider;
import com.penguin.model.provider.commands.CalculateBillGroupCommand;
import com.penguin.model.provider.dto.Bill;
import com.penguin.model.provider.dto.LiteraryWork;
import com.penguin.model.provider.entity.Copy;
import com.penguin.model.provider.events.BookSaved;
import com.penguin.model.provider.events.CalculatedBill;
import com.penguin.model.provider.factory.CopyFactory;
import com.penguin.model.provider.mapper.Mapper;
import com.penguin.model.provider.values.copy.*;
import com.penguin.model.provider.values.identities.ProviderId;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CalculateBillGroupUseCase extends CommandUseCase<CalculateBillGroupCommand> {
    private final DomainEventRepository repository;
    private final CopyFactory copyFactory;
    private final Mapper mapper;

    public CalculateBillGroupUseCase(DomainEventRepository repository, CopyFactory copyFactory, Mapper mapper) {
        this.repository = repository;
        this.copyFactory = copyFactory;
        this.mapper = mapper;
    }

    @Override
    public Flux<DomainEvent> apply(Mono<CalculateBillGroupCommand> calculateBillGroupCommandMono) {
        return calculateBillGroupCommandMono.flatMapMany(command -> {
            return Flux.fromIterable(command.getItems())
                    .flatMap(list -> repository.findById(command.getBookStoreQuoteId())
                            .filter(domain -> domain instanceof BookSaved)
                            .collectMap((domainEvent) ->  domainEvent.uuid.toString(),domainEvent -> domainEvent)
                            .map(domainMap -> {
                                Map<DomainEvent, Integer> domains = new HashMap<>();
                                list.forEach(bookItem -> {
                                    if(domainMap.containsKey(bookItem.getBookId())) {
                                        domains.put(domainMap.get(bookItem.getBookId()),bookItem.getQuantity());
                                    } else {
                                        throw new IllegalArgumentException("Book not found with That ID: " + bookItem.getBookId());
                                    }
                                });
                                return domains;
                            }).flatMapIterable(events -> {

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


                                        provider.addCopiesStock(copyStock, command.getRegisteredAt());


                                        return provider.getUncommittedChanges();
                                    })

                    ).collectList()
                    .flatMapIterable(events -> {
                        ProviderId providerId = ProviderId.of(command.getBookStoreQuoteId());
                        Provider provider = Provider.from(providerId, events);

                        List<Bill> bills = events
                                .stream()
                                .map(domain -> (CalculatedBill) domain)
                                .map(mapper::toBill)
                                .toList();

                        provider.calculateBillGroup(bills);

                        return provider.getUncommittedChanges();

                    }).flatMap(repository::saveEvent);


        });
    }
}
