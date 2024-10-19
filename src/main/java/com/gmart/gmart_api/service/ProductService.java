package com.gmart.gmart_api.service;

import com.gmart.gmart_api.dto.productDto.GetProductDto;
import com.gmart.gmart_api.dto.productDto.ProductDto;
import com.gmart.gmart_api.dto.productDto.UpdateProductDto;
import com.gmart.gmart_api.model.Product;
import com.gmart.gmart_api.repository.ProductRepository;
import com.gmart.gmart_api.service.impl.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductService implements IProductService {

    private final ProductRepository productRepository;


    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public GetProductDto CreateNewProduct(ProductDto productDto) {
        Product newProduct = ConvertToEntity(productDto);
        Product savedProduct = productRepository.save(newProduct);
        return ConvertToDto(savedProduct);
    }

    @Override
    // Method to get all products without pagination
    public List<GetProductDto> getAllProducts() {
        List<Product> products = productRepository.findAll();
        return products.stream()
                .map(this::ConvertToDto)
                .collect(Collectors.toList());
    }

    @Override
    // Method to get paginated products
    public Page<GetProductDto> getPaginatedProducts(int pageNumber, int pageSize) {
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize);
        Page<Product> paginatedProducts = productRepository.findAll(pageRequest);
        return paginatedProducts.map(this::ConvertToDto);
    }

    @Override
    public void deleteProductById(String productId) {
        if (productRepository.existsById(productId)) {
            productRepository.deleteById(productId);
        } else {
            throw new IllegalArgumentException("Product with ID " + productId + " does not exist.");
        }
    }

    // Update product by ID
    @Override
    public GetProductDto updateProduct(String productId, UpdateProductDto productDto) {
        // Find the existing product by ID
        Optional<Product> productOptional = productRepository.findById(productId);
        if (!productOptional.isPresent()) {
            throw new IllegalArgumentException("Product with ID " + productId + " not found.");
        }

        // Update the product details
        Product product = productOptional.get();
        product.setDescription(productDto.getDescription());
        product.setStockQuantity(productDto.getStockQuantity());
        product.setWholesalePrice(productDto.getWholesalePrice());
        product.setRetailPrice(productDto.getRetailPrice());
        product.setImageUrls(productDto.getImageUrls());
        product.setVideoUrl(productDto.getVideoUrl());
        product.setSeoTags(productDto.getSeoTags());
        product.setSupplier_Id(productDto.getSupplier_Id());
        product.setCategory_Id(productDto.getCategory_Id());
        product.setLast_updated_at(Instant.now());

        // Save the updated product to the database
        Product updatedProduct = productRepository.save(product);

        // Convert the updated product entity to DTO and return
        return ConvertToDto(updatedProduct);
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
