package io.trollo.services;

import io.trollo.broker.Exchange;
import io.trollo.broker.MQClient;
import io.trollo.domain.InstantMessage;
import restx.factory.Component;
import restx.jongo.JongoCollection;

import javax.inject.Named;

@Component
public class InstantMessageService {

    private final JongoCollection messages;
    private final MQClient mqClient;

    public InstantMessageService(@Named("messages") JongoCollection messages, MQClient mqClient) {
        this.messages = messages;
        this.mqClient = mqClient;
    }

    public Iterable<InstantMessage> findAll() {
        return messages.get().find().as(InstantMessage.class);
    }

    public InstantMessage createMessage(InstantMessage message) {
        messages.get().insert(message);
        mqClient.publish(Exchange.byName("trollo.messages", "topic"), "*", message);
        return message;
    }
}
