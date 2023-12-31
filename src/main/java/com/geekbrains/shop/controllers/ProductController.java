package com.geekbrains.shop.controllers;

import com.geekbrains.shop.converters.ProductConverter;
import com.geekbrains.shop.dtos.product.ProductDto;
import com.geekbrains.shop.entities.Category;
import com.geekbrains.shop.entities.Product;
import com.geekbrains.shop.services.CategoryService;
import com.geekbrains.shop.services.ProductService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/products")
@SecurityRequirement(name="Bearer Auth")
@Tag(name="Products")
public class ProductController {

    private final ProductService productService;
    private final ProductConverter productConverter;
    private final CategoryService categoryService;

    @GetMapping
    public List<ProductDto> findAllProducts() {

        List<ProductDto> products = productService.findAllProducts().stream()
                .map(productConverter::entityToDto)
                .toList();

        return products;
    }

    @GetMapping("/{id}")
    public ProductDto findProductById(@PathVariable Long id) {

        Product product = productService.findProductById(id).orElseThrow();
        ProductDto productDto = productConverter.entityToDto(product);

        return productDto;
    }

    @PostMapping
    public void createNewProduct(@RequestBody ProductDto productDto) {

        Product product = productConverter.dtoToEntity(productDto);
        productService.save(product);
    }

    @GetMapping("/delete/{id}")
    public void deleteProductById(@PathVariable Long id) {

        productService.delete(id);
    }

    @PutMapping
    public void updateProduct(@RequestBody ProductDto productDto) {

        productService.update(productDto);
    }

    @GetMapping("/byCategory")
    public List<ProductDto> findAllProductsByCategory(@RequestParam String title) {

        Category category = categoryService.findCategoryByTitle(title).orElseThrow();
        List<ProductDto> products = category.getProducts().stream().map(productConverter::entityToDto).toList();

        return products;
    }

}
