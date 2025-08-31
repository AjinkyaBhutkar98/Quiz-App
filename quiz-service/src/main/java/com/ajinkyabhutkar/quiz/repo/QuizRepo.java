package com.ajinkyabhutkar.quiz.repo;

import com.ajinkyabhutkar.quiz.collections.Quiz;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuizRepo extends MongoRepository<Quiz,String > {

    List<Quiz> findByTitle(String title);

    List<Quiz> findByCategoryId(Long categoryId);
}
