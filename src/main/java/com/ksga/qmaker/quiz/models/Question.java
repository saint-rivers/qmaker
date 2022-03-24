package com.ksga.qmaker.quiz.models;

import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.UUID;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Question {

    private Integer id;

    private String questionPrompt;

    private String correctAnswer;

    private String givenAnswer;

    private Boolean isSaved;

    private UUID quizId;

    @NotEmpty
    @Size(min = 0, max = 100)
    private Integer pointsAwarded;

    public QuestionRequest toQuestionRequest() {
        return QuestionRequest.builder()
                .questionPrompt(this.getQuestionPrompt())
                .correctAnswer(this.getCorrectAnswer())
                .pointsAwarded(this.getPointsAwarded())
                .build();
    }
}
