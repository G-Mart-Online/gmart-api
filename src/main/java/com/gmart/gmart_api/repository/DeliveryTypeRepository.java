package com.gmart.gmart_api.repository;

import com.gmart.gmart_api.model.DeliveryType;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeliveryTypeRepository extends MongoRepository<DeliveryType, String> {

}
