package ru.ik_net.resume.configuration;

import org.sitemesh.builder.SiteMeshFilterBuilder;
import org.sitemesh.config.ConfigurableSiteMeshFilter;
import org.springframework.orm.jpa.support.OpenEntityManagerInViewFilter;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.DispatcherServlet;
import ru.ik_net.resume.filter.ErrorFilter;
import ru.ik_net.resume.listener.ApplicationListener;

import javax.servlet.Filter;
import javax.servlet.ServletContext;
import javax.servlet.ServletRegistration;
import javax.servlet.SessionTrackingMode;
import java.util.EnumSet;

/**
 * Author: Igor Kovalkov.
 * 13.04.2018
 */
public class Initialisation implements WebApplicationInitializer {
    @Override
    public void onStartup(ServletContext container) {
        container.setSessionTrackingModes(EnumSet.of(SessionTrackingMode.COOKIE));
        WebApplicationContext context = createContext(container);
        container.addListener(new ContextLoaderListener(context));
        container.addListener(context.getBean(ApplicationListener.class));
        registeredDispatcher(container, context);
        registeredFilters(container, context);
    }

    /** create context.
     * @param container container
     * @return context
     */
    private WebApplicationContext createContext(ServletContext container) {
        AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
        context.scan("ru.ik_net.resume.configuration");
        context.setServletContext(container);
        context.refresh();
        return context;
    }

    /** register filters.
     * @param container container
     * @param context context
     */
    private void registeredFilters(ServletContext container, WebApplicationContext context) {
        registeredFilter(container, context.getBean(ErrorFilter.class));
        registeredFilter(container, new CharacterEncodingFilter("UTF-8", true));
        registeredFilter(container, new OpenEntityManagerInViewFilter());
        registeredFilter(container, new SiteMesh());
    }

    /** register filter.
     * @param container container
     * @param filter filter
     */
    private void registeredFilter(ServletContext container, Filter filter) {
        String filterName = filter.getClass().getSimpleName();
        container.addFilter(filterName, filter)
                .addMappingForUrlPatterns(null, true, "/*");
    }

    /** register dispatcher MVC Servlet.
     * @param container container
     * @param context context
     */
    private void registeredDispatcher(ServletContext container, WebApplicationContext context) {
        ServletRegistration.Dynamic registration = container.addServlet("dispatcher", new DispatcherServlet(context));
        registration.setLoadOnStartup(1);
        registration.addMapping("/");
    }

    /**
     * SiteMesh filter.
     */
    private class SiteMesh extends ConfigurableSiteMeshFilter {
        @Override
        protected void applyCustomConfiguration(SiteMeshFilterBuilder builder) {
            builder.addDecoratorPath("/*", "/WEB-INF/template/page-template.jsp");
        }
    }
}