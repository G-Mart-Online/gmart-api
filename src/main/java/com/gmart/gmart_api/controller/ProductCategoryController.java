package com.gmart.gmart_api.controller;


import com.gmart.gmart_api.config.Response;
import com.gmart.gmart_api.dto.productCategoryDto.GetProductCategoryDto;
import com.gmart.gmart_api.dto.productCategoryDto.ProductCategoryDto;
import com.gmart.gmart_api.dto.productCategoryDto.UpdateProdcutCategoryDto;
import com.gmart.gmart_api.dto.productDto.GetProductDto;
import com.gmart.gmart_api.dto.productDto.ProductDto;
import com.gmart.gmart_api.dto.productDto.UpdateProductDto;
import com.gmart.gmart_api.service.impl.IProductCategoryService;
import com.gmart.gmart_api.service.impl.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/categories")
public class ProductCategoryController extends AbstractController {

    private final IProductCategoryService productCategoryService;

    @Autowired
    public ProductCategoryController(IProductCategoryService productCategoryService) {
        this.productCategoryService = productCategoryService;
    }

    @PostMapping()
    public ResponseEntity<GetProductCategoryDto> AddNewCategory(@RequestBody ProductCategoryDto productCategoryDto) {

        GetProductCategoryDto createCategory = productCategoryService.CreateNewCategory(productCategoryDto);
        return new ResponseEntity<>(createCategory, HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<?> getAllCategories(
            @RequestParam(required = false) Integer pageNumber,
            @RequestParam(defaultValue = "10") int pageSize) {

        if (pageNumber == null) {

            List<GetProductCategoryDto> allCategories = productCategoryService.getAllProductCategories();
            return ResponseEntity.ok(allCategories);
        } else {
            // If a page number is provided, return paginated products
            Page<GetProductCategoryDto> paginatedCategories = productCategoryService.getPaginatedProductCategories(pageNumber, pageSize);
            return ResponseEntity.ok(paginatedCategories);
        }
    }

    @DeleteMapping("/{categoryId}")
    public ResponseEntity<Response> deleteCategoryById(@PathVariable("categoryId") String categoryId) {

        productCategoryService.deleteCategoryById(categoryId);
        return createDeleteSuccessResponse();

    }


    @PutMapping("/{productId}")
    public ResponseEntity<GetProductCategoryDto> updateCategoryById(
            @PathVariable("categoryId") String categoryId,
            @RequestBody UpdateProdcutCategoryDto updateDto) {

        GetProductCategoryDto updatedProduct = productCategoryService.updateProductCategory(categoryId, updateDto);
        return ResponseEntity.ok(updatedProduct);
    }


}
