package com.abnamro.assessment.service;

import static org.junit.Assert.assertEquals;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.abnamro.assessment.model.Person;

import org.junit.Test;

/**
 * PersonServiceTest
 */
public class PersonServiceTest {

    @Test(expected = Exception.class)
    public void createInvalidPersonName() throws Exception {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("test", new HashMap<>());
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();
        new PersonService(em).createPerson(new Person("a", LocalDate.now()));
        em.getTransaction().commit();

    }

 
    @Test(expected = Exception.class)
    public void createInvalidPersonBirthDate() throws Exception {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("test", new HashMap<>());
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();
        new PersonService(em).createPerson(new Person("ab", null));
        em.getTransaction().commit();

    }   

    @Test(expected = Exception.class)
    public void createDuplicatePerson() throws Exception {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("test", new HashMap<>());
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();
        new PersonService(em).createPerson(new Person("ac", LocalDate.now()));
        new PersonService(em).createPerson(new Person("ac", LocalDate.now()));
        em.getTransaction().commit();

    }

    @Test
    public void listFilteredPersons() throws Exception {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("test", new HashMap<>());
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();
        new PersonService(em).createPerson(new Person("bc", LocalDate.now()));
        new PersonService(em).createPerson(new Person("ef", LocalDate.of(1982, 1, 1)));
        em.getTransaction().commit();

         List<Person> persons = new PersonService(em).listFilteredPersons();
        assertEquals(1, persons.size());
    } 
}