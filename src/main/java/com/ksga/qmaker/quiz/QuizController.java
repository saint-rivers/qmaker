package com.ksga.qmaker.quiz;

import com.ksga.qmaker.quiz.models.Question;
import com.ksga.qmaker.quiz.models.QuestionRequest;
import com.ksga.qmaker.quiz.models.Quiz;
import com.ksga.qmaker.quiz.models.QuizRequest;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/quiz")
public class QuizController {

    private final QuizService quizService;

    private final UUID tmpUserId = UUID.fromString("b760ce1d-ef95-48ac-b51f-8a9d07908a1a");

    public QuizController(QuizService quizService) {
        this.quizService = quizService;
    }

    @GetMapping("/new")
    public ModelAndView createQuizPage() {
        Quiz defaultQuiz = new Quiz(UUID.randomUUID(), "untitled quiz");
        quizService.createNewQuiz(defaultQuiz, tmpUserId);
        return new ModelAndView("redirect:/quiz?id=" + defaultQuiz.getId());
    }

    @GetMapping
    public ModelAndView editQuizPage(@RequestParam("id") String quizId) {
        UUID qId = UUID.fromString(quizId);
        List<Question> questions = quizService.findQuestionsByQuizId(qId);
        Quiz fetchedQuiz = quizService.findQuizById(UUID.fromString(quizId));
        QuestionRequest newQuestion = QuestionRequest.builder().quizId(qId).build();

        ModelAndView mv = new ModelAndView("pages/quizMaker");
        mv.addObject("quiz", fetchedQuiz);
        mv.addObject("questions", questions);
        mv.addObject("newQuestion", newQuestion);
        return mv;
    }

    @PostMapping("/question")
    public ModelAndView addQuestion(QuestionRequest question) {
        quizService.addUnsavedQuestion(question);
        return new ModelAndView("redirect:/quiz?id=" + question.getQuizId());
    }

    @PostMapping(consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public ModelAndView postQuiz(QuizRequest quiz) {
        quizService.saveQuestionsToQuiz(quiz.toQuiz().getId());
        ModelAndView mv = new ModelAndView();
        mv.setViewName("redirect:/");
        return mv;
    }

}
