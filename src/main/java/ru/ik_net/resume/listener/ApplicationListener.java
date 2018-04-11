package ru.ik_net.resume.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * Author: Igor Kovalkov.
 * 13.04.2018
 */
@Component
public class  ApplicationListener implements ServletContextListener {
    /**
     * Log.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(ApplicationListener.class);

    /**
     * Production flag.
     */
    @Value("${application.production}")
    private boolean production;

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        sce.getServletContext().setAttribute("application.production", this.production);
        LOGGER.info("Application started.");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        LOGGER.info("Application destroyed.");
    }
}
