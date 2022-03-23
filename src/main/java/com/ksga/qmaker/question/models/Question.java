package com.ksga.qmaker.question.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.UUID;

@Data
@Builder
public class Question {

    private Integer id;

    @NotEmpty
    private String question;

    @NotEmpty
    private String correctAnswer;

    private String givenAnswer;

    @NotEmpty
    @Size(min = 0, max = 100)
    private int pointsAwarded;
}
