package com.gmart.gmart_api.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.time.Instant;
import java.time.ZonedDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {

    @Id
    private String productId;
    private String description;
    private Integer stockQuantity;
    private float wholesalePrice;
    private float retailPrice;
    private List<String> imageUrls;
    private String videoUrl;
    private List<String> seoTags;
    private boolean status;
    private String supplier_Id;
    private String category_Id;
    private Instant created_at;
    private Instant last_updated_at;
}
