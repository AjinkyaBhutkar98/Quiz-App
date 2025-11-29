package com.ajinkyabhutkar.questiongenerator.functions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Consumer;


@Configuration
public class InformationService {

    private Logger logger = LoggerFactory.getLogger(InformationService.class);

    @Bean
    public Consumer<String> quizAckBinding() {
        return ack -> {
            logger.info("Received ACK from question-generator: {}", ack);
        };
    }





}
