package com.penguin;

//import com.penguin.config.MongoRepository;
import com.penguin.data.Event;
import com.penguin.gateways.DomainEventRepository;
import com.penguin.model.generic.DomainEvent;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.Date;

@Component
public class MongoRepositoryAdapter implements DomainEventRepository {

    private final ReactiveMongoTemplate template;

    public MongoRepositoryAdapter(ReactiveMongoTemplate template) {
        this.template = template;
    }

    @Override
    public Mono<DomainEvent> saveEvent(DomainEvent event) {
        Event eventStore = new Event();
        System.out.println("Event: " + event.aggregateRootId());
        eventStore.setAggregateRootId(event.aggregateRootId());
        eventStore.setEventBody(eventStore.mapperEventBody(event));
        eventStore.setOccurredOn(new Date());
        eventStore.setTypeName(event.getClass().getTypeName());
        System.out.println(eventStore.getEventBody());
        return template.save(eventStore)
                .map(eventStored -> event);
    }
}
