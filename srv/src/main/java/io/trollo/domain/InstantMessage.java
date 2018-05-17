package io.trollo.domain;

import java.time.Instant;

public class InstantMessage implements Payload {

    private Instant timestamp;

    private String content;

    public Instant getTimestamp() {
        return timestamp;
    }

    public InstantMessage setTimestamp(Instant timestamp) {
        this.timestamp = timestamp;
        return this;
    }

    public String getContent() {
        return content;
    }

    public InstantMessage setContent(String content) {
        this.content = content;
        return this;
    }
}
