package com.gmart.gmart_api.dto.productDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.time.ZonedDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {
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
}
