package com.ajinkyabhutkar.quiz.functions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Function;

@Configuration
public class QuestionGeneratorService {

    private Logger logger = LoggerFactory.getLogger(QuestionGeneratorService.class);

    @Bean(name = "getQuizBinding")
    public Function<QuizDto, String> getQuizBinding() {
        return quizDto -> {
            logger.info("Received quiz-created event");
            logger.info("Quiz title: {}", quizDto.getTitle());
            return "Quiz created successfully";
        };
    }
}

