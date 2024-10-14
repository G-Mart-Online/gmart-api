package com.gmart.gmart_api.service;

import com.gmart.gmart_api.exceptions.BankAccountDetailsNotFoundException;
import com.gmart.gmart_api.model.BankAccountDetails;
import com.gmart.gmart_api.repository.BankAccountDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class BankAccountDetailService {

    private final BankAccountDetailsRepository bankAccountDetailsRepository;

    @Autowired
    public BankAccountDetailService(BankAccountDetailsRepository bankAccountDetailsRepository) {
        this.bankAccountDetailsRepository = bankAccountDetailsRepository;
    }

    public BankAccountDetails saveBankAccountDetails(BankAccountDetails bankAccountDetails) {
        try {
            return bankAccountDetailsRepository.save(bankAccountDetails);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to save the bank account details", e);
        }
    }

    public List<BankAccountDetails> getAllBankAccount() {
        try {
            return bankAccountDetailsRepository.findAll();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to fetch bank account details", e);
        }
    }

    public BankAccountDetails getBankAccountDetailsById(String bankAccountDetailsId) {
        return bankAccountDetailsRepository.findById(bankAccountDetailsId).orElseThrow(() -> new BankAccountDetailsNotFoundException("Bank account details not found with Id " + bankAccountDetailsId));
    }

    public void deleteBankAccountDetailsById(String bankAccountDetailsId) {
        Optional<BankAccountDetails> existingBankAccountDetails = bankAccountDetailsRepository.findById(bankAccountDetailsId);

        if (existingBankAccountDetails.isPresent()) {
            try {
                bankAccountDetailsRepository.deleteById(bankAccountDetailsId);
            } catch (Exception e) {
                e.printStackTrace();
                throw new RuntimeException("Failed to delete the bank account details with ID: " + bankAccountDetailsId);
            }
        } else {
            throw new BankAccountDetailsNotFoundException("Bank Account Details not found with Id " + bankAccountDetailsId);
        }
    }

    public BankAccountDetails updateBankAccountDetails(String bankAccountDetailsId, BankAccountDetails bankAccountDetails) {
        BankAccountDetails existsBankAccountDetails = bankAccountDetailsRepository.findById(bankAccountDetailsId).orElseThrow(() -> new BankAccountDetailsNotFoundException("Bank account details not found with id " + bankAccountDetailsId));

        try {
            existsBankAccountDetails.setAccountNumber(bankAccountDetails.getAccountNumber());
            existsBankAccountDetails.setAccountHolderName((bankAccountDetails.getAccountHolderName()));
            existsBankAccountDetails.setBankName(bankAccountDetails.getBankName());
            existsBankAccountDetails.setBranchName(bankAccountDetails.getBranchName());
            existsBankAccountDetails.setBranchCode(bankAccountDetails.getBranchCode());
            existsBankAccountDetails.setUpdatedAt(LocalDateTime.now());
            existsBankAccountDetails.setUser(bankAccountDetails.getUser());

            return bankAccountDetailsRepository.save(existsBankAccountDetails);

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to update the bank account details for Id " + bankAccountDetailsId);
        }
    }
}
