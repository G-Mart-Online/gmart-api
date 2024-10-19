package com.gmart.gmart_api.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "order")
public class Order {

    @Id
    private String orderId;

    private Seller seller;

    private Supplier supplier;

    private List<OrderItem> orderItems;

    private Address orderAddress;

    private DeliveryType deliveryType;

    private String contactNumberOne;

    private String contactNumberTwo;

    private String placedDate;

    private String updatedDate;

    private String deliveredDate;

    private String trackingId;

    private String status;

    private String totalPrice;

}
