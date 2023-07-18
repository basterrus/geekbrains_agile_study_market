package com.geekbrains.shop.services;

import com.geekbrains.shop.entities.Category;
import com.geekbrains.shop.repositories.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public List<Category> findAllCategory(){
        return categoryRepository.findAll();
    }

    public Optional<Category> findCategoryByTitle(String title) {

        return categoryRepository.findByTitle(title);
    }

    public Optional<Category> findCategoryById(Long id){
        return categoryRepository.findById(id);
    }

    public Category save(Category category) {
        categoryRepository.save(category);
        return category;
    }

    public void delete(Long id) {
        categoryRepository.deleteById(id);
    }
}
