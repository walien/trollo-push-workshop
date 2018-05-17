package io.trollo;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.guava.GuavaModule;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.mongodb.MongoClient;
import org.jongo.Mapper;
import org.jongo.marshall.jackson.JacksonMapper;
import org.jongo.marshall.jackson.bson4jackson.BsonModule;
import org.jongo.marshall.jackson.bson4jackson.MongoBsonFactory;
import org.slf4j.Logger;
import restx.factory.AutoStartable;
import restx.factory.Module;
import restx.factory.Provides;
import restx.jackson.Views;
import restx.mongo.MongoModule;

import javax.inject.Named;
import java.util.Optional;

import static org.slf4j.LoggerFactory.getLogger;

@Module
public class DBModule {

    private static final Logger logger = getLogger(DBModule.class);

    private static JacksonMapper.Builder getDefaultJacksonMapperBuilder() {
        return new JacksonMapper.Builder(
                new ObjectMapper(MongoBsonFactory.createFactory())
                        .registerModule(new BsonModule())
                        .registerModule(new JavaTimeModule())
                        .registerModule(new Jdk8Module())
                        .registerModule(new GuavaModule())
                        .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
                        .disable(SerializationFeature.FAIL_ON_EMPTY_BEANS)
                        .disable(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES)
                        .disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)
        );
    }

    public static JacksonMapper.Builder getJacksonMapperBuilder() {
        return getDefaultJacksonMapperBuilder().withView(Views.Private.class);
    }

    @Provides(priority = -100)
    @Named("Mapper")
    public Mapper mapper() {
        return getJacksonMapperBuilder().build();
    }

    @Provides
    public AutoStartable mongoConnectionLogger(@Named("restx.server.id") final Optional<String> serverId,
                                               @Named("mongo.db") final String mongoDBName,
                                               @Named(MongoModule.MONGO_CLIENT_NAME) final MongoClient client) {
        return () -> {
            if (logger.isInfoEnabled()) {
                logger.info("{} - connected to Mongo @ {} - db = {}", serverId.orElse("-"), client.getAllAddress(), mongoDBName);
            }
        };
    }

    @Provides
    @Named("mongo.db")
    public String dbName() {
        return "trollo";
    }
}
