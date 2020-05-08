package com.abnamro.assessment.service;

import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

import com.abnamro.assessment.model.Person;

/**
 * PersonService
 */
@ApplicationScoped
@Transactional
public class PersonService {

    @PersistenceContext
    EntityManager em;

    public PersonService(EntityManager em) {
        this.em = em;
    }

    public void createPerson(@Valid Person p) {
        em.persist(p);
    }

    public List<Person> listFilteredPersons() {
        List<Person> persons = em.createQuery("FROM Person p", Person.class).getResultList();
        return persons.stream()
            .filter(p -> !getBannedYears().contains(p.getBirthDate().getYear()))
            .collect(Collectors.toList());
    }

    private List<Integer> getBannedYears() {
        try {
            URL resource = PersonService.class.getResource("/banned-years");
            return Files.lines(Paths.get(resource.toURI()))
                    .map(t -> Integer.valueOf((String) t))
                    .collect(Collectors.toList());
        } catch (Exception ex) {
            return Collections.EMPTY_LIST;
        }

    }
}