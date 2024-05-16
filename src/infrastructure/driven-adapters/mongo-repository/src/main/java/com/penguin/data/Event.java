package com.penguin.data;


import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Setter
@Getter
@Document
public class Event {

    private String aggregateRootId;
    private String eventBody;
    private Date occurredOn;
    private String typeName;

//    public static String wrapEvent(DomainEvent domainEvent, JSONMapper eventSerializer){
//        return eventSerializer.writeToJson(domainEvent);
//    }

}
