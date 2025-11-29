package com.ajinkyabhutkar.questiongenerator.service.impl;

import com.ajinkyabhutkar.questiongenerator.collections.Quiz;
import com.ajinkyabhutkar.questiongenerator.dto.CategoryDto;
import com.ajinkyabhutkar.questiongenerator.dto.QuizDto;
import com.ajinkyabhutkar.questiongenerator.repo.QuizRepo;
import com.ajinkyabhutkar.questiongenerator.service.CategoryFeignService;
import com.ajinkyabhutkar.questiongenerator.service.CategoryService;
import com.ajinkyabhutkar.questiongenerator.service.QuizService;

import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;

import java.util.List;
import java.util.UUID;

@Slf4j
@Service
public class QuizServiceImpl implements QuizService {

    private Logger logger = LoggerFactory.getLogger(QuizServiceImpl.class);

    private ModelMapper modelMapper;

    private QuizRepo quizRepo;

    private RestTemplate restTemplate;

    private CategoryService categoryService;

    private CategoryFeignService categoryFeignService;

    private StreamBridge streamBridge;

    @Autowired
    public QuizServiceImpl(ModelMapper modelMapper, QuizRepo quizRepo, RestTemplate restTemplate, CategoryService categoryService, CategoryFeignService categoryFeignService, StreamBridge streamBridge) {

        this.modelMapper = modelMapper;
        this.quizRepo = quizRepo;
        this.restTemplate = restTemplate;
        this.categoryService = categoryService;
        this.categoryFeignService = categoryFeignService;
        this.streamBridge = streamBridge;
    }

    @Override
    public QuizDto createQuiz(QuizDto quizDto) {
        Quiz quiz = modelMapper.map(quizDto, Quiz.class);
        quiz.setId(UUID.randomUUID().toString());

        String url = "lb://CATEGORY-SERVICE/api/v1/categories/" + quizDto.getCategoryId();
        logger.info("Category url : {}", url);
        //call to category service
        CategoryDto categoryDto = restTemplate.getForObject(url, CategoryDto.class);
        quizDto.setCategoryDto(categoryDto);

        Quiz savedQuiz = quizRepo.save(quiz);
        //after quiz created we will publish a msg to the broker
        QuizDto newQuiz=modelMapper.map(savedQuiz, QuizDto.class);
        publishQuizCreatedEvent(newQuiz);
        return newQuiz;



    }

    public void publishQuizCreatedEvent(QuizDto quizDto) {

        logger.info("publish quiz created event:");

        Message<QuizDto> message = MessageBuilder
                .withPayload(quizDto)
                .setHeader("Content-Type", "application/json")  // VERY IMPORTANT
                .build();

        boolean success = streamBridge.send("quizCreatedBinding-out-0", message);

        if (success) {
            logger.info("event is sent to broker");
        } else {
            logger.info("event is not sent to broker");
        }
    }

    @Override
    public QuizDto findById(String id) {
        Quiz quiz = quizRepo.findById(id).orElseThrow(() -> new RuntimeException("Quiz Not Found" + id));
        QuizDto quizDto = modelMapper.map(quiz, QuizDto.class);
        //using resttemplate
        //get categoryid
        Long categoryId = quiz.getCategoryId();
        //define url
        String url = "lb://CATEGORY-SERVICE/api/v1/categories/" + categoryId;
        logger.info("Category url : {}", url);
        //call to category service
        CategoryDto categoryDto = restTemplate.getForObject(url, CategoryDto.class);
        quizDto.setCategoryDto(categoryDto);

        return quizDto;
    }

    public List<QuizDto> getAllQuiz() {
        List<Quiz> all = quizRepo.findAll();
        // getting category of all quiz
        logger.info("get all quizzes");
        return all.stream().map(quiz -> {
            Long categoryId = quiz.getCategoryId();
            QuizDto quizDto = modelMapper.map(quiz, QuizDto.class);
            //call to quiz service using webclient
            CategoryDto categoryDto = this.categoryService.findById(categoryId);
            quizDto.setCategoryDto(categoryDto);
            return quizDto;
        }).toList();
    }



    @Override
    public List<QuizDto> findByCategory(Long categoryId) {

        List<Quiz> quizByCategory = quizRepo.findByCategoryId(categoryId);
        return quizByCategory.stream().map(quiz -> {
            QuizDto quizDto=modelMapper.map(quiz,QuizDto.class);

            CategoryDto categoryDto=categoryFeignService.getSingleCategory(quizDto.getCategoryId());

            quizDto.setCategoryDto(categoryDto);
            return quizDto;
        }).toList();
    }

    @Override
    public QuizDto updateQuiz(QuizDto quizDto, String id) {
        Quiz savedQuiz = quizRepo.save(modelMapper.map(quizDto, Quiz.class));

        return modelMapper.map(savedQuiz, QuizDto.class);
    }

    public void deleteQuiz(String id) {
        Quiz quiz = quizRepo.findById(id).orElseThrow(() -> new RuntimeException("Quiz Not Found" + id));
        quizRepo.delete(quiz);
    }
}
