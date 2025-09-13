package com.ajinkyabhutkar.quiz.service;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "question-service",url = "http://localhost:9093")
public interface QuestionFeignService {
}
