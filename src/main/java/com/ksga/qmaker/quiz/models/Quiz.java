package com.ksga.qmaker.quiz.models;

import lombok.*;

import java.util.List;
import java.util.UUID;

@Data
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Quiz {
    private UUID id;
    private String name;
//    private String author;
    private List<Question> questions;
}
