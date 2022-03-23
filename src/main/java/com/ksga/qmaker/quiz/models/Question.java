package com.ksga.qmaker.quiz.models;

import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Question {

    private Integer id;

    @NotEmpty
    private String questionPrompt;

    @NotEmpty
    private String correctAnswer;

    private String givenAnswer;

    @NotEmpty
    @Size(min = 0, max = 100)
    private int pointsAwarded;
}
