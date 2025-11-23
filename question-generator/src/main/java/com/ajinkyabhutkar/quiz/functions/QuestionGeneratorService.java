package com.ajinkyabhutkar.quiz.functions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Consumer;


@Configuration
public class QuestionGeneratorService {

    private Logger logger = LoggerFactory.getLogger(QuestionGeneratorService.class);



    @Bean
    public Consumer<QuizDto> getQuizBinding() {
        return quizDto -> {
            logger.info("Received quiz-created event");
            logger.info("Quiz title: {}", quizDto.getTitle());

            // process quiz here
        };
    }
}

