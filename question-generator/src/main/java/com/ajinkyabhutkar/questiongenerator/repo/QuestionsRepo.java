package com.ajinkyabhutkar.questiongenerator.repo;

import com.ajinkyabhutkar.questiongenerator.collections.Question;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionsRepo extends MongoRepository<Question,String> {
}
