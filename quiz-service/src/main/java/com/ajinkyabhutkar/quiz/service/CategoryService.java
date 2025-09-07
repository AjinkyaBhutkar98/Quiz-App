package com.ajinkyabhutkar.quiz.service;

import com.ajinkyabhutkar.quiz.dto.CategoryDto;

import java.util.List;

public interface CategoryService {


    CategoryDto findById(Long categoryId);

    List<CategoryDto> findAll();

    CategoryDto create(CategoryDto categoryDto);

    CategoryDto update(String categoryId,CategoryDto categoryDto);

    void delete(String categoryId);

}
