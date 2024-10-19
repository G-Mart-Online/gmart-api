package com.gmart.gmart_api.dto.productDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateProductDto {
    private String description;
    private Integer stockQuantity;
    private float wholesalePrice;
    private float retailPrice;
    private List<String> imageUrls;
    private String videoUrl;
    private List<String> seoTags;
    private String supplier_Id;
    private String category_Id;
}
