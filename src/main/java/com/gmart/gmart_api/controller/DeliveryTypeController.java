package com.gmart.gmart_api.controller;

import com.gmart.gmart_api.model.DeliveryType;
import com.gmart.gmart_api.service.DeliveryTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/deliveryTypes")
public class DeliveryTypeController {

    private final DeliveryTypeService deliveryTypeService;

    @Autowired
    public DeliveryTypeController(DeliveryTypeService deliveryTypeService) {
        this.deliveryTypeService = deliveryTypeService;
    }

    @Secured({"ROLE_ADMIN", "ROLE_SELLER"})
    @PostMapping
    public ResponseEntity<DeliveryType> createDeliveryType(@RequestBody DeliveryType deliveryType) {
        DeliveryType savedDeliveryType = deliveryTypeService.saveDeliveryType(deliveryType);
        return new ResponseEntity<>(savedDeliveryType, HttpStatus.CREATED);
    }

    @Secured({"ROLE_ADMIN", "ROLE_SELLER"})
    @GetMapping("/list")
    public ResponseEntity<List<DeliveryType>> getAllDeliveryTypes() {
        List<DeliveryType> deliveryTypes = deliveryTypeService.getAllDeliveryTypes();
        return new ResponseEntity<>(deliveryTypes, HttpStatus.OK);
    }

    @Secured({"ROLE_ADMIN", "ROLE_SELLER"})
    @GetMapping("/{deliveryTypeId}")
    public ResponseEntity<DeliveryType> getDeliveryTypeById(@PathVariable String deliveryTypeId) {
        DeliveryType deliveryType = deliveryTypeService.getDeliveryTypeById(deliveryTypeId);

        if (deliveryType != null) {
            return new ResponseEntity<>(deliveryType, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Secured({"ROLE_ADMIN"})
    @DeleteMapping("/{deliveryTypeId}")
    public ResponseEntity<Void> deleteDeliveryType(@PathVariable String deliveryTypeId) {
        deliveryTypeService.deleteDeliveryTypeById(deliveryTypeId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Secured({"ROLE_ADMIN", "ROLE_SELLER"})
    @PutMapping("/{deliveryTypeId}")
    public ResponseEntity<DeliveryType> updateDeliveryType(@PathVariable String deliveryTypeId, @RequestBody DeliveryType deliveryType) {
        DeliveryType updatedDeliveryType = deliveryTypeService.updateDeliveryType(deliveryTypeId, deliveryType);
        if (updatedDeliveryType != null) {
            return new ResponseEntity<>(updatedDeliveryType, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
