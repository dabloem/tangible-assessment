package com.abnamro.assessment.web.api.rest;

import java.time.LocalDate;
import java.util.List;

import javax.validation.ConstraintViolationException;
import javax.validation.Valid;

import com.abnamro.assessment.model.Person;
import com.abnamro.assessment.service.PersonService;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * PersonController
 */
@RestController
public class PersonController {

    @Autowired
    PersonService service;

    
    @GetMapping("/")
    public List<Person> getPersons() {
        return service.listFilteredPersons();
    }

    @PostMapping("/")
    public String addPerson(@Valid @RequestBody Person p) {
        service.createPerson(p);
        return "success";
    }  
    
    @ExceptionHandler(Exception.class)
    public ResponseEntity handleException(Exception cnae) {
        Throwable rootCause = ExceptionUtils.getRootCause(cnae);
        if (rootCause instanceof ConstraintViolationException) {
            ConstraintViolationException cve = (ConstraintViolationException) rootCause;
            return new ResponseEntity<>(cve.getConstraintViolations().iterator().next().getMessage(), HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(rootCause.getClass().getName(), HttpStatus.BAD_REQUEST);
    }
}