package com.abnamro.assessment.service;

import com.abnamro.assessment.entity.PersonEntity;
import com.abnamro.assessment.model.Person;
import com.abnamro.assessment.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * PersonService
 */
@Service
public class PersonService implements IPersonService {

    @Autowired
    private PersonRepository personRepository;

    List<String> bannedYears = new ArrayList<>();

    private static final String PATTERN_CHECK_NAME = "[0-9].*[0-9]";

    public void createPerson(Person p) {
        if (validateCreateRequest(p)) {
            PersonEntity personEntity = PersonEntity.builder()
                    .personName(p.getName())
                    .birthDate(p.getBirthDate())
                    .build();
            personRepository.save(personEntity);
        }
    }

    public List<Person> listFilteredPersons() {
        List<PersonEntity> personEntities = personRepository.findAll().stream().filter(
                        person -> isBirthYearBanned(person.getBirthDate().getYear()))
                .collect(Collectors.toList());
        return convert(personEntities);
    }

    private boolean isBirthYearBanned(Integer birthYear) {
        String bannedYearIncluded = bannedYears.stream().filter(bannedYear -> bannedYear.equals(String.valueOf(birthYear)))
                .findAny().orElse(null);
        return bannedYearIncluded != null;
    }


    private boolean validateCreateRequest(Person person) {
        return validateName(person.getName()) && isNameAlreadyExist(person.getName());
    }

    private boolean validateName(String personName) {
        return Pattern.compile(PATTERN_CHECK_NAME).matcher(personName).find();
    }

    private boolean isNameAlreadyExist(String personName) {
        return personRepository.isNameExist(personName) > 0;
    }

    private List<Person> convert(List<PersonEntity> personEntities) {
        return personEntities.stream()
                .map(personEntity -> new Person(personEntity.getPersonName(), personEntity.getBirthDate()))
                .collect(Collectors.toList());
    }

    @PostConstruct
    public void populateBannedYears() throws Exception {
        try (Stream<String> lines = Files.lines(Paths.get("src/main/resources/banned-years"))) {
            bannedYears = lines.collect(Collectors.toList());
        }
    }


}