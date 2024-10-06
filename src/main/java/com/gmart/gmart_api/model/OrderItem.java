package com.gmart.gmart_api.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "order_item")
public class OrderItem {

    @Id
    private String orderItemId;

    private Product product;

    private String quantity;

    private String totalPrice;
}
