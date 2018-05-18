package io.trollo.rest;

import io.trollo.domain.InstantMessage;
import io.trollo.services.InstantMessageService;
import restx.annotations.GET;
import restx.annotations.POST;
import restx.annotations.RestxResource;
import restx.factory.Component;
import restx.security.PermitAll;

@Component
@RestxResource
@PermitAll
public class InstantMessageResource {

    private InstantMessageService service;

    public InstantMessageResource(InstantMessageService service) {
        this.service = service;
    }

    @GET("/message")
    public Iterable<InstantMessage> findAll() {
        return service.findAll();
    }

    @POST("/message")
    public InstantMessage createMessage(InstantMessage message) {
        return service.createMessage(message);
    }
}
