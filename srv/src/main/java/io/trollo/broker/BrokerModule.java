package io.trollo.broker;

import com.rabbitmq.client.ConnectionFactory;
import org.slf4j.Logger;
import restx.factory.AutoStartable;
import restx.factory.Module;
import restx.factory.Provides;

import javax.inject.Named;

import static org.slf4j.LoggerFactory.getLogger;

@Module
public class BrokerModule {

    private static final Logger logger = getLogger(BrokerModule.class);

    @Provides
    public ConnectionFactory connectionFactory(@Named("broker.host") String host,
                                               @Named("broker.login") String login,
                                               @Named("broker.password") String password) {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost(host);
        factory.setUsername(login);
        factory.setPassword(password);
        return factory;
    }

    @Provides
    public AutoStartable brokerConfiguration(BrokerSettings brokerSettings) {
        return () -> logger.info("Broker enabled - {}@{}", brokerSettings.brokerLogin(), brokerSettings.brokerHost());
    }
}
