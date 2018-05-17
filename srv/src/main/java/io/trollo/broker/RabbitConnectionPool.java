package io.trollo.broker;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import org.slf4j.Logger;
import restx.factory.Component;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeoutException;

import static org.slf4j.LoggerFactory.getLogger;

@Component
public class RabbitConnectionPool {

    private static final Logger logger = getLogger(RabbitConnectionPool.class);

    private final ConnectionFactory factory;
    private final ExecutorService executor = Executors.newFixedThreadPool(10);
    private Connection connection;
    private Channel channel;

    public RabbitConnectionPool(ConnectionFactory factory) {
        this.factory = factory;
        start();
    }

    private synchronized void ensureStarted() {
        if (connection == null) {
            start();
        }
    }

    Channel getChannel() {
        ensureStarted();
        return channel;
    }

    private void start() {
        if (connection == null) {
            logger.info("Starting RabbitMQ connection pool {}", this);
            try {
                connection = factory.newConnection(executor);
                channel = connection.createChannel();
            } catch (IOException | TimeoutException e) {
                logger.error("Error during RabbitMQ connection establishment", e);
            }
        }
    }
}
