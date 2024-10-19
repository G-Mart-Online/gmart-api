package com.gmart.gmart_api.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "delivery_type")
public class DeliveryType {

    @Id
    private String deliveryTypeId;

    private String deliveryService;

    private String trackingSiteUrl;

    private String description;

    private String lastUpdatedDate;
}
