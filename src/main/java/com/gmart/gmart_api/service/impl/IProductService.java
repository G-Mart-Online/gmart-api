package com.gmart.gmart_api.service.impl;


import com.gmart.gmart_api.dto.productDto.GetProductDto;
import com.gmart.gmart_api.dto.productDto.ProductDto;
import com.gmart.gmart_api.model.Order;
import com.gmart.gmart_api.model.Product;

public interface IProductService {

    //create new product
    GetProductDto CreateNewProduct(ProductDto productDto);

}