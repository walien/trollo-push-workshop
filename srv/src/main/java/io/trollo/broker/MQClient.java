package io.trollo.broker;

import io.trollo.domain.Payload;

import java.util.function.Consumer;

public interface MQClient {

    <P extends Payload> void publish(Exchange exchange, String routingKey, P payload);

    <P extends Payload> void consume(Queue queue, Consumer<P> consumer);
}
