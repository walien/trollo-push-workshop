package io.trollo.services;

import io.trollo.domain.Card;
import io.trollo.domain.State;
import restx.factory.Component;
import restx.jongo.JongoCollection;

import javax.inject.Named;
import java.util.Optional;

@Component
public class CardService {

    private final JongoCollection cards;
    private final SequenceService sequencesService;

    public CardService(@Named("cards") JongoCollection cards, SequenceService sequencesService) {
        this.cards = cards;
        this.sequencesService = sequencesService;
    }

    public Iterable<Card> findAll() {
        return cards.get().find().as(Card.class);
    }

    public Card createCard(Card card) {
        card.setState(State.TODO);
        card.setId(String.valueOf(sequencesService.getNext("cards")));
        cards.get().insert(card);
        return card;
    }

    public Optional<Card> updateCard(String id, Card card) {
        Card dbCard = cards.get()
                .findAndModify("{ _id: # }", id)
                .with("{ $set: { title: #, body: # } }", card.getTitle(), card.getBody())
                .returnNew()
                .as(Card.class);
        return Optional.ofNullable(dbCard);
    }

    public Optional<Card> updateCardState(String id, State state) {
        Card dbCard = cards.get()
                .findAndModify("{ _id: # }", id)
                .with("{ $set: { state: # } }", state.name())
                .returnNew()
                .as(Card.class);
        return Optional.ofNullable(dbCard);
    }
}
