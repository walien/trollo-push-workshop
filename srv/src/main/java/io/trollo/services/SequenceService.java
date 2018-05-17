package io.trollo.services;

import io.trollo.domain.Sequence;
import restx.factory.Component;
import restx.jongo.JongoCollection;

import javax.inject.Named;

@Component
public class SequenceService {

    private final JongoCollection sequences;

    public SequenceService(@Named("sequences") JongoCollection sequences) {
        this.sequences = sequences;
    }

    public int getNext(String name) {
        Sequence sequence = sequences.get()
                .findAndModify("{ name: # }", name)
                .with("{ $inc: { value: 1 } }")
                .upsert()
                .returnNew()
                .as(Sequence.class);
        return sequence.getValue();
    }
}
