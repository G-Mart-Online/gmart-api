package com.gmart.gmart_api.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "address")
public class Address {

    @Id
    private String addressId;

    private String address1;

    private String address2;

    private String suburb;

    private String city;

    private String postalCode;

    private String province;

    private String lastUpdateDate;
}
