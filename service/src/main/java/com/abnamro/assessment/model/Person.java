package com.abnamro.assessment.model;

import java.time.LocalDate;

/**
 * Person
 */
public class Person {

    private String name;
    private LocalDate birthDate;

    public Person() {}

    public Person(String name, LocalDate birthDate) {
        this.name = name;
        this.birthDate = birthDate;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}