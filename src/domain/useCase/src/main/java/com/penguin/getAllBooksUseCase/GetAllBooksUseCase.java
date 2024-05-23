package com.penguin.getAllBooksUseCase;

import com.penguin.gateways.DomainEventRepository;
import com.penguin.generic.CommandUseCase;
import com.penguin.model.generic.DomainEvent;
import com.penguin.model.provider.Provider;
import com.penguin.model.provider.commands.GetAllBooksCommand;
import com.penguin.model.provider.events.BookSaved;
import com.penguin.model.provider.values.identities.ProviderId;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class GetAllBooksUseCase extends CommandUseCase<GetAllBooksCommand> {

    private final DomainEventRepository repository;

    public GetAllBooksUseCase(DomainEventRepository repository) {
        this.repository = repository;
    }

    @Override
    public Flux<DomainEvent> apply(Mono<GetAllBooksCommand> getAllBooksCommandMono) {
        return getAllBooksCommandMono.flatMapMany(commandId -> repository.findById(commandId.getBookStoreQuoteId())
                .collectList()
                .flatMapIterable(events -> {
                    if(events.isEmpty()) {
                        throw new IllegalArgumentException("There are not books!");
                    }
                    return events;
                })
                .filter(domain -> domain instanceof BookSaved)
                .collectList()
                .flatMapIterable(events -> {
                    /*ProviderId providerId = ProviderId.of(commandId.getBookStoreQuoteId());
                    Provider provider = Provider.from(providerId,events);*/
                    return events;
                })
        );
    }
}
