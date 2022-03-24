package com.ksga.qmaker.quiz;

import com.ksga.qmaker.quiz.models.QuestionRequest;
import com.ksga.qmaker.repository.QuestionRepository;
import com.ksga.qmaker.quiz.models.Question;
import com.ksga.qmaker.quiz.models.Quiz;
import com.ksga.qmaker.repository.QuizRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@Slf4j
public class QuizServiceImpl implements QuizService {

    private final QuizRepository quizRepository;
    private final QuestionRepository questionRepository;
    private final ModelMapper modelMapper = new ModelMapper();

    public QuizServiceImpl(QuizRepository quizRepository, QuestionRepository questionRepository) {
        this.quizRepository = quizRepository;
        this.questionRepository = questionRepository;
    }

    @Override
    public void createNewQuiz(Quiz quiz, UUID userId) {
        quizRepository.insert(quiz.getId(), quiz.getName(), userId);
//        this.saveAll(quiz.getId(), questions);
    }

    @Override
    public List<Quiz> findAllByUserId(String id) {
        // todo: add a real user uuid
        return quizRepository.findAllByOwnerId(UUID.randomUUID());
    }

    @Override
    public void addUnsavedQuestion(QuestionRequest questionRequest) {
        questionRepository.insert(
                questionRequest.getQuestionPrompt(),
                questionRequest.getCorrectAnswer(),
                questionRequest.getGivenAnswer(),
                false,
                questionRequest.getPointsAwarded(),
                questionRequest.getQuizId()
        );
    }

    @Override
    public void saveQuestionsToQuiz(UUID quizId) {
        //todo: get all quizzes with that id
        List<Question> quizzes = questionRepository.findAllByQuizId(quizId);
        // todo: set the isSaved to true
        quizzes.forEach(q -> q.setIsSaved(true));
    }

    @Override
    public List<Question> findQuestionsByQuizId(UUID quizId) {
        try {
            return questionRepository.findAllByQuizId(quizId);
        } catch (Exception e){
            log.info("Unable to find questions of quiz: {}", quizId.toString());
        }
        return new ArrayList<>();
    }

    @Override
    public Quiz findQuizById(UUID quizId) {
        try {
            return quizRepository.findById(quizId);
        } catch (Exception e){
            log.info("Unable to find quiz: {}", quizId.toString());
        }
        return new Quiz();
    }

    private void saveAll(UUID quizId, List<Question> questions) {
        if (questions.isEmpty()) return;
        for (Question q : questions) {
            questionRepository.insert(
                    q.getQuestionPrompt(),
                    q.getCorrectAnswer(),
                    q.getGivenAnswer(),
                    q.getIsSaved(),
                    q.getPointsAwarded(),
                    quizId
            );
        }
    }
}
