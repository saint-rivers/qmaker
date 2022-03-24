package com.ksga.qmaker.quiz.models;

import com.ksga.qmaker.appuser.AppUser;
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
    private AppUser owner;
    private List<Question> questions;

    public Quiz(UUID id, String name) {
        this.id = id;
        this.name = name;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public AppUser getOwner() {
        return owner;
    }

    public List<Question> getQuestions() {
        return questions;
    }
}
