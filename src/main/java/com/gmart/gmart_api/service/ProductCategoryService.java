package com.gmart.gmart_api.service;

import com.gmart.gmart_api.dto.productCategoryDto.GetProductCategoryDto;
import com.gmart.gmart_api.dto.productCategoryDto.ProductCategoryDto;
import com.gmart.gmart_api.dto.productCategoryDto.UpdateProdcutCategoryDto;
import com.gmart.gmart_api.dto.productDto.GetProductDto;
import com.gmart.gmart_api.dto.productDto.ProductDto;
import com.gmart.gmart_api.dto.productDto.UpdateProductDto;
import com.gmart.gmart_api.model.Product;
import com.gmart.gmart_api.model.ProductCategory;
import com.gmart.gmart_api.repository.ProductCategoryRepository;
import com.gmart.gmart_api.service.impl.IProductCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductCategoryService implements IProductCategoryService {
    private final ProductCategoryRepository productCategoryRepository;

    @Autowired
    public ProductCategoryService(ProductCategoryRepository productCategoryRepository) {
        this.productCategoryRepository = productCategoryRepository;
    }

    @Override
    public GetProductCategoryDto CreateNewCategory(ProductCategoryDto categoryDto) {
        ProductCategory newCategory = ConvertToEntity(categoryDto);
        ProductCategory savedProduct = productCategoryRepository.save(newCategory);
        return ConvertToDto(savedProduct);
    }

    @Override
    // Method to get all products without pagination
    public List<GetProductCategoryDto> getAllProductCategories() {
        List<ProductCategory> categories = productCategoryRepository.findAll();
        return categories.stream()
                .map(this::ConvertToDto)
                .collect(Collectors.toList());
    }

    @Override
    // Method to get paginated products
    public Page<GetProductCategoryDto> getPaginatedProductCategories(int pageNumber, int pageSize) {
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize);
        Page<ProductCategory> paginatedProducts = productCategoryRepository.findAll(pageRequest);
        return paginatedProducts.map(this::ConvertToDto);
    }

    @Override
    public void deleteCategoryById(String categoryId) {
        if (productCategoryRepository.existsById(categoryId)) {
            productCategoryRepository.deleteById(categoryId);
        } else {
            throw new IllegalArgumentException("Category with ID " + categoryId + " does not exist.");
        }
    }

    @Override
    public GetProductCategoryDto updateProductCategory(String categoryId, UpdateProdcutCategoryDto dto) {

        Optional<ProductCategory> categoryOptional = productCategoryRepository.findById(categoryId);
        if (!categoryOptional.isPresent()) {
            throw new IllegalArgumentException("Product with ID " + categoryId + " not found.");
        }


        ProductCategory category = categoryOptional.get();
        category.setCategoryDescription(dto.getCategoryDescription());
        category.setCategoryName(dto.getCategoryName());
        category.setLast_updated_at(Instant.now());


        ProductCategory updatedProductCategory = productCategoryRepository.save(category);


        return ConvertToDto(updatedProductCategory);
    }








    private ProductCategory ConvertToEntity(ProductCategoryDto dto){
        ProductCategory category = new ProductCategory();
        category.setCategoryName(dto.getCategoryName());
        category.setCategoryDescription(dto.getCategoryDescription());
        category.setCreated_at(Instant.now());
        category.setLast_updated_at(Instant.now());
        return category;
    }

    private GetProductCategoryDto ConvertToDto(ProductCategory category){
        GetProductCategoryDto newDto = new GetProductCategoryDto();
        newDto.setCategoryId(category.getCategoryId());
        newDto.setCategoryName(category.getCategoryName());
        newDto.setCategoryDescription(category.getCategoryDescription());
        return newDto;
    }




}
