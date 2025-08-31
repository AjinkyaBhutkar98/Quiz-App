package com.ajinkyabhutkar.quiz.service.impl;

import com.ajinkyabhutkar.quiz.collections.Quiz;
import com.ajinkyabhutkar.quiz.dto.CategoryDto;
import com.ajinkyabhutkar.quiz.dto.QuizDto;
import com.ajinkyabhutkar.quiz.repo.QuizRepo;
import com.ajinkyabhutkar.quiz.service.QuizService;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.UUID;

@Service
public class QuizServiceImpl implements QuizService {

    private Logger logger= LoggerFactory.getLogger(QuizServiceImpl.class);

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private QuizRepo quizRepo;

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public QuizDto createQuiz(QuizDto quizDto) {
        Quiz quiz=modelMapper.map(quizDto, Quiz.class);
        quiz.setId(UUID.randomUUID().toString());

        String url="http://localhost:9091/api/v1/categories/"+quizDto.getCategoryId();
        logger.info("Category url : {}", url);
        //call to category service
        CategoryDto categoryDto=restTemplate.getForObject(url,CategoryDto.class);
        quizDto.setCategoryDto(categoryDto);

        Quiz savedQuiz=quizRepo.save(quiz);
        return modelMapper.map(savedQuiz,QuizDto.class);
    }

    @Override
    public QuizDto findById(String id) {
        Quiz quiz=quizRepo.findById(id).orElseThrow(()->new RuntimeException("Quiz Not Found"+id));
        QuizDto quizDto=modelMapper.map(quiz, QuizDto.class);
        //using resttemplate
        //get categoryid
        Long categoryId=quiz.getCategoryId();
        //define url
        String url="http://localhost:9091/api/v1/categories/"+categoryId;
        logger.info("Category url : {}", url);
        //call to category service
        CategoryDto categoryDto=restTemplate.getForObject(url,CategoryDto.class);
        quizDto.setCategoryDto(categoryDto);

        return quizDto;
    }

    @Override
    public List<QuizDto> getAllQuiz() {

        List<Quiz> allQuiz=quizRepo.findAll();
        return allQuiz.stream().map(quiz->modelMapper.map(quiz, QuizDto.class)).toList();
    }

    @Override
    public List<QuizDto> findByCategory(Long categoryId) {

        List<Quiz> quizByCategory=quizRepo.findByCategoryId(categoryId);
        return quizByCategory.stream().map(quiz -> modelMapper.map(quiz, QuizDto.class)).toList();
    }

    @Override
    public QuizDto updateQuiz(QuizDto quizDto, String id) {
        Quiz quiz=quizRepo.findById(id).orElseThrow(()->new RuntimeException("Quiz Not Found"+id));
        Quiz savedQuiz=quizRepo.save(modelMapper.map(quizDto,Quiz.class));

        return modelMapper.map(savedQuiz, QuizDto.class);
    }

    public void deleteQuiz(String id){
        Quiz quiz=quizRepo.findById(id).orElseThrow(()->new RuntimeException("Quiz Not Found"+id));
        quizRepo.delete(quiz);
    }
}
