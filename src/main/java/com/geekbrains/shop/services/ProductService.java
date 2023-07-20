package com.geekbrains.shop.services;

import com.geekbrains.shop.converters.ProductConverter;
import com.geekbrains.shop.dtos.product.ProductDto;
import com.geekbrains.shop.entities.Product;
import com.geekbrains.shop.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductConverter productConverter;

    public List<Product> findAllProducts() {
        return productRepository.findAll();
    }

    public Optional<Product> findProductById(Long id) {
        return productRepository.findById(id);
    }

    public void save(Product product) {
        productRepository.save(product);
    }

    public void delete(Long id) {
        productRepository.deleteById(id);
    }

    @Transactional
    public void update(ProductDto productDto) {
        Product product = findProductById(productDto.getId()).orElseThrow();
        product.setPrice(product.getPrice());
    }

}
