package com.gmart.gmart_api.dto.productCategoryDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateProdcutCategoryDto {
    private String categoryName;
    private String categoryDescription;
}
