package com.abnamro.assessment.service;

import com.abnamro.assessment.entity.PersonEntity;
import com.abnamro.assessment.model.Person;
import com.abnamro.assessment.repository.PersonRepository;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

/**
 * PersonServiceTest
 */
public class PersonServiceTest {

    @Mock
    private PersonRepository personRepository;

    @Autowired
    @InjectMocks
    private PersonService personService;

    @BeforeMethod
    @BeforeClass
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        when(personRepository.findAll()).thenReturn(getAllPersonData());
    }

    @Test
    public void createPerson() throws Exception {
        when(personRepository.save(any(PersonEntity.class))).thenReturn(mockSavedPerson());
        personService.createPerson(new Person("Person1", LocalDate.of(1980, 1,1)));
    }

    @Test
    public void listFilteredPersons() throws Exception {
        List<Person> people = personService.listFilteredPersons();
        Assert.assertNotNull(people);
    }

    private PersonEntity mockSavedPerson() {
        return PersonEntity.builder()
                .personName("Person1")
                .birthDate(LocalDate.of(1980, 1,1))
                .build();
    }

    private List<PersonEntity> getAllPersonData() {

        PersonEntity person1 = PersonEntity.builder()
                .personName("Person1")
                .birthDate(LocalDate.of(1980, 1,1))
                .build();
        PersonEntity bannedYear1 = PersonEntity.builder()
                .personName("Person1")
                .birthDate(LocalDate.of(1982, 1,1))
                .build();
        PersonEntity person3 = PersonEntity.builder()
                .personName("Person1")
                .birthDate(LocalDate.of(1985, 1,1))
                .build();
        PersonEntity person4 = PersonEntity.builder()
                .personName("Person1")
                .birthDate(LocalDate.of(1992, 1,1))
                .build();
        PersonEntity bannedYear2 = PersonEntity.builder()
                .personName("Person1")
                .birthDate(LocalDate.of(1999, 1,1))
                .build();
        PersonEntity bannedYear3 = PersonEntity.builder()
                .personName("Person1")
                .birthDate(LocalDate.of(2002, 1,1))
                .build();
        return Arrays.asList(person1, bannedYear1, person3, person4, bannedYear2, bannedYear3);
    }
}