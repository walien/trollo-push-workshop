package io.trollo.broker;

import io.trollo.domain.Payload;

import java.util.function.Consumer;

public interface MQClient {

    Exchange declareExchange(Exchange exchange);

    Queue declareQueue(Queue queue, Exchange exchange, String routingKey);

    void deleteQueue(Queue queue);

    <P extends Payload> void publish(Exchange exchange, String routingKey, P payload);

    <P extends Payload> void consume(Queue queue, Consumer<P> consumer);
}
