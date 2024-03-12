package com.example.demo.service;

import com.example.demo.model.domain.Person;
import com.example.demo.model.request.PersonRequest;
import com.example.demo.model.response.PersonResponse;
import com.example.demo.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonService {
    private final PersonRepository personRepository;

    @Autowired
    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public List<Person> getPersons(String firstName){
        if(firstName != null && !firstName.isBlank()){
            return personRepository.findAllByFirstName(firstName);
        }
        return personRepository.findAll();
    }

    public PersonResponse getPersonById(Long id){
        Person person = personRepository.findById(id).get();
        return convertPersonToPersonResponse(person);
    }

    public Person createPerson(PersonRequest request) {
        Person person = convertPersonRequestToPerson(request);
        return personRepository.save(person);
    }

    private PersonResponse convertPersonToPersonResponse(Person person){
        return person == null
                ? null
                : new PersonResponse(person.getFirstName(),
                                     person.getLastName(),
                                     person.getEmail(),
                                     person.getPhone());
    }

    private Person convertPersonRequestToPerson(PersonRequest request){
        return request == null
                ? null
                : Person.builder()
                        .firstName(request.getFirstName())
                        .lastName(request.getLastName())
                        .email(request.getEmail())
                        .phone(request.getPhone())
                        .build();
    }

}
