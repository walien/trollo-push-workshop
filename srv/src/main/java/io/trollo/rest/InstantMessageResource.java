package io.trollo.rest;

import io.trollo.domain.InstantMessage;
import io.trollo.services.InstantMessageService;
import restx.annotations.GET;
import restx.annotations.POST;
import restx.annotations.RestxResource;
import restx.factory.Component;
import restx.security.PermitAll;

import java.time.Clock;
import java.time.Instant;

@Component
@RestxResource
@PermitAll
public class InstantMessageResource {

    private Clock clock;
    private InstantMessageService service;

    public InstantMessageResource(Clock clock, InstantMessageService service) {
        this.clock = clock;
        this.service = service;
    }

    @GET("/message")
    public Iterable<InstantMessage> findAll() {
        return service.findAll();
    }

    @POST("/message")
    public InstantMessage findAll(InstantMessage message) {
        message.setTimestamp(Instant.now(clock));
        return service.createMessage(message);
    }
}
