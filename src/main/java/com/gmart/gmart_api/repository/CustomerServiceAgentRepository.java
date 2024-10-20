package com.gmart.gmart_api.repository;

import com.gmart.gmart_api.model.CustomerServiceAgent;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerServiceAgentRepository extends MongoRepository<CustomerServiceAgent, String> {
}
