package com.abnamro.assessment.web.api.rest;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.abnamro.assessment.service.PersonService;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.LocalEntityManagerFactoryBean;

/**
 * PersonConfiguration
 */
@Configuration
public class PersonConfiguration {

    @PersistenceContext(name = "test")
    EntityManager em;

    @Bean
    PersonService personService() {
        return new PersonService(em);
    }

    @Bean
    public LocalEntityManagerFactoryBean entityManagerFactoryBean() {
        LocalEntityManagerFactoryBean factory = new LocalEntityManagerFactoryBean();
        factory.setPersistenceUnitName("test");
        return factory;
    }

}