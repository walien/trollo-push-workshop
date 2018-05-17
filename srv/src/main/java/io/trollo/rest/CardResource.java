package io.trollo.rest;

import io.trollo.domain.Card;
import io.trollo.domain.State;
import io.trollo.services.CardService;
import restx.annotations.*;
import restx.factory.Component;
import restx.security.PermitAll;

import java.util.Optional;

@Component
@RestxResource
@PermitAll
public class CardResource {

    private CardService service;

    public CardResource(CardService service) {
        this.service = service;
    }

    @GET("/card")
    public Iterable<Card> findAll() {
        return service.findAll();
    }

    @POST("/card")
    public Card createCard(Card card) {
        return service.createCard(card);
    }

    @PUT("/card/:id")
    public Optional<Card> updateCard(@Param(kind = Param.Kind.PATH) String id,
                                     @Param(kind = Param.Kind.BODY) Card card) {
        return service.updateCard(id, card);
    }

    @PUT("/card/:id/state/:state")
    public Optional<Card> updateCardState(@Param(kind = Param.Kind.PATH) String id,
                                          @Param(kind = Param.Kind.PATH) State state) {
        return service.updateCardState(id, state);
    }
}
