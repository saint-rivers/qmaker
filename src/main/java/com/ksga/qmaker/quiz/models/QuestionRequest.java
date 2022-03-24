package com.ksga.qmaker.quiz.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class QuestionRequest {

    @NotEmpty
    private String questionPrompt;

    @NotEmpty
    private String correctAnswer;

    private String givenAnswer;

    private UUID quizId;

    @NotEmpty
    @Size(min = 0, max = 100)
    private int pointsAwarded;

}
