package ru.ik_net.resume.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

/**
 * Author: Igor Kovalkov.
 * 13.04.2018
 */
@Configuration
@ComponentScan({
        "ru.ik_net.resume.controller",
        "ru.ik_net.resume.filter",
        "ru.ik_net.resume.listener",
        "ru.ik_net.resume.service"})
public class Config {

    /**
     * @return appConfiguration
     */
    @Bean
    public static PropertySourcesPlaceholderConfigurer placeholderConfigurer() {
        PropertySourcesPlaceholderConfigurer config = new PropertySourcesPlaceholderConfigurer();
        config.setLocations(getResources());
        return config;
    }

    /**
     * @return AppResources
     */
    private static Resource[] getResources() {
        return new Resource[] {new ClassPathResource("application.properties")};
    }
}
