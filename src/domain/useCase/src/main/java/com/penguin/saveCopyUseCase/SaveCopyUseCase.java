package com.penguin.saveCopyUseCase;

import com.penguin.gateways.DomainEventRepository;
import com.penguin.generic.CommandUseCase;
import com.penguin.model.bookStoreQuotes.BookStoreQuotes;
import com.penguin.model.bookStoreQuotes.commands.SaveBookCommand;
import com.penguin.model.bookStoreQuotes.values.copy.*;
import com.penguin.model.generic.DomainEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class SaveCopyUseCase extends CommandUseCase<SaveBookCommand> {

    private final DomainEventRepository domainEventRepository;

    @Autowired
    public SaveCopyUseCase(DomainEventRepository domainEventRepository) {
        this.domainEventRepository = domainEventRepository;
    }

    @Override
    public Flux<DomainEvent> apply(Mono<SaveBookCommand> saveBookCommandMono) {
        return saveBookCommandMono.flatMapIterable(command -> {
            BookStoreQuotes bookStoreQuotes = new BookStoreQuotes(
                    new Title(command.getTitle()),
                    new Author(command.getAuthor()),
                    new Stock(command.getStock()),
                    new PublicationYear(command.getPublicationYear()),
                    new Price(command.getPrice()),
                    new Type(command.getType())
            );
            return bookStoreQuotes.getUncommittedChanges();
        }).map(event -> event).flatMap(domainEventRepository::saveEvent);
    }
}

