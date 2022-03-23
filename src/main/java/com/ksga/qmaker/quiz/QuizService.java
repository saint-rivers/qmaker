package com.ksga.qmaker.quiz;

import com.ksga.qmaker.quiz.models.Question;
import com.ksga.qmaker.quiz.models.Quiz;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.UUID;

public interface QuizService {

    void createNewQuiz(@Valid @NotNull Quiz quiz, @NotNull UUID userId, List<Question> questions);

    List<Quiz> findAllByUserId(String id);
}
