package com.geekbrains.shop.converters;

import com.geekbrains.shop.dtos.product.ProductDto;
import com.geekbrains.shop.entities.Category;
import com.geekbrains.shop.entities.Product;
import com.geekbrains.shop.services.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProductConverter {

    private final CategoryService categoryService;

    public ProductDto entityToDto(Product product) {

        ProductDto productDto = new ProductDto();
        productDto.setId(product.getId());
        productDto.setTitle(product.getTitle());
        productDto.setPrice(product.getPrice());
        productDto.setCategoryTitle(product.getCategory().getTitle());

        return productDto;
    }

    public Product dtoToEntity(ProductDto productDto) {

        Category category = categoryService.findCategoryByTitle(productDto.getCategoryTitle()).orElseThrow();

        Product product = new Product();
        product.setId(productDto.getId());
        product.setTitle(productDto.getTitle());
        product.setPrice(productDto.getPrice());
        product.setCategory(category);

        return product;
    }

}
