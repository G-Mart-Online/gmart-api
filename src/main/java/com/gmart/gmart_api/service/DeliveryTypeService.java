package com.gmart.gmart_api.service;

import com.gmart.gmart_api.exceptions.DeliveryTypeNotFoundException;
import com.gmart.gmart_api.model.DeliveryType;
import com.gmart.gmart_api.repository.DeliveryTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DeliveryTypeService {

    private final DeliveryTypeRepository deliveryTypeRepository;

    @Autowired
    public DeliveryTypeService(DeliveryTypeRepository deliveryTypeRepository) {
        this.deliveryTypeRepository = deliveryTypeRepository;
    }

    public DeliveryType saveDeliveryType(DeliveryType deliveryType) {
        try {
            return deliveryTypeRepository.save(deliveryType);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to save a delivery type", e);
        }
    }

    public List<DeliveryType> getAllDeliveryTypes() {
        try {
            return deliveryTypeRepository.findAll();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to fetch delivery types", e);
        }
    }

    public DeliveryType getDeliveryTypeById(String deliveryTypeId) {
        return deliveryTypeRepository.findById(deliveryTypeId).orElseThrow(() -> new DeliveryTypeNotFoundException("Delivery type not found with Id " + deliveryTypeId));
    }

    public void deleteDeliveryTypeById(String deliveryTypeId) {
        Optional<DeliveryType> existingDeliveryType = deliveryTypeRepository.findById(deliveryTypeId);

        if (existingDeliveryType.isPresent()) {
            try {
                deliveryTypeRepository.deleteById(deliveryTypeId);
            } catch (Exception e) {
                e.printStackTrace();
                throw new RuntimeException("Failed to delete the delivery type with ID: " + deliveryTypeId);
            }
        } else {
            throw new DeliveryTypeNotFoundException("Delivery type not found with Id " + deliveryTypeId);
        }
    }

    public DeliveryType updateDeliveryType(String deliveryTypeId, DeliveryType deliveryTypeDetails) {
        DeliveryType deliveryType = deliveryTypeRepository.findById(deliveryTypeId).orElseThrow(() -> new DeliveryTypeNotFoundException("Delivery type not found with id " + deliveryTypeId));

        try {
            deliveryType.setDeliveryService(deliveryTypeDetails.getDeliveryService());
            deliveryType.setTrackingSiteUrl(deliveryTypeDetails.getTrackingSiteUrl());
            deliveryType.setDescription(deliveryTypeDetails.getDescription());

            return deliveryTypeRepository.save(deliveryType);

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to update the delivery type for Id " + deliveryTypeId);
        }
    }
}
