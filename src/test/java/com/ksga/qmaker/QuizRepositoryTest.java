package com.ksga.qmaker;

import com.ksga.qmaker.appuser.UserRole;
import com.ksga.qmaker.quiz.models.Question;
import com.ksga.qmaker.quiz.models.Quiz;
import com.ksga.qmaker.repository.AppUserRepository;
import com.ksga.qmaker.repository.QuestionRepository;
import com.ksga.qmaker.repository.QuizRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class QuizRepositoryTest {

    @Autowired
    private QuizRepository quizRepository;

    @Autowired
    private AppUserRepository appUserRepository;

    @Autowired
    private QuestionRepository questionRepository;

    @Test
    void shouldInsertQuiz() {
        // given
        UUID userId = UUID.randomUUID();
        appUserRepository.insert(userId, "dayan", "eam", "rc@gmail.com", "asd", LocalDateTime.now(), LocalDateTime.now(), UserRole.USER.toString());
        UUID quizId = UUID.randomUUID();

        // when
        String quizName = "junit testing";
        quizRepository.insert(quizId, quizName, userId);

        // then
        Quiz quiz = quizRepository.findById(quizId);
        assertThat(quiz.getName()).isEqualTo(quizName);
        assertThat(quiz.getId()).isEqualTo(quizId);
    }

    @Test
    void shouldInsertQuestions() {
        // given
        UUID userId = UUID.randomUUID();
        appUserRepository.insert(userId, "dayan", "eam", "rc@gmail.com", "asd", LocalDateTime.now(), LocalDateTime.now(), UserRole.USER.toString());
        UUID quizId = UUID.randomUUID();
        String quizName = "junit testing";
        quizRepository.insert(quizId, quizName, userId);

        questionRepository.insert("What?", "no", "", false, 1, quizId);
        questionRepository.insert("when?", "no", "", false, 1, quizId);
        questionRepository.insert("why?", "no", "", false, 1, quizId);

        // when
        List<Question> questions = questionRepository.findAllByQuizId(quizId);

        // then
        assertThat(questions.size()).isEqualTo(3);
        assertThat(questions.get(0).getQuestionPrompt()).isNotNull();
    }
}
