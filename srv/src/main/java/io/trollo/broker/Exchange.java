package io.trollo.broker;

public class Exchange {

    private final String name;

    private final String type;

    private Exchange(String name, String type) {
        this.name = name;
        this.type = type;
    }

    public static Exchange byName(String name, String type) {
        return new Exchange(name, type);
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }
}
