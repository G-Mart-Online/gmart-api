package com.gmart.gmart_api.service.impl;


import com.gmart.gmart_api.dto.productDto.GetProductDto;
import com.gmart.gmart_api.dto.productDto.ProductDto;
import com.gmart.gmart_api.model.Order;
import com.gmart.gmart_api.model.Product;
import org.springframework.data.domain.Page;

import java.util.List;

public interface IProductService {

    //create new product
    GetProductDto CreateNewProduct(ProductDto productDto);

    Page<GetProductDto> getPaginatedProducts(int pageNumber, int pageSize);

    List<GetProductDto> getAllProducts();

    void deleteProductById(String productId);



}