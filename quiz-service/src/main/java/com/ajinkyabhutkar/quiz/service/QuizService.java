package com.ajinkyabhutkar.quiz.service;

import com.ajinkyabhutkar.quiz.dto.QuizDto;

import java.util.List;


public interface QuizService {

    public QuizDto createQuiz(QuizDto quizDto);

    public List<QuizDto> getAllQuiz();

    public QuizDto findById(String id);

    public List<QuizDto> findByCategory(Long categoryId);

    public QuizDto updateQuiz(QuizDto quizDto,String id);

    public void deleteQuiz(String id);

}
