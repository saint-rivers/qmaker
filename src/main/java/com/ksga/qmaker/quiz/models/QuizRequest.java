package com.ksga.qmaker.quiz.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class QuizRequest {
    private String quizName;
    private List<Question> questions = new ArrayList<>();

    public Quiz toQuiz(){
        return Quiz.builder()
                .name(this.getQuizName())
                .build();
    }
}
