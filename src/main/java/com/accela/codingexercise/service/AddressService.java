package com.accela.codingexercise.service;

import com.accela.codingexercise.model.Address;

public interface AddressService {
    Address saveAddress(Address address);
    Address findAddressById(long id);
    void deleteAddress(long id);
}
