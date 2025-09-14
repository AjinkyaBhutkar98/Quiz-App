package com.ajinkyabhutkar.quiz.controller;

import com.ajinkyabhutkar.quiz.dto.ApiResponse;
import com.ajinkyabhutkar.quiz.dto.QuizDto;
import com.ajinkyabhutkar.quiz.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/quiz")
public class QuizController {

    @Autowired
    private QuizService quizService;

    //create quiz
    @PostMapping
    public ResponseEntity<QuizDto> createQuiz(@RequestBody QuizDto quizDto){

        return new ResponseEntity<>(quizService.createQuiz(quizDto), HttpStatus.CREATED);
    }

    //get quiz
    @GetMapping("/{id}")
    public ResponseEntity<QuizDto> getQuiz(@PathVariable String id){

        return new ResponseEntity<>(quizService.findById(id), HttpStatus.OK);
    }

    //get quiz by id
    @GetMapping
    public ResponseEntity<List<QuizDto>> getAllQuiz(){

        return new ResponseEntity<>(quizService.getAllQuiz(),HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<QuizDto> updateQuiz(@RequestBody QuizDto quizDto,@PathVariable String id){

        return new ResponseEntity<>(quizService.updateQuiz(quizDto,id),HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteQuiz(@PathVariable String id) {
        quizService.deleteQuiz(id);
        return ResponseEntity.noContent().build();  // 204 with empty body
    }

    @GetMapping("/categories/{id}")
    public ResponseEntity<List<QuizDto>> getByCategoryId(@PathVariable Long id){

        return new ResponseEntity<>(quizService.findByCategory(id),HttpStatus.OK);
    }


}
