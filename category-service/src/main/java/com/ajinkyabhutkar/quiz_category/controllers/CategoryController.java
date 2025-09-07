package com.ajinkyabhutkar.quiz_category.controllers;

import com.ajinkyabhutkar.quiz_category.dtos.ApiResponse;
import com.ajinkyabhutkar.quiz_category.dtos.CategoryDto;
import com.ajinkyabhutkar.quiz_category.entities.Category;
import com.ajinkyabhutkar.quiz_category.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;


    //create category
    @PostMapping
    public ResponseEntity<CategoryDto> createCategory(@RequestBody CategoryDto categoryDto){

        CategoryDto categoryDto1=categoryService.createCategory(categoryDto);

        return new ResponseEntity<>(categoryDto1, HttpStatus.CREATED);
    }

    //get category by id
    @GetMapping("/{id}")
    public ResponseEntity<CategoryDto> getCategory(@PathVariable Long id){

        CategoryDto categoryDto1=categoryService.getCategoryById(id);

        return new ResponseEntity<>(categoryDto1, HttpStatus.OK);
    }

    //get all categories
    @GetMapping
    public ResponseEntity<List<CategoryDto>> getAllCategories(){

        List<CategoryDto> categoryDtoList=categoryService.getAllCategories();

        return new ResponseEntity<>(categoryDtoList, HttpStatus.OK);
    }

    //update category
    @PutMapping("/{id}")
    public ResponseEntity<CategoryDto> updateCategory(@RequestBody CategoryDto categoryDto,Long id){

        CategoryDto updatedCategoryDto=categoryService.updateCategory(categoryDto,id);

        return new ResponseEntity<>(updatedCategoryDto, HttpStatus.OK);
    }

    //delete category
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteCategory(@PathVariable Long id){

        categoryService.deleteCategory(id);

        ApiResponse apiResponse=new ApiResponse("Category deleted successfully",true,HttpStatus.OK);

        return new ResponseEntity<>(apiResponse,HttpStatus.OK);
    }
}
