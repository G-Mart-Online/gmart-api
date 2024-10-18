package com.gmart.gmart_api.controller;

import com.gmart.gmart_api.dto.UserDto;
import com.gmart.gmart_api.dto.productDto.GetProductDto;
import com.gmart.gmart_api.dto.productDto.ProductDto;
import com.gmart.gmart_api.service.impl.IProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequestMapping("api/v1/products")
public class ProductController {

    private final IProductService productService;

    @Autowired
    public ProductController(IProductService productService) {
        this.productService = productService;
    }

    @PostMapping()
    public ResponseEntity<GetProductDto> AddNewProduct(@RequestBody ProductDto productDto) {

        GetProductDto createProduct = productService.CreateNewProduct(productDto);
        return new ResponseEntity<>(createProduct, HttpStatus.OK);
    }
}
