package com.accela.codingexercise.services.address;

import com.accela.codingexercise.model.Address;
import com.accela.codingexercise.repositories.AddressRepository;
import com.accela.codingexercise.service.AddressServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static java.util.Optional.ofNullable;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
public class AddressServiceTest {

    public static final String DUBLIN = "Dublin";
    public static final long ID = 1L;

    @Mock
    AddressRepository addressRepository;

    @InjectMocks
    AddressServiceImpl addressService;


    Address address;


    @BeforeEach
    void setUp() {
        address = Address.builder().id(ID).city(DUBLIN).build();
    }

    @Test
    void findAddressByIdTest() {
        when(addressRepository.findById(anyLong())).thenReturn(ofNullable(address));

        Address city = addressService.findAddressById(ID);

        assertEquals(DUBLIN, city.getCity());
    }

    @Test
    void findAddressByIdRunTimeExceptionTest() {
        RuntimeException thrown = assertThrows(
                RuntimeException.class,
                () -> addressService.findAddressById(anyLong()),
                "Address not found for id :" + 0
        );

        assertTrue(thrown.getMessage().contains("Address not found for id"));
    }

    @Test
    void saveAddress() {
        Address address = Address.builder().id(ID).build();

        Address savedAddress = addressService.saveAddress(address);

        assertEquals(address, savedAddress);
    }

    @Test
    void deleteAddress() {
        doNothing().doThrow(new IllegalStateException())
                .when(addressRepository).deleteById(1L);

        addressService.deleteAddress(1L);

        verify(addressRepository).deleteById(1L);
    }

}
