package com.accela.codingexercise.controllers;

import com.accela.codingexercise.model.Address;
import com.accela.codingexercise.service.AddressService;
import com.accela.codingexercise.service.PersonService;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@DynamicUpdate
public class AddressController {

    public static final String VIEW_ADDRESS_INFO = "address_info";
    public static final String ADD_NEW_ADDRESS = "new_address";
    public static final String REDIRECT_PERSON_ID_ADD_NEW_ADDRESS = "redirect:/person/{id}/address/new";
    public static final String EDIT_ADDRESS = "edit_address";
    public static final String REDIRECT_VIEW_ADDRESS_ID = "redirect:/view/address/{id}";
    public static final String REDIRECT_TO_HOMEPAGE = "redirect:/";
    public static final String ADDRESS = "address";

    public AddressController(PersonService personService, AddressService addressService) {
        this.personService = personService;
        this.addressService = addressService;
    }

    private final PersonService personService;
    private final AddressService addressService;

    @GetMapping("/person/{id}/address/new")
    public String createAddressForm(@PathVariable("id") Long personId, Model model) {
        var address = new Address();
        var person = personService.getPersonById(personId);
        address.setPerson(person);

        model.addAttribute(ADDRESS, address);

        return ADD_NEW_ADDRESS;
    }

    @PostMapping("/person/{id}/address/new")
    public String processCreationForm(@PathVariable("id") Long personId, Address address) {
        var person = personService.getPersonById(personId);

        person.addAddress(address);
        addressService.saveAddress(address);

        return REDIRECT_PERSON_ID_ADD_NEW_ADDRESS;
    }

    @GetMapping("/view/address/{id}")
    public String viewAddress(@PathVariable Long id, Model model) {
        var address = addressService.findAddressById(id);

        model.addAttribute(ADDRESS, address);

        return VIEW_ADDRESS_INFO;
    }


    @GetMapping("/address/{id}")
    public String editAddress(@PathVariable Long id, Model model) {
        var address = addressService.findAddressById(id);

        model.addAttribute(ADDRESS, address);

        return EDIT_ADDRESS;
    }

    @PostMapping("/address/{id}")
    public String processUpdateForm(Address address) {
        addressService.saveAddress(address);

        return REDIRECT_VIEW_ADDRESS_ID;

    }


    @GetMapping("/address/delete/{id}")
    public String deletePerson(@PathVariable(value = "id") long id) {
        addressService.deleteAddress(id);

        return REDIRECT_TO_HOMEPAGE;
    }
}


