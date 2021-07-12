package com.accela.codingexercise.controllers.person;

import com.accela.codingexercise.controllers.PersonController;
import com.accela.codingexercise.model.Person;
import com.accela.codingexercise.service.PersonService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class PersonControllerTest {

    @Mock
    PersonService personService;

    @InjectMocks
    PersonController personController;

    MockMvc mockMvc;

    @BeforeEach
    void setup() {
        mockMvc = MockMvcBuilders
                .standaloneSetup(personController)
                .build();
    }

    @Test
    void testViewHomePage() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(view().name("index"))
                .andExpect(model().attributeExists("listPersons"))
                .andExpect(model().attributeExists("noOfPersons"));
    }

    @Test
    void testDisplayNewPersonForm() throws Exception {
        mockMvc.perform(get("/add/new/person"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("person"))
                .andExpect(view().name("new_person"));
    }


    @Test
    void testDisplayPerson() throws Exception {
        when(personService.getPersonById(anyLong()))
                .thenReturn(Person.builder().id(1L).build());

        mockMvc.perform(get("/person/1"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("person"))
                .andExpect(view().name("person_details"));

        verify(personService).getPersonById(anyLong());
    }

    @Test
    void testCreatePerson() throws Exception {
        mockMvc.perform(post("/add/new/person").param("firstName", "Mike").param("lastName", "Cleary"))
                .andExpect(status().is3xxRedirection());
    }

    @Test
    void testEditPerson() throws Exception {
        when(personService.getPersonById(anyLong()))
                .thenReturn(Person.builder().id(1L).build());

        mockMvc.perform(get("/edit/person/1"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("person"))
                .andExpect(view().name("update_person"));

        verify(personService).getPersonById(anyLong());
    }

    @Test
    void testDeletePerson() throws Exception {

        mockMvc.perform(get("/deletePerson/1"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/"));
    }
}
