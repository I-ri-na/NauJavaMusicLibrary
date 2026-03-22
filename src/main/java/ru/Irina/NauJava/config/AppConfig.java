package ru.Irina.NauJava.config;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Value("${app.name}")
    private String appName;

    @Value("${app.version}")
    private String appVersion;

    @PostConstruct
    public void printAppInfo() {
        System.out.println("Приложение: " + appName);
        System.out.println("Версия:     " + appVersion);
    }
}
