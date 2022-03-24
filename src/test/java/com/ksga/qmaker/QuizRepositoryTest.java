package com.ksga.qmaker;

import com.ksga.qmaker.appuser.UserRole;
import com.ksga.qmaker.quiz.models.Question;
import com.ksga.qmaker.quiz.models.Quiz;
import com.ksga.qmaker.repository.AppUserRepository;
import com.ksga.qmaker.repository.QuestionRepository;
import com.ksga.qmaker.repository.QuizRepository;
import org.junit.jupiter.api.BeforeEach;
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

    private UUID userId = null;

    @BeforeEach
    void setupUser() {
        userId = UUID.randomUUID();
        appUserRepository.insert(userId, "dayan", "eam", "rc@gmail.com", "asd", LocalDateTime.now(), LocalDateTime.now(), UserRole.USER.toString());
    }

    @Test
    void shouldInsertQuestions() {
        // given
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

    @Test
    void shouldInsertQuiz() {
        // given
        userId = UUID.randomUUID();
        appUserRepository.insert(userId, "dayan", "eam", "rc@gmail.com", "asd", LocalDateTime.now(), LocalDateTime.now(), UserRole.USER.toString());

        UUID quizId = UUID.randomUUID();
//        userId = UUID.randomUUID();
//        appUserRepository.insert(userId, "dayan", "eam", "rc@gmail.com", "asd", LocalDateTime.now(), LocalDateTime.now(), UserRole.USER.toString());

        // when
        String quizName = "junit testing";
        quizRepository.insert(quizId, quizName, userId);

        // then
        Quiz quiz = quizRepository.findById(quizId);
        assertThat(quiz.getName()).isEqualTo(quizName);
        assertThat(quiz.getId()).isEqualTo(quizId);
    }

    @Test
    void shouldFetchAllQuizzesByUserId() {
        // given
        UUID quizId = UUID.randomUUID();
        String quizName = "junit testing";
        quizRepository.insert(quizId, quizName, userId);

        UUID quizId2 = UUID.randomUUID();
        String quizName2 = "junit testing 2";
        quizRepository.insert(quizId2, quizName2, userId);

        // when
        List<Quiz> quizzes = quizRepository.findAllByOwnerId(userId.toString());

        // then
        assertThat(quizzes.size()).isEqualTo(2);
        assertThat(quizzes.get(0).getName()).isEqualTo("junit testing");
        assertThat(quizzes.get(0).getOwner()).isNotNull();
        assertThat(quizzes.get(0).getOwner().getFirstname()).isEqualTo("dayan");
    }
}
