package com.abnamro.api;

import com.abnamro.assessment.model.Person;
import com.abnamro.assessment.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class QueryController {

    @Autowired
    private PersonService personService;

    @GetMapping(value = "/person/v1",produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<Person> getPersons() throws Exception {
        return personService.listFilteredPersons();
    }
}
