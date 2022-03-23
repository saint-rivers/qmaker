package com.ksga.qmaker;

import com.ksga.qmaker.question.models.Question;
import com.ksga.qmaker.repository.QuestionRepository;
import com.ksga.qmaker.quiz.models.Quiz;
import com.ksga.qmaker.repository.QuizRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@Slf4j
@AllArgsConstructor
public class InitializeApplication implements CommandLineRunner {

    private final QuizRepository quizRepository;
    private final QuestionRepository questionRepository;

    @Override
    public void run(String... args) {
        log.info("Initializing table");

        try {
            quizRepository.drop();
            quizRepository.createTable();
            questionRepository.drop();
            questionRepository.createTable();

            if (quizRepository.tableExists())
                log.info("quizzes table created");
        } catch (Exception e) {
            log.info("Unable to refresh quizzes table");
        }


        Quiz ooadQuiz = quizRepository.insert(UUID.randomUUID(), "OOAD quiz");
        System.out.println(ooadQuiz.getId());
        quizRepository.insert(UUID.randomUUID(), "Linux Shell");
        quizRepository.insert(UUID.randomUUID(), "Docker");

        Question question = questionRepository.insert(Question.builder()
                .question("2+2")
                .correctAnswer("4")
                .givenAnswer("5")
                .pointsAwarded(1)
                .build(), ooadQuiz.getId());
        System.out.println(question);
    }
}
