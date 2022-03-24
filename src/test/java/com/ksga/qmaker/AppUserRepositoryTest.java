package com.ksga.qmaker;

import com.ksga.qmaker.appuser.AppUser;
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
public class AppUserRepositoryTest {

    @Autowired
    private QuizRepository quizRepository;

    @Autowired
    private AppUserRepository appUserRepository;

    @Autowired
    private QuestionRepository questionRepository;

    @BeforeEach
    void setupUser() {
    }

    @Test
    void shouldInsertQuestions() {
        // given
        UUID userId = UUID.randomUUID();
        appUserRepository.insert(userId, "dayan", "eam", "rc@gmail.com", "asd", LocalDateTime.now(), LocalDateTime.now(), UserRole.USER.toString());

        // when
        AppUser user = appUserRepository.findById(userId.toString());

        // then
        assertThat(user.getId()).isEqualTo(userId);
        assertThat(user.getFirstname()).isEqualTo("dayan");
    }
}
