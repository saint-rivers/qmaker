package com.ksga.qmaker;

import com.ksga.qmaker.quiz.models.Question;
import com.ksga.qmaker.repository.QuestionRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class QuestionRepositoryTest {

    @Autowired
    private QuestionRepository questionRepository;

    @Test
    void shouldQuestionById() {
        // given

        // when
        Question question = questionRepository.findById(1);

        // then
        assertThat(question).isNotNull();
        assertThat(question.getQuestionPrompt()).isEqualTo("2+2");
    }
}
