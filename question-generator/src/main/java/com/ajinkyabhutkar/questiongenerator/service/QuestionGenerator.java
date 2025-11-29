package com.ajinkyabhutkar.questiongenerator.service;

import com.ajinkyabhutkar.questiongenerator.collections.Question;
import com.ajinkyabhutkar.questiongenerator.dtos.QuestionDto;
import com.ajinkyabhutkar.questiongenerator.functions.QuizDto;

import java.util.List;

public interface QuestionGenerator {

    void generateAndSaveQuestions(QuizDto quizDto);
    List<QuestionDto> generateQuestions(String quizName, int NoOfQuestions, String desc);
}
