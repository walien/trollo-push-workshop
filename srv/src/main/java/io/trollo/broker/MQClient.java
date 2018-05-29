package io.trollo.broker;

import io.trollo.domain.Payload;

import java.util.function.Consumer;

public interface MQClient {

    <P extends Payload> void publish(String exchangeName, String routingKey, P payload);

    <P extends Payload> void consume(String exchangeName, String queueName, String routingKey, Consumer<P> consumer);
}
