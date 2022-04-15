package com.abnamro.assessment.service;

import com.abnamro.assessment.model.Person;

import java.util.List;

/**
 * PersonService Interface
 */

public interface IPersonService {
    void createPerson(Person p) ;
    List<Person> listFilteredPersons();
}