package com.ajinkyabhutkar.quiz_category.services;

import com.ajinkyabhutkar.quiz_category.dtos.CategoryDto;

import java.util.List;

public interface CategoryService {

    public CategoryDto createCategory(CategoryDto categoryDto);

    public CategoryDto getCategoryById(Long categoryId);

    public List<CategoryDto> getAllCategories();

    public CategoryDto updateCategory(CategoryDto categoryDto,Long id);

    public void deleteCategory(Long categoryId);


}
