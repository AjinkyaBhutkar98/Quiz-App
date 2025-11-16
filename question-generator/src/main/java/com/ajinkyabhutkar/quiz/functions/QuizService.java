package com.ajinkyabhutkar.quiz.functions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Consumer;

@Configuration
public class QuizService {

    private Logger logger = LoggerFactory.getLogger(QuizService.class);

    @Bean
    public Consumer<QuizDto> getQuizBinding(){
        return quizDto -> {
            logger.info("Quiz Created Event Captured");
            logger.info(quizDto.getTitle());
        };
    }
}
