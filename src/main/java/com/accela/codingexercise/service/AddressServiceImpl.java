package com.accela.codingexercise.service;

import com.accela.codingexercise.model.Address;
import com.accela.codingexercise.repositories.AddressRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AddressServiceImpl implements AddressService {

    private final AddressRepository addressRepository;

    public AddressServiceImpl(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    @Override
    public Address saveAddress(Address address) {
        addressRepository.save(address);
        return address;
    }

    @Override
    public Address findAddressById(long id) {
        Optional<Address> optionalAddress = addressRepository.findById(id);
        Address address;
        if (optionalAddress.isPresent()) {
            address = optionalAddress.get();
        } else {
            throw new RuntimeException("Address not found for id :" + id);
        }
        return address;
    }

    @Override
    public void deleteAddress(long id) {
        addressRepository.deleteById(id);
    }
}
