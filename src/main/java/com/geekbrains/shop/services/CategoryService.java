package com.geekbrains.shop.services;

import com.geekbrains.shop.entities.Category;
import com.geekbrains.shop.repositories.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public Optional<Category> findCategoryByTitle(String title) {

        return categoryRepository.findByTitle(title);
    }

}
