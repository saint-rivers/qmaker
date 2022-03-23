package com.ksga.qmaker.quiz;

import com.ksga.qmaker.question.models.Question;
import com.ksga.qmaker.quiz.models.Quiz;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

public interface QuizService {

    void createNewQuiz(@Valid @NotNull Quiz quiz, List<Question> questions);
}
