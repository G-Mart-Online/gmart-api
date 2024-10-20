package com.gmart.gmart_api.service.impl;

import com.gmart.gmart_api.dto.productCategoryDto.GetProductCategoryDto;
import com.gmart.gmart_api.dto.productCategoryDto.ProductCategoryDto;
import com.gmart.gmart_api.dto.productCategoryDto.UpdateProdcutCategoryDto;
import org.springframework.data.domain.Page;

import java.util.List;

public interface IProductCategoryService{
    GetProductCategoryDto CreateNewCategory(ProductCategoryDto categoryDto);

    List<GetProductCategoryDto> getAllProductCategories();

    Page<GetProductCategoryDto> getPaginatedProductCategories(int pageNumber, int pageSize);

    void deleteCategoryById(String categoryId);

    GetProductCategoryDto updateProductCategory(String categoryId, UpdateProdcutCategoryDto dto);



}
