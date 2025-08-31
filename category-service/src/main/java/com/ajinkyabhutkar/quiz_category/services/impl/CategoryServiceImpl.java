package com.ajinkyabhutkar.quiz_category.services.impl;

import com.ajinkyabhutkar.quiz_category.dtos.CategoryDto;
import com.ajinkyabhutkar.quiz_category.entities.Category;
import com.ajinkyabhutkar.quiz_category.repo.CategoryRepo;
import com.ajinkyabhutkar.quiz_category.services.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    private ModelMapper modelMapper;

    private CategoryRepo categoryRepo;

    @Autowired
    public CategoryServiceImpl(ModelMapper modelMapper, CategoryRepo categoryRepo) {
        this.modelMapper = modelMapper;
        this.categoryRepo = categoryRepo;
    }

    @Override
    public CategoryDto createCategory(CategoryDto categoryDto) {

       Category category=modelMapper.map(categoryDto,Category.class);

       categoryRepo.save(category);

       return modelMapper.map(category,CategoryDto.class);
    }

    @Override
    public CategoryDto getCategoryById(Long categoryId) {

        Category category=categoryRepo.findById(categoryId).orElseThrow(()->new RuntimeException("Category Not Found!! :"+categoryId));

        return modelMapper.map(category,CategoryDto.class);


    }

    @Override
    public List<CategoryDto> getAllCategories() {

        List<Category> categoryList=categoryRepo.findAll();
        return categoryList.stream().map(category->modelMapper.map(category,CategoryDto.class)).toList();
    }

    @Override
    public CategoryDto updateCategory(CategoryDto categoryDto, Long categoryId) {
        Category category=categoryRepo.findById(categoryId).orElseThrow(()->new RuntimeException("Category Not Found!! :"+categoryId));

        category=modelMapper.map(categoryDto,Category.class);
        Category updatedCategory=categoryRepo.save(category);
        return modelMapper.map(updatedCategory,CategoryDto.class);
    }

    @Override
    public void deleteCategory(Long categoryId) {
        Category category=categoryRepo.findById(categoryId).orElseThrow(()->new RuntimeException("Category Not Found!! :"+categoryId));
        categoryRepo.delete(category);
    }
}
