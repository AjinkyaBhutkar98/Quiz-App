package com.ajinkyabhutkar.questiongenerator.service.impl;

import com.ajinkyabhutkar.questiongenerator.dto.CategoryDto;
import com.ajinkyabhutkar.questiongenerator.repo.QuizRepo;
import com.ajinkyabhutkar.questiongenerator.service.CategoryService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    private Logger logger = LoggerFactory.getLogger(QuizServiceImpl.class);
    private ModelMapper modelMapper;
    private QuizRepo quizRepo;
    private WebClient.Builder webClient;

    @Autowired
    public CategoryServiceImpl(ModelMapper modelMapper, QuizRepo quizRepo, WebClient.Builder webClient) {
        this.modelMapper = modelMapper;
        this.quizRepo = quizRepo;
        this.webClient = webClient.baseUrl("http://CATEGORY-SERVICE");
    }

    @Override
    public CategoryDto findById(Long categoryId) {
        try {
            CategoryDto categoryDto = webClient.build()
                    //get is just a request method
                    .get()
                    //uri to send requestn on baseurl+ uri
                    .uri("/api/v1/categories/{categoryId}", categoryId)
                    //retrieve means get data
                    .retrieve()
                    //mono means a single object
                    .bodyToMono(CategoryDto.class)
                    //blocking request- remove block() for nonblocking if you want to use nonblocking request
                    .block();

            System.out.println(categoryDto);
            logger.info("returning category dtos");
            return categoryDto;


        } catch (WebClientResponseException ex) {
            logger.error("category not found");
//                        if(ex.getStatusCode()== HttpStatusCode.valueOf(404)){
//                            logger.error("category not found");
//                        }

            ex.printStackTrace();
        }

        return null;

    }

    @Override
    public List<CategoryDto> findAll() {

       logger.info("in get all method for category service");
        return this.webClient.build()
                .get()
                .uri("/api/v1/categories")
                .retrieve()
                //body to flux means multiple object in response
                .bodyToFlux(CategoryDto.class)
                .collectList()
                .block();
    }

    @Override
    public CategoryDto create(CategoryDto categoryDto) {

        return this.webClient.build()
                .post()
                .uri("api/v1/categories")
                .bodyValue(categoryDto)
                .retrieve()
                .bodyToMono(CategoryDto.class)
                .block();

    }

    @Override
    public CategoryDto update(String categoryId, CategoryDto categoryDto) {
        return this.webClient.build()
                .put()
                .uri("api/v1/categories/{categoryId}", categoryId)
                .bodyValue(categoryDto)
                .retrieve()
                .bodyToMono(CategoryDto.class)
                .block();
    }

    @Override
    public void delete(String categoryId) {

        this.webClient.build()
                .delete()
                .uri("api/v1/categories/{categoryId}", categoryId)
                .retrieve()
                .toBodilessEntity()
                .block();
    }
}
