package com.ajinkyabhutkar.quiz;

import com.ajinkyabhutkar.quiz.dto.CategoryDto;
import com.ajinkyabhutkar.quiz.service.CategoryFeignService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class QuizServiceApplicationTests {


	@Autowired
	private CategoryFeignService categoryFeignService;

	@Test
	public void testFeignClientForAllCategories(){
		System.out.println("getting all categories");
		List<CategoryDto> allCategories=categoryFeignService.findALl();

		allCategories.forEach(categoryDto -> System.out.println(categoryDto.getTitle()));

//		Assertions.assertEquals(3,allCategories.size());
		Assertions.assertNotNull(allCategories); // assure the result is not null
	}

	@Test
	public void getSingleCategoryWithFeignClient(){
		System.out.println("Getting single category");
		CategoryDto categoryDto=categoryFeignService.getSingleCategory(1L);
		System.out.println(categoryDto.getTitle());
		Assertions.assertNotNull(categoryDto);


	}

}
