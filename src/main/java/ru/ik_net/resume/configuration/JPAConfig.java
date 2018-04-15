package ru.ik_net.resume.configuration;


import org.apache.commons.dbcp2.BasicDataSource;
import org.hibernate.jpa.HibernatePersistenceProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

/**
 * @author Igor Kovalkov
 * @link http://ik-net.ru
 * 14.04.2018
 */
@Configuration
@PropertySource("classpath:application.properties")
@EnableTransactionManagement
@EnableJpaRepositories("ru.ik_net.resume.data.storage")
public class JPAConfig {

    /**
     * environment.
     */
    @Autowired
    private Environment environment;

    /**
     * @return entityManagerFactory
     */
    @Bean
    LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean emf = new LocalContainerEntityManagerFactoryBean();
        emf.setDataSource(dataSource());
        emf.setPersistenceProviderClass(HibernatePersistenceProvider.class);
        emf.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        emf.setPackagesToScan("ru.ik_net.resume.data.model");
        emf.setJpaProperties(hibernateProperties());
        return emf;
    }

    /**
     * @return hibernateProperties
     */
    private Properties hibernateProperties() {
        Properties p = new Properties();
        p.put("hibernate.dialect", environment.getRequiredProperty("hibernate.dialect"));
        p.put("javax.persistence.validation.mode", "none");
        return p;
    }

    /**
     * @return dataSource
     */
    @Bean
    public DataSource dataSource() {
        BasicDataSource ds = new BasicDataSource();
        ds.setDriverClassName(environment.getRequiredProperty("db.driver"));
        ds.setUrl(environment.getRequiredProperty("db.url"));
        ds.setUsername(environment.getRequiredProperty("db.username"));
        ds.setPassword(environment.getRequiredProperty("db.password"));
        ds.setInitialSize(Integer.parseInt(environment.getRequiredProperty("db.pool.initSize")));
        ds.setMaxTotal(Integer.parseInt(environment.getRequiredProperty("db.pool.maxSize")));
        return ds;
    }

    /**
     * @return transactionManager
     */
    @Bean
    public JpaTransactionManager transactionManager() {
        JpaTransactionManager tx = new JpaTransactionManager();
        tx.setEntityManagerFactory(entityManagerFactory().getObject());
        return tx;
    }



}
