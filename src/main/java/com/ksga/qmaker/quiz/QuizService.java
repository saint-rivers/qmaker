package com.ksga.qmaker.quiz;

import com.ksga.qmaker.quiz.models.Question;
import com.ksga.qmaker.quiz.models.QuestionRequest;
import com.ksga.qmaker.quiz.models.Quiz;
import com.ksga.qmaker.quiz.models.QuizRequest;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.UUID;

public interface QuizService {

    void createNewQuiz(@Valid @NotNull Quiz quiz, @NotNull UUID userId);

    List<Quiz> findAllByUserId(UUID id);

    void addUnsavedQuestion(QuestionRequest question);

    void saveQuestionsToQuiz(UUID quizId);

    List<Question> findQuestionsByQuizId(UUID quizId);

    Quiz findQuizById(UUID quizId);

    void updateQuizName(Quiz quiz);
}
