package com.accela.codingexercise.services.person;

import com.accela.codingexercise.model.Person;
import com.accela.codingexercise.repositories.PersonRepository;
import com.accela.codingexercise.service.PersonServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static java.util.Optional.ofNullable;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PersonServiceTest {
    public static final String FIRST_NAME = "Mike";
    public static final long ID = 1L;

    @Mock
    PersonRepository personRepository;

    @InjectMocks
    PersonServiceImpl personService;


    Person person;

    @BeforeEach
    void setUp() {
        person = Person.builder().id(ID).firstName(FIRST_NAME).build();
    }

    @Test
    void findPersonByIdTest() {
        when(personRepository.findById(anyLong())).thenReturn(ofNullable(person));

        Person mike = personService.getPersonById(ID);

        assertEquals(FIRST_NAME, mike.getFirstName());

    }

    @Test
    void findPersonByIdThrowsRunTimeExceptionTest() {
        RuntimeException thrown = assertThrows(
                RuntimeException.class,
                () -> personService.getPersonById(1L),
                "Person not found for id :" + 0

        );

        assertTrue(thrown.getMessage().contains("Person not found for id"));
    }

    @Test
    void findAllPersonsTest() {
        List<Person> persons = new ArrayList<>();
        persons.add(Person.builder().id(ID).build());
        persons.add(Person.builder().id(2L).build());

        when(personRepository.findAll()).thenReturn(persons);

        List<Person> listPersons = personService.findAllPersons();

        assertNotNull(persons);
        assertEquals(2, listPersons.size());
    }

    @Test
    void savePerson() {
        Person person = Person.builder().id(ID).build();

        Person savedPerson = personService.savePerson(person);

        assertEquals(person, savedPerson);

    }

    @Test
    void deletePerson() {
        personService.deletePerson(ID);

        assertEquals(0, personService.findAllPersons().size());

    }

}
