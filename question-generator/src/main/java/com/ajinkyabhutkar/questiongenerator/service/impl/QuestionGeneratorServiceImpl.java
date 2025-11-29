package com.ajinkyabhutkar.questiongenerator.service.impl;

import com.ajinkyabhutkar.questiongenerator.collections.Question;
import com.ajinkyabhutkar.questiongenerator.dtos.QuestionDto;
import com.ajinkyabhutkar.questiongenerator.functions.QuestionGeneratorService;
import com.ajinkyabhutkar.questiongenerator.functions.QuizDto;
import com.ajinkyabhutkar.questiongenerator.repo.QuestionsRepo;
import com.ajinkyabhutkar.questiongenerator.service.QuestionGenerator;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class QuestionGeneratorServiceImpl implements QuestionGenerator {


    private Logger logger = LoggerFactory.getLogger(QuestionGeneratorServiceImpl.class);

    @Autowired
    private QuestionsRepo questionsRepo;

    private ChatClient chatClient;

    @Autowired
    private ModelMapper modelMapper;

    public QuestionGeneratorServiceImpl(ChatClient.Builder builder) {
        this.chatClient = builder.build();
    }

    @Override
    public void generateAndSaveQuestions(QuizDto quizDto) {

        List<QuestionDto> questions = this.generateQuestions(
                quizDto.getTitle(),
                10,
                quizDto.getDescription()
        );

        // 🔥 Log generated questions
        questions.forEach(q ->
                logger.info("Generated Question: {}", q.getQuestion())
        );

        List<Question> genQuestions = questions.stream()
                .map(q -> {
                    q.setQuizId(quizDto.getId());
                    return modelMapper.map(q, Question.class);
                })
                .toList();

        questionsRepo.saveAll(genQuestions);
        logger.info("Questions saved successfully");
    }

    @Override
    public List<QuestionDto> generateQuestions(String quizName, int noOfQuestions, String desc) {

        String systemString = """
            As a coding, technology, programming, and framework expert,
            your primary role is to generate high-quality quiz questions.
            """;

        String promptString = """
            Generate {NoOfQuestions} questions for a quiz named "{quizName}".
            The quiz description is: "{desc}".
            Return the questions as a JSON array of QuestionDto objects.
            """;

        Map<String, Object> valuesForPrompt = Map.of(
                "NoOfQuestions", noOfQuestions,
                "quizName", quizName,
                "desc", desc
        );

        return this.chatClient
                .prompt()
                .system(systemString)
                .user(promptUserSpec ->
                        promptUserSpec.text(promptString).params(valuesForPrompt)
                )
                .call()
                .entity(new ParameterizedTypeReference<List<QuestionDto>>() {});
    }

}
