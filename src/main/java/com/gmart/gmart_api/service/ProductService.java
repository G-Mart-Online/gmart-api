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
        Product product = modelMapper.map(productDto,Product.class);
        product.setCreated_at(ZonedDateTime.now());
        product.setLast_updated_at(ZonedDateTime.now());
        return product;
    }

    private GetProductDto ConvertToDto(Product product){
        GetProductDto newDto = modelMapper.map(product, GetProductDto.class);
        return newDto;
    }
}
