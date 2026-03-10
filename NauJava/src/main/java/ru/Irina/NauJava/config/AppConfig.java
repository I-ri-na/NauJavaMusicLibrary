package ru.Irina.NauJava.config;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import ru.Irina.NauJava.console.CommandProcessor;
import ru.Irina.NauJava.entity.Score;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class AppConfig {

    @Value("${app.name}")
    private String appName;

    @Value("${app.version}")
    private String appVersion;

    @Bean
    @Scope(value = BeanDefinition.SCOPE_SINGLETON)
    public List<Score> scoreContainer() {
        return new ArrayList<>();
    }

    @PostConstruct
    public void printAppInfo() {
        System.out.println("Приложение: " + appName);
        System.out.println("Версия:     " + appVersion);
    }

    @Bean
    public CommandLineRunner commandScanner(CommandProcessor commandProcessor) {
        return args -> {
            try (java.util.Scanner scanner = new java.util.Scanner(System.in)) {
                System.out.println("Команды: create | find | delete | update | list | bycomposer | exit");
                while (true) {
                    System.out.print("> ");
                    String input = scanner.nextLine();
                    if ("exit".equalsIgnoreCase(input.trim())) {
                        System.out.println("Выход...");
                        break;
                    }
                    commandProcessor.processCommand(input);
                }
            }
        };
    }
}
