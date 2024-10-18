package com.gmart.gmart_api.service;

import com.gmart.gmart_api.dto.UserDto;
import com.gmart.gmart_api.dto.productDto.GetProductDto;
import com.gmart.gmart_api.dto.productDto.ProductDto;
import com.gmart.gmart_api.model.Product;
import com.gmart.gmart_api.repository.ProductRepository;
import com.gmart.gmart_api.service.impl.IProductService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.ZonedDateTime;

@Service
public class ProductService implements IProductService {

    private final ProductRepository productRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public ProductService(ProductRepository productRepository, ModelMapper modelMapper) {
        this.productRepository = productRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public GetProductDto CreateNewProduct(ProductDto productDto) {
        Product newProduct = ConvertToEntity(productDto);
        Product savedProduct = productRepository.save(newProduct);
        return ConvertToDto(savedProduct);
    }

    private Product ConvertToEntity(ProductDto productDto){
        Product product = new Product();
        product.setDescription(productDto.getDescription());
        product.setStockQuantity(productDto.getStockQuantity());
        product.setWholesalePrice(productDto.getWholesalePrice());
        product.setRetailPrice(productDto.getRetailPrice());
        product.setImageUrls(productDto.getImageUrls());
        product.setVideoUrl(productDto.getVideoUrl());
        product.setSeoTags(productDto.getSeoTags());
        product.setStatus(false);
        product.setSupplier_Id(productDto.getSupplier_Id());
        product.setCategory_Id(productDto.getCategory_Id());
        product.setCreated_at(Instant.now());
        product.setLast_updated_at(Instant.now());
        return product;
    }

    private GetProductDto ConvertToDto(Product product){
        GetProductDto newDto = new GetProductDto();
        newDto.setProductId(product.getProductId());
        newDto.setDescription(product.getDescription());
        newDto.setStockQuantity(product.getStockQuantity());
        newDto.setWholesalePrice(product.getWholesalePrice());
        newDto.setRetailPrice(product.getRetailPrice());
        newDto.setImageUrls(product.getImageUrls());
        newDto.setVideoUrl(product.getVideoUrl());
        newDto.setSeoTags(product.getSeoTags());
        newDto.setStatus(product.isStatus());
        newDto.setSupplier_Id(product.getSupplier_Id());
        newDto.setCategory_Id(product.getCategory_Id());
        return newDto;
    }
}
