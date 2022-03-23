package com.ksga.qmaker.quiz;

import com.ksga.qmaker.repository.QuestionRepository;
import com.ksga.qmaker.question.models.Question;
import com.ksga.qmaker.quiz.models.Quiz;
import com.ksga.qmaker.repository.QuizRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class QuizServiceImpl implements QuizService {

    private final QuizRepository quizRepository;
    private final QuestionRepository questionRepository;

    @Override
    public void createNewQuiz(Quiz quiz, List<Question> questions) {
        quizRepository.insert(UUID.randomUUID(), quiz.getName());
        this.saveAll(quiz.getId(), questions);
    }

    private void saveAll(UUID quizId, List<Question> questions) {
        if (questions.isEmpty()) return;
        for (Question q : questions) {
            questionRepository.insert(q, quizId);
        }
    }
}
