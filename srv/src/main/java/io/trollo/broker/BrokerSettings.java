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

    @SettingsKey(key = "stomp.url", defaultValue = "http://127.0.0.1:15674/stomp")
    String stompUrl();

    @SettingsKey(key = "stomp.login", defaultValue = "guest")
    String stompLogin();

    @SettingsKey(key = "stomp.password", defaultValue = "guest")
    String stompPassword();

}