package com.gmart.gmart_api.repository;

import com.gmart.gmart_api.model.BankAccountDetails;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BankAccountDetailsRepository extends MongoRepository<BankAccountDetails, String> {

    List<BankAccountDetails> findBankAccountByAccountHolderName(String accountHolderName);

    BankAccountDetails findBankAccountDetailsByAccountNumber(String accountNumber);
}
