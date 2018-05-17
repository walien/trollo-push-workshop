package io.trollo;

import com.google.common.base.Charsets;
import com.google.common.base.Predicates;
import com.google.common.collect.ImmutableList;
import restx.config.ConfigLoader;
import restx.config.ConfigSupplier;
import restx.factory.Module;
import restx.factory.Provides;
import restx.security.CORSAuthorizer;
import restx.security.SignatureKey;
import restx.security.StdCORSAuthorizer;

import javax.inject.Named;

@Module
public class AppModule {

    @Provides
    public SignatureKey signatureKey() {
        return new SignatureKey("srv aa00fc9e-a587-42b8-aa0c-fc4e03f63262 srv 2994584264414006588".getBytes(Charsets.UTF_8));
    }

    @Provides
    @Named("env")
    public String appEnv() {
        return "dev";
    }

    @Provides
    public ConfigSupplier appConfigSupplier(ConfigLoader configLoader) {
        // Load settings.properties in io.trollo package as a set of config entries
        return configLoader.fromResource("io/trollo/settings");
    }

    @Provides
    public CORSAuthorizer authorizeCORS() {
        return new StdCORSAuthorizer(
                Predicates.alwaysTrue(), Predicates.alwaysTrue(),
                ImmutableList.of("GET", "POST", "DELETE", "PUT"), ImmutableList.of("accept", "content-type", "authorization"),
                com.google.common.base.Optional.fromNullable(true), 0);
    }

    @Provides
    @Named("restx.admin.password")
    public String restxAdminPassword() {
        return "admin";
    }
}
