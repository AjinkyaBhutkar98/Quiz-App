package com.ajinkyabhutkar.quiz.service;

import com.ajinkyabhutkar.quiz.dto.CategoryDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name = "CATEGORY-SERVICE")
public interface CategoryFeignService {

    //get all category
    @GetMapping("/api/v1/categories")
    List<CategoryDto> findAll();

    //get single category
    @GetMapping("/api/v1/categories/{id}")
    CategoryDto getSingleCategory(@PathVariable Long id);

    //create cateogry
    @PostMapping
    CategoryDto createCategory(@RequestBody CategoryDto categoryDto);
}
