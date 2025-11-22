package com.ajinkyabhutkar.quiz.functions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

@Configuration
public class InformationService {

    private Logger logger = LoggerFactory.getLogger(InformationService.class);

    @Bean
    public Supplier<String> getInformation(){

        return ()->"hello Ajinkya";
    }

    //to take input and return output
    @Bean
    public Function<String,String> getUppercase(){

        return String::toUpperCase;
    }

    @Bean
    public Consumer<String> getAcknowledgement(){
       return x-> logger.info("Acknowledgement received");
    }


}
