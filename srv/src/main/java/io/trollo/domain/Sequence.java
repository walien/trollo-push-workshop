package io.trollo.domain;

public class Sequence {

    private String name;

    private int value;

    public String getName() {
        return name;
    }

    public Sequence setName(String name) {
        this.name = name;
        return this;
    }

    public int getValue() {
        return value;
    }

    public Sequence setValue(int value) {
        this.value = value;
        return this;
    }
}
