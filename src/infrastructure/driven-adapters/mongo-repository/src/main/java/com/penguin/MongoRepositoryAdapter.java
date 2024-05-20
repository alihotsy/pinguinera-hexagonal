package com.penguin;

import com.penguin.data.Event;
import com.penguin.gateways.DomainEventRepository;
import com.penguin.model.generic.DomainEvent;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Comparator;
import java.util.Date;

@Repository
public class MongoRepositoryAdapter implements DomainEventRepository {

    private final ReactiveMongoTemplate template;
    private final JSONMapper eventSerializer;

    public MongoRepositoryAdapter(ReactiveMongoTemplate template, JSONMapper eventSerializer) {
        this.template = template;
        this.eventSerializer = eventSerializer;
    }

    @Override
    public Mono<DomainEvent> saveEvent(DomainEvent event) {
        Event eventStore = new Event();
        eventStore.setAggregateRootId(event.aggregateRootId());
        eventStore.setEventBody(Event.wrapEvent(event,eventSerializer));
        eventStore.setOccurredOn(new Date());
        eventStore.setTypeName(event.getClass().getTypeName());
        return template.save(eventStore)
                .map(eventStored -> eventStore.deserializeEvent(eventSerializer));
    }

    @Override
    public Flux<DomainEvent> findById(String aggregateId) {

        System.out.println("AGREGATE "+aggregateId);
        return template.find(new Query(Criteria.where("aggregateRootId").is(aggregateId)), Event.class)
                .sort(Comparator.comparing(Event::getOccurredOn))
                .map(storedEvent -> {
                    System.out.println("FOUND: "+storedEvent.getEventBody());
                    return storedEvent.deserializeEvent(eventSerializer);
                });
    }

    @Override
    public Mono<DomainEvent> findByEventId(String eventId) {
        return template.findById(eventId, Event.class)
                .map(storedEvent -> {
                    System.out.println(storedEvent.getEventBody());
                    return storedEvent.deserializeEvent(eventSerializer);
                });
    }
}
