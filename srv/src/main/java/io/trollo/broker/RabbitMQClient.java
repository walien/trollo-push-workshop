package io.trollo.broker;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;
import io.trollo.domain.Payload;
import org.slf4j.Logger;
import restx.factory.Component;
import restx.jackson.FrontObjectMapperFactory;

import javax.inject.Named;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

import static org.slf4j.LoggerFactory.getLogger;

@Component
public class RabbitMQClient implements MQClient {

    private static final Logger logger = getLogger(RabbitMQClient.class);

    private final RabbitConnectionPool connectionPool;
    private final ObjectMapper objectMapper;

    public RabbitMQClient(RabbitConnectionPool connectionPool, @Named(FrontObjectMapperFactory.MAPPER_NAME) ObjectMapper objectMapper) {
        this.connectionPool = connectionPool;
        this.objectMapper = objectMapper;
    }

    @Override
    public <P extends Payload> void publish(String exchangeName, String routingKey, P event) {
        try {
            byte[] valueAsBytes = objectMapper.writeValueAsBytes(event);
            Map<String, Object> headers = new HashMap<>();
            headers.put("Content-Type", "application/json");
            connectionPool.getChannel().basicPublish(exchangeName, routingKey, new AMQP.BasicProperties.Builder().headers(headers).build(), valueAsBytes);
            logger.debug("Message published on {} (routingKey = {})", exchangeName, routingKey);
        } catch (IOException e) {
            logger.error("rabbitmq - unable to publish message", e);
        }
    }

    @Override
    public <P extends Payload> void consume(String exchangeName, String queueName, String routingKey, Consumer<P> consumer) {
        Channel channel = connectionPool.getChannel();
        try {
            channel.queueBind(queueName, exchangeName, routingKey);
            channel.basicConsume(queueName, true, new DefaultConsumer(channel) {
                @Override
                public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                    super.handleDelivery(consumerTag, envelope, properties, body);
                    P payload = (P) objectMapper.readValue(body, Payload.class);
                    consumer.accept(payload);
                }
            });
        } catch (IOException e) {
            logger.error("rabbitmq - unable to consume message", e);
        }
    }
}
