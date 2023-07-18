package com.geekbrains.shop.controllers;

import com.geekbrains.shop.dtos.CategoryDto;
import com.geekbrains.shop.entities.Category;
import com.geekbrains.shop.services.CategoryService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.websocket.server.PathParam;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/categories")
@SecurityRequirement(name = "Bearer Auth")
@Tag(name = "Categories")
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping
    public List<CategoryDto> findAllCategories() {
        return categoryService.findAllCategory().stream()
                .map(c -> new CategoryDto(c.getId(), c.getTitle())).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public CategoryDto findCategoryById(@PathVariable Long id) {
        Category category = categoryService.findCategoryById(id).orElseThrow();
        return new CategoryDto(category.getId(), category.getTitle());
    }

    @PostMapping
    public Category createNewCategory(@RequestBody Category category) {
        return categoryService.save(category);
    }

    @PutMapping("/{id}")
    public void updateCategory(@PathVariable Long id,@RequestBody CategoryDto categoryDto){
        Category category = new Category();
        category.setId(id);
        category.setTitle(categoryDto.getTitle());
        categoryService.save(category);
    }

    @DeleteMapping("/{id}")
    public void deleteCategoryById(@PathVariable Long id) {
        //TODO  если не пустая то подумать как сохранить товары внутри
        categoryService.delete(id);
    }
}
