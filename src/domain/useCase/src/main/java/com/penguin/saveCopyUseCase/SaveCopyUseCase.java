package com.penguin.saveCopyUseCase;

import com.penguin.gateways.DomainEventRepository;
import com.penguin.generic.CommandUseCase;
import com.penguin.model.provider.Provider;
import com.penguin.model.provider.commands.SaveBookCommand;
import com.penguin.model.provider.factory.CopyFactory;
import com.penguin.model.provider.values.copy.*;
import com.penguin.model.provider.values.identities.ProviderId;
import com.penguin.model.generic.DomainEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class SaveCopyUseCase extends CommandUseCase<SaveBookCommand> {

    private final DomainEventRepository repository;


    @Autowired
    public SaveCopyUseCase(DomainEventRepository domainEventRepository, CopyFactory copyFactory) {
        this.repository = domainEventRepository;
    }

    @Override
    public Flux<DomainEvent> apply(Mono<SaveBookCommand> commandMono) {

        return commandMono.flatMapMany(command -> repository.findById(command.getBookStoreQuoteId())
                .collectList()
                .flatMapIterable(events -> {
                    System.out.println("Eventos: "+events);
                    ProviderId providerId = ProviderId.of(command.getBookStoreQuoteId());
                    Provider provider;
                    if (events.isEmpty()) {
                        provider = new Provider(
                                new Title(command.getTitle()),
                                new Author(command.getAuthor()),
                                new Stock(command.getStock()),
                                new PublicationYear(command.getPublicationYear()),
                                new Price(command.getPrice()),
                                new Type(command.getType())
                        );
                    } else {
                        provider = Provider.from(providerId, events);
                        provider.addCopy(
                                new Title(command.getTitle()),
                                new Author(command.getAuthor()),
                                new Stock(command.getStock()),
                                new PublicationYear(command.getPublicationYear()),
                                new Price(command.getPrice()),
                                new Type(command.getType())
                        );
                    }
                    return provider.getUncommittedChanges();

                })
                .flatMap(repository::saveEvent)

        );
    }
}
