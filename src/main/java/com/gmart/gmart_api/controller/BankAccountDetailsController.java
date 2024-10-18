package com.gmart.gmart_api.controller;

import com.gmart.gmart_api.model.BankAccountDetails;
import com.gmart.gmart_api.service.BankAccountDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/bankAccountDetails")
public class BankAccountDetailsController {

    private final BankAccountDetailService bankAccountDetailService;

    @Autowired
    public BankAccountDetailsController(BankAccountDetailService bankAccountDetailService) {
        this.bankAccountDetailService = bankAccountDetailService;
    }

    @Secured({"ROLE_ADMIN", "ROLE_SELLER"})
    @PostMapping
    public ResponseEntity<BankAccountDetails> createBankAccountDetails(@RequestBody BankAccountDetails bankAccountDetails) {
        BankAccountDetails savedBankAccountDetails = bankAccountDetailService.saveBankAccountDetails(bankAccountDetails);
        return new ResponseEntity<>(savedBankAccountDetails, HttpStatus.CREATED);
    }

    @Secured({"ROLE_ADMIN", "ROLE_SELLER"})
    @GetMapping("/list")
    public ResponseEntity<List<BankAccountDetails>> getAllBankAccountDetails() {
        List<BankAccountDetails> bankAccountDetailsList = bankAccountDetailService.getAllBankAccount();
        return new ResponseEntity<>(bankAccountDetailsList, HttpStatus.OK);
    }

    @Secured({"ROLE_ADMIN", "ROLE_SELLER"})
    @GetMapping("/{bankAccountDetailsId}")
    public ResponseEntity<BankAccountDetails> getBankAccountDetailsById(@PathVariable String bankAccountDetailsId) {
        BankAccountDetails bankAccountDetails = bankAccountDetailService.getBankAccountDetailsById(bankAccountDetailsId);

        if (bankAccountDetails != null) {
            return new ResponseEntity<>(bankAccountDetails, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Secured({"ROLE_ADMIN"})
    @DeleteMapping("/{bankAccountDetailsId}")
    public ResponseEntity<Void> deleteBankAccountDetails(@PathVariable String bankAccountDetailsId) {
        bankAccountDetailService.deleteBankAccountDetailsById(bankAccountDetailsId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Secured({"ROLE_ADMIN", "ROLE_SELLER"})
    @PutMapping("/{bankAccountDetailsId}")
    public ResponseEntity<BankAccountDetails> updateBankAccountDetails(@PathVariable String bankAccountDetailsId, @RequestBody BankAccountDetails bankAccountDetails) {
        BankAccountDetails updatedBankAccountDetails = bankAccountDetailService.updateBankAccountDetails(bankAccountDetailsId, bankAccountDetails);
        if (updatedBankAccountDetails != null) {
            return new ResponseEntity<>(updatedBankAccountDetails, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
