package com.ksga.qmaker.quiz.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class QuizRequest {
    private UUID id;
    private String quizName;
    private List<Question> questions = new ArrayList<>();

    public Quiz toQuiz(){
        return Quiz.builder()
                .name(this.getQuizName())
                .build();
    }
}
