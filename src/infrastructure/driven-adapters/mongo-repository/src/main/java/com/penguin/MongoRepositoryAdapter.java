package com.penguin;

import com.penguin.data.Event;
import com.penguin.gateways.DomainEventRepository;
import com.penguin.model.generic.DomainEvent;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Comparator;
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
        eventStore.setAggregateRootId(event.aggregateRootId());
        eventStore.setEventBody(eventStore.mapperEventBody(event));
        eventStore.setOccurredOn(new Date());
        eventStore.setTypeName(event.getClass().getTypeName());
        return template.save(eventStore)
                .map(eventStored -> event);
    }

    @Override
    public Flux<DomainEvent> findById(String aggregateId) {
        return template.find(new Query(Criteria.where("aggregateRootId").is(aggregateId)), Event.class)
                .sort(Comparator.comparing(Event::getOccurredOn))
                .map(storedEvent -> storedEvent.mapperEventBody(storedEvent));
    }
}
