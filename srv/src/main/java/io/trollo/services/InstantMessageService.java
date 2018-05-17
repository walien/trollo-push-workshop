package io.trollo.services;

import io.trollo.domain.InstantMessage;
import restx.factory.Component;
import restx.jongo.JongoCollection;

import javax.inject.Named;

@Component
public class InstantMessageService {

    private JongoCollection messages;

    public InstantMessageService(@Named("messages") JongoCollection messages) {
        this.messages = messages;
    }

    public Iterable<InstantMessage> findAll() {
        return messages.get().find().as(InstantMessage.class);
    }

    public InstantMessage createMessage(InstantMessage message) {
        messages.get().insert(message);
        return message;
    }
}
