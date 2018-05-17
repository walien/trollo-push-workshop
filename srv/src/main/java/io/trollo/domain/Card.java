package io.trollo.domain;

import org.jongo.marshall.jackson.oid.MongoId;

public class Card {

    @MongoId
    private String id;

    private String title;

    private String body;

    private State state;

    public String getId() {
        return id;
    }

    public Card setId(String id) {
        this.id = id;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public Card setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getBody() {
        return body;
    }

    public Card setBody(String body) {
        this.body = body;
        return this;
    }

    public State getState() {
        return state;
    }

    public Card setState(State state) {
        this.state = state;
        return this;
    }
}
