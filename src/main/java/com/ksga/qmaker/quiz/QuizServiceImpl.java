package com.ksga.qmaker.quiz;

import com.ksga.qmaker.repository.QuestionRepository;
import com.ksga.qmaker.quiz.models.Question;
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
    public void createNewQuiz(Quiz quiz, UUID userId, List<Question> questions) {
        quizRepository.insert(UUID.randomUUID(), quiz.getName(), userId);
        this.saveAll(quiz.getId(), questions);
    }

    @Override
    public List<Quiz> findAllByUserId(String id) {
        // todo: add a real user uuid
        return quizRepository.findAllById(UUID.randomUUID());
    }

    private void saveAll(UUID quizId, List<Question> questions) {
        if (questions.isEmpty()) return;
        for (Question q : questions) {
            questionRepository.insert(q, quizId);
        }
    }
}
