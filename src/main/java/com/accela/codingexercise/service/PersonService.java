package com.accela.codingexercise.service;

import com.accela.codingexercise.model.Person;

import java.util.List;

public interface PersonService {
    List<Person> findAllPersons();

    Person savePerson(Person person);

    Person getPersonById(long id);

    void deletePerson(long id);

}
