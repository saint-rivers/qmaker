package com.ksga.qmaker.quiz.models;

import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

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

//    @NotEmpty
//    @Size(min = 0, max = 100)
    private Integer pointsAwarded;
}
