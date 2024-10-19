package com.gmart.gmart_api.dto.productCategoryDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetProductCategoryDto {
    private String categoryId;
    private String categoryName;
    private String categoryDescription;
}
