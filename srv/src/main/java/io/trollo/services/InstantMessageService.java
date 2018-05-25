package io.trollo.services;

import io.trollo.broker.Exchange;
import io.trollo.broker.MQClient;
import io.trollo.domain.InstantMessage;
import restx.factory.Component;
import restx.jongo.JongoCollection;

import javax.inject.Named;
import java.time.Clock;
import java.time.Instant;

@Component
public class InstantMessageService {

    private final JongoCollection messages;
    private final Clock clock;
    private final MQClient mqClient;

    public InstantMessageService(@Named("messages") JongoCollection messages, Clock clock, MQClient mqClient) {
        this.clock = clock;
        this.messages = messages;
        this.mqClient = mqClient;
    }

    public Iterable<InstantMessage> findAll() {
        return messages.get().find().sort("{ timestamp: -1 }").limit(5).as(InstantMessage.class);
    }

    public InstantMessage createMessage(InstantMessage message) {
        message.setTimestamp(Instant.now(clock));
        messages.get().insert(message);
        mqClient.publish(Exchange.byName("trollo.messages", "topic"), "*", message);
        return message;
    }
}
