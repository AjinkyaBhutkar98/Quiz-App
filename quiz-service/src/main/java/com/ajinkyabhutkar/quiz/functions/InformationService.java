package com.ajinkyabhutkar.quiz.functions;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Function;
import java.util.function.Supplier;

@Configuration
public class InformationService {

    @Bean
    public Supplier<String> getInformation(){

        return ()->"hello Ajinkya";
    }

    //to take input and return output
    @Bean
    public Function<String,String> getUppercase(){

        return String::toUpperCase;
    }






}
