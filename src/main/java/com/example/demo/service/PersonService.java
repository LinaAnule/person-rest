package com.example.demo.service;

import com.example.demo.model.domain.Person;
import com.example.demo.model.exception.NoPersonFoundException;
import com.example.demo.model.request.PersonRequest;
import com.example.demo.model.response.PersonResponse;
import com.example.demo.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PersonService {
    private final PersonRepository personRepository;

    @Autowired
    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public List<PersonResponse> getPersons(String firstName) {
        if (firstName != null && !firstName.isBlank()) {
            return convertPersonListToPersonResponseList(personRepository.findAllByFirstName(firstName));
        }
        return convertPersonListToPersonResponseList(personRepository.findAll());
    }

    public PersonResponse getPersonById(Long id) {
        try {
            Person person = personRepository.findById(id).get();
            return convertPersonToPersonResponse(person);
        }catch (Exception e){
            throw new NoPersonFoundException();
        }
    }

    public PersonResponse createPerson(PersonRequest request) {
        Person person = convertPersonRequestToPerson(request);
        return convertPersonToPersonResponse(personRepository.save(person));
    }

    public PersonResponse updatePerson(PersonRequest request, Long id) {


        try{
            Person oldPerson = personRepository.findById(id).orElse(null);
        Person person = convertPersonRequestToPerson(request);
        assert oldPerson != null;
        oldPerson.setFirstName(person.getFirstName());
        oldPerson.setLastName(person.getLastName());
        oldPerson.setEmail(person.getEmail());
        oldPerson.setPhone(person.getEmail());
            return convertPersonToPersonResponse(personRepository.save(oldPerson));}
        catch (Exception e){
            throw new NoPersonFoundException();
        }

    }

    public void deletePerson(Long id) {
        try {
            personRepository.findById(id).get();
            personRepository.deleteById(id);
        } catch (Exception e) {
            throw new NoPersonFoundException();
        }
    }

    private PersonResponse convertPersonToPersonResponse(Person person) {
        return person == null
                ? null
                : new PersonResponse(person.getFirstName(),
                person.getLastName(),
                person.getEmail(),
                person.getPhone());
    }

    private List<PersonResponse> convertPersonListToPersonResponseList(List<Person> person) {
        return person.stream()
                .map(this::convertPersonToPersonResponse)
                .collect(Collectors.toList());
    }

    private Person convertPersonRequestToPerson(PersonRequest request) {
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
