package com.accela.codingexercise.controllers;

import com.accela.codingexercise.model.Person;
import com.accela.codingexercise.service.PersonService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class PersonController {
    public static final String PERSON = "person";
    private static final String VIEWS_INDEX_PAGE = "index";
    public static final String ADD_NEW_PERSON = "new_person";
    public static final String VIEW_PERSON_DETAILS = "person_details";
    public static final String UPDATE_PERSON = "update_person";
    public static final String REDIRECT_TO_HOME_PAGE = "redirect:/";

    PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping({"/", "", "index.html"})
    public String viewHomePage(Model model) {
        List<Person> listPersons = personService.findAllPersons();
        Integer noOfPersons = listPersons.size();

        model.addAttribute("listPersons", listPersons);
        model.addAttribute("noOfPersons", noOfPersons);

        return VIEWS_INDEX_PAGE;
    }

    @GetMapping("/add/new/person")
    public String displayNewPersonForm(Model model) {
        var person = new Person();
        model.addAttribute(PERSON, person);

        return ADD_NEW_PERSON;
    }

    @PostMapping("/add/new/person")
    public String addNewPersonForm(@ModelAttribute("person") Person person) {
        personService.savePerson(person);

        return REDIRECT_TO_HOME_PAGE;
    }


    @GetMapping("/person/{id}")
    public String displayPersonForm(@PathVariable Long id, Model model) {
        var person = personService.getPersonById(id);
        model.addAttribute("person", person);

        return VIEW_PERSON_DETAILS;
    }


    @GetMapping("/edit/person/{id}")
    public String displayUpdateForm(@PathVariable(value = "id") long id, Model model) {
        var person = personService.getPersonById(id);

        model.addAttribute("person", person);

        return UPDATE_PERSON;
    }

    @PostMapping("/edit/person/{id}")
    public String editPersonForm(@ModelAttribute("person") Person person) {
        personService.savePerson(person);

        return REDIRECT_TO_HOME_PAGE;
    }


    @GetMapping("/deletePerson/{id}")
    public String deletePersonForm(@PathVariable(value = "id") long id) {
        this.personService.deletePerson(id);

        return REDIRECT_TO_HOME_PAGE;
    }

}
