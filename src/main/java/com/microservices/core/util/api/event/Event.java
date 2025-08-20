package com.microservices.core.util.api.event;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Event<K, T> {

    public enum Type {CREATE, DELETE};

    private Event.Type eventType;
    private K key;
    private T data;
    private ZonedDateTime eventCreatedAt;
}
