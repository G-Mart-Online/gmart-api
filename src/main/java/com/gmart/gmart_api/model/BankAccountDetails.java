package com.gmart.gmart_api.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "bank_account_details")
public class BankAccountDetails {

    @Id
    private String Id;

    private String accountNumber;

    private String accountHolderName;

    private String bankName;

    private String branchName;

    private String branchCode;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private User user;

}
