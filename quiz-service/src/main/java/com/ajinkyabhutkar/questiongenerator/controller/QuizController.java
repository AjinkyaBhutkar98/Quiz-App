package com.ajinkyabhutkar.questiongenerator.controller;

import com.ajinkyabhutkar.questiongenerator.dto.QuizDto;
import com.ajinkyabhutkar.questiongenerator.service.QuizService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

//    @Autowired
    private Logger logger= LoggerFactory.getLogger(this.getClass());

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
    int i=0; // for retry pattern
    @GetMapping
    @CircuitBreaker(name = "quizCircuitBreaker",fallbackMethod = "quizFallback")
    public ResponseEntity<List<QuizDto>> getAllQuiz(){
        logger.info("getting all quizzes {}",i++);
        return new ResponseEntity<>(quizService.getAllQuiz(),HttpStatus.OK);
    }

    public ResponseEntity<List<QuizDto>> quizFallback(){
        return new ResponseEntity<>(List.of(),HttpStatus.SERVICE_UNAVAILABLE);
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
