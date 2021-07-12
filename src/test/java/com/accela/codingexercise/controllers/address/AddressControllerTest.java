package com.accela.codingexercise.controllers.address;

import com.accela.codingexercise.controllers.AddressController;
import com.accela.codingexercise.model.Address;
import com.accela.codingexercise.model.Person;
import com.accela.codingexercise.service.AddressService;
import com.accela.codingexercise.service.PersonService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.HashSet;
import java.util.Set;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class AddressControllerTest {

    @Mock
    AddressService addressService;

    @Mock
    PersonService personService;

    @InjectMocks
    AddressController addressController;

    MockMvc mockMvc;


    @BeforeEach
    void setup() {
        mockMvc = MockMvcBuilders
                .standaloneSetup(addressController)
                .build();
    }


    @Test
    void testDisplayAddress() throws Exception {
        when(addressService.findAddressById(anyLong()))
                .thenReturn(Address.builder().id(1L).build());

        mockMvc.perform(get("/view/address/1"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("address"))
                .andExpect(view().name("address_info"));

        verify(addressService).findAddressById(anyLong());
    }

    @Test
    void testDisplayCreateAddressForm() throws Exception {
        when(personService.getPersonById(anyLong()))
                .thenReturn(Person.builder().id(1L).build());

        mockMvc.perform(get("/person/1/address/new"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("address"))
                .andExpect(view().name("new_address"));

        verify(personService).getPersonById(anyLong());
    }

    @Test
    void testAddNewAddressForm() throws Exception {
        Set<Address> address = new HashSet<>();
        Person person = Person.builder().id(1L).firstName("Jake").lastName("Paul").build();
        address.add(Address.builder().id(1L).state("Dublin").postalCode("D24").person(person).build());

        when(personService.getPersonById(anyLong()))
                .thenReturn(Person.builder().id(1L).address(address).build());

        mockMvc.perform(post("/person/1/address/new"))
                .andExpect(status().is3xxRedirection());

        verify(personService).getPersonById(anyLong());
    }


    @Test
    void testViewAddress() throws Exception {
        when(addressService.findAddressById(anyLong()))
                .thenReturn(Address.builder().id(1L).build());

        mockMvc.perform(get("/view/address/1"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("address"))
                .andExpect(view().name("address_info"));

        verify(addressService).findAddressById(anyLong());
    }


    @Test
    void testEditAddressForm() throws Exception {
        mockMvc.perform(post("/address/1").param("city", "Dublin").param("state", "Dublin").param("postalCode", "D13"))
                .andExpect(status().is3xxRedirection());

    }

    @Test
    void deleteAddress() throws Exception {
        mockMvc.perform(get("/address/delete/1"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/"));
    }

}
