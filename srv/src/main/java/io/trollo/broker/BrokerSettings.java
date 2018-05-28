package io.trollo.broker;

import restx.config.Settings;
import restx.config.SettingsKey;

@Settings
public interface BrokerSettings {

    @SettingsKey(key = "broker.host", defaultValue = "localhost")
    String brokerHost();

    @SettingsKey(key = "broker.login", defaultValue = "guest")
    String brokerLogin();

    @SettingsKey(key = "broker.password", defaultValue = "guest")
    String brokerPassword();

}