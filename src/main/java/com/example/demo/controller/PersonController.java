package com.example.demo.controller;

import com.example.demo.model.domain.Person;
import com.example.demo.model.request.PersonRequest;
import com.example.demo.model.response.PersonResponse;
import com.example.demo.service.PersonService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/persons")
@Tag(name = "Person Controller", description = "Provides service with person data")
public class PersonController {

    private final PersonService personService;

    @Autowired
    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping
    @Operation(summary = "Get person Data")
    public List<PersonResponse> getPersons(@RequestParam(required = false) String firstName) {
        return personService.getPersons(firstName);
    }

    @GetMapping(value = "/{id}")
    public PersonResponse getPersonById(@PathVariable Long id) {
        return personService.getPersonById(id);
    }

    @PostMapping
    @Operation(summary = "Save person data")
    public PersonResponse savePerson(@Validated @RequestBody PersonRequest personRequest) {
        return personService.createPerson(personRequest);
    }

    @PutMapping(value = "/{id}")
    @Operation(summary = "Change person data")
    public PersonResponse updatePerson(@Validated @RequestBody PersonRequest personRequest, @PathVariable Long id) {
        return personService.updatePerson(personRequest, id);
    }

    @DeleteMapping(value = "/{id}")
    @Operation(summary = "Delete person")
    public void deletePerson(@PathVariable Long id) {
        personService.deletePerson(id);
    }
}
