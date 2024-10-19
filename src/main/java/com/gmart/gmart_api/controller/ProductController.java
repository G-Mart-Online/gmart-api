package com.gmart.gmart_api.controller;

import com.gmart.gmart_api.config.Response;
import com.gmart.gmart_api.dto.productDto.GetProductDto;
import com.gmart.gmart_api.dto.productDto.ProductDto;
import com.gmart.gmart_api.dto.productDto.UpdateProductDto;
import com.gmart.gmart_api.service.impl.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/products")
public class ProductController extends AbstractController{

    private final IProductService productService;

    @Autowired
    public ProductController(IProductService productService) {
        this.productService = productService;
    }

    //@Secured({"ROLE_ADMIN", "ROLE_SELLER"})
    @PostMapping()
    public ResponseEntity<GetProductDto> AddNewProduct(@RequestBody ProductDto productDto) {

        GetProductDto createProduct = productService.CreateNewProduct(productDto);
        return new ResponseEntity<>(createProduct, HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<?> getAllProducts(
            @RequestParam(required = false) Integer pageNumber, // Optional pageNumber
            @RequestParam(defaultValue = "10") int pageSize) {  // Default pageSize to 10

        if (pageNumber == null) {
            // If no page number is provided, return all products
            List<GetProductDto> allProducts = productService.getAllProducts();
            return ResponseEntity.ok(allProducts);
        } else {
            // If a page number is provided, return paginated products
            Page<GetProductDto> paginatedProducts = productService.getPaginatedProducts(pageNumber, pageSize);
            return ResponseEntity.ok(paginatedProducts);
        }
    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<Response> deleteProduct(@PathVariable("productId") String productId) {

        productService.deleteProductById(productId);
        return createDeleteSuccessResponse();

    }

    // Update product by ID
    @PutMapping("/{productId}")
    public ResponseEntity<GetProductDto> updateProduct(
            @PathVariable("productId") String productId,
            @RequestBody UpdateProductDto productDto) {

            GetProductDto updatedProduct = productService.updateProduct(productId, productDto);
            return ResponseEntity.ok(updatedProduct);
    }
}
