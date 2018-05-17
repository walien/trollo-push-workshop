package io.trollo.broker;

public class Queue {

    private final String name;

    private Queue(String name) {
        this.name = name;
    }

    public static Queue byName(String name) {
        return new Queue(name);
    }

    public String getName() {
        return name;
    }
}
