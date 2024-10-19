package com.gmart.gmart_api.controller;

import com.gmart.gmart_api.model.Address;
import com.gmart.gmart_api.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/addresses")
public class AddressController {

    private final AddressService addressService;

    @Autowired
    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    @Secured({"ROLE_ADMIN", "ROLE_SELLER"})
    @PostMapping
    public ResponseEntity<Address> createAddress(@RequestBody Address address) {
        Address savedAddress = addressService.saveAddress(address);
        return new ResponseEntity<>(savedAddress, HttpStatus.CREATED);
    }

    @Secured({"ROLE_ADMIN", "ROLE_SELLER"})
    @GetMapping("/list")
    public ResponseEntity<List<Address>> getAllAddresses() {
        List<Address> addresses = addressService.getAllAddresses();
        return new ResponseEntity<>(addresses, HttpStatus.OK);
    }

    @Secured({"ROLE_ADMIN", "ROLE_SELLER"})
    @GetMapping("/{addressId}")
    public ResponseEntity<Address> getAddressById(@PathVariable String addressId) {
        Address address = addressService.getAddressById(addressId);

        if (address != null) {
            return new ResponseEntity<>(address, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Secured({"ROLE_ADMIN"})
    @DeleteMapping("/{addressId}")
    public ResponseEntity<Void> deleteAddress(@PathVariable String addressId) {
        addressService.deleteAddressById(addressId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Secured({"ROLE_ADMIN", "ROLE_SELLER"})
    @PutMapping("/{addressId}")
    public ResponseEntity<Address> updateAddress(@PathVariable String addressId, @RequestBody Address address) {
        Address updatedAddress = addressService.updateAddress(addressId, address);
        if (updatedAddress != null) {
            return new ResponseEntity<>(updatedAddress, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
