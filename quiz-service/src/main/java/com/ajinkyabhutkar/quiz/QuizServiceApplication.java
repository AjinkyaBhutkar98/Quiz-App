package com.ajinkyabhutkar.quiz;

import com.ajinkyabhutkar.quiz.collections.Quiz;
import com.ajinkyabhutkar.quiz.repo.QuizRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.UUID;

@SpringBootApplication
public class QuizServiceApplication implements CommandLineRunner {

	@Autowired
	private QuizRepo quizRepo;

	public static void main(String[] args) {
		SpringApplication.run(QuizServiceApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

//		Quiz buildQuiz=Quiz.builder()
//				.id(UUID.randomUUID().toString())
//				.title("Spring Boot Quiz")
//				.description("this is basic Spring Boot quiz for beginners")
//				.createdBy("Ajinkya Bhutkar")
//				.maxMarks(50)
//				.noOfQuestions(20)
//				.passingMarks(17)
//				.timeLimit(30)
//				.imageUrl("https://miro.medium.com/0*w8ixAEZdEuzpzG9G.png")
//				.live(true)
//				.build();
//
//		Quiz savedQuiz=quizRepo.save(buildQuiz);
//
//		System.out.println(savedQuiz.toString());

	}
}
