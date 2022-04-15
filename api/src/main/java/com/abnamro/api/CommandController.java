package com.abnamro.api;

import com.abnamro.assessment.exception.EmployeeAlreadyExistException;
import com.abnamro.assessment.exception.EmployeeNameInvalidException;
import com.abnamro.assessment.model.Person;
import com.abnamro.assessment.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class CommandController {

    @Autowired
    private PersonService personService;

    @PostMapping(value = "/person/v1", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> savePerson(@RequestBody Person person) throws Exception {
        personService.createPerson(person);
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ExceptionHandler(value = EmployeeAlreadyExistException.class)
    public ResponseEntity handleEmployeeAlreadyExistException(EmployeeAlreadyExistException employeeAlreadyExistException) {
        return new ResponseEntity(employeeAlreadyExistException.getMessage(), HttpStatus.FORBIDDEN);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = EmployeeNameInvalidException.class)
    public ResponseEntity handleEmployeeNameInvalidException(EmployeeNameInvalidException employeeNameInvalidException) {
        return new ResponseEntity(employeeNameInvalidException.getMessage(), HttpStatus.BAD_REQUEST);
    }

}
