package com.accela.codingexercise.service;

import com.accela.codingexercise.model.Person;
import com.accela.codingexercise.repositories.PersonRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PersonServiceImpl implements PersonService {

    private final PersonRepository personRepository;

    public PersonServiceImpl(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Override
    public List<Person> findAllPersons() {
        return personRepository.findAll();
    }

    @Override
    public Person savePerson(Person person) {
        personRepository.save(person);
        return person;
    }

    @Override
    public Person getPersonById(long id) {
        Optional<Person> optionalPerson = personRepository.findById(id);
        Person person;
        if (optionalPerson.isPresent()) {
            person = optionalPerson.get();
        } else {
            throw new RuntimeException("Person not found for id :" + id);
        }
        return person;
    }

    @Override
    public void deletePerson(long id) {
        personRepository.deleteById(id);
    }
}
