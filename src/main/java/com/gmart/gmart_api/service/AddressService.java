package com.gmart.gmart_api.service;

import com.gmart.gmart_api.exceptions.AddressNotFoundException;
import com.gmart.gmart_api.model.Address;
import com.gmart.gmart_api.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class AddressService {

    private final AddressRepository addressRepository;

    @Autowired
    public AddressService(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    public Address saveAddress(Address address) {
        try {
            return addressRepository.save(address);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to save the address", e);
        }
    }

    public List<Address> getAllAddresses() {
        try {
            return addressRepository.findAll();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to fetch addresses", e);
        }
    }

    public Address getAddressById(String addressId) {
        return addressRepository.findById(addressId).orElseThrow(() -> new AddressNotFoundException("Address not found with Id " + addressId));
    }

    public void deleteAddressById(String addressId) {
        Optional<Address> existingAddress = addressRepository.findById(addressId);

        if (existingAddress.isPresent()) {
            try {
                addressRepository.deleteById(addressId);
            } catch (Exception e) {
                e.printStackTrace();
                throw new RuntimeException("Failed to delete the address with ID: " + addressId);
            }
        } else {
            throw new AddressNotFoundException("Address not found with Id " + addressId);
        }
    }

    public Address updateAddress(String addressId, Address addressDetails) {
        Address address = addressRepository.findById(addressId).orElseThrow(() -> new AddressNotFoundException("Address not found with id " + addressId));

        try {
            address.setAddress1(addressDetails.getAddress1());
            address.setAddress2(addressDetails.getAddress2());
            address.setSuburb(addressDetails.getSuburb());
            address.setCity(addressDetails.getCity());
            address.setProvince(addressDetails.getProvince());
            address.setPostalCode(addressDetails.getPostalCode());

            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
            Date currentDate = new Date();
            String formattedDate = simpleDateFormat.format(currentDate);

            address.setLastUpdateDate(formattedDate);

            return addressRepository.save(address);

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to update the address for Id " + addressId);
        }
    }
}
