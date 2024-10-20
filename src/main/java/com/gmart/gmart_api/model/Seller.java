package com.gmart.gmart_api.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "sellers")
public class Seller extends User {
    private String description;
    private String sellerStatus;

    @CreatedDate
    private LocalDateTime sellerCreatedDate;

    @LastModifiedDate
    private LocalDateTime sellerUpdatedDate;
}
