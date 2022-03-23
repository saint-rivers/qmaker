package com.ksga.qmaker.quiz;

import com.ksga.qmaker.quiz.models.Question;
import com.ksga.qmaker.quiz.models.QuizRequest;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/quiz")
public class QuizController {

    private final QuizService quizService;
    private final List<Question> questions;

    private final UUID tmpUuid = UUID.fromString("b760ce1d-ef95-48ac-b51f-8a9d07908a1a");

    public QuizController(QuizService quizService) {
        this.quizService = quizService;
        this.questions = new ArrayList<>();
    }

    @GetMapping
    public ModelAndView createQuizPage() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("pages/quizMaker");
        modelAndView.addObject("quiz", new QuizRequest());
        modelAndView.addObject("questions", questions);
        modelAndView.addObject("newQuestion", new Question());
        return modelAndView;
    }

    @PostMapping("/question")
    public ModelAndView addQuestion(Question question) {
        System.out.println(question);
        questions.add(question);
        return new ModelAndView("redirect:/quiz");
    }

    @PostMapping(consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public ModelAndView postQuiz(QuizRequest quiz) {
        quizService.createNewQuiz(quiz.toQuiz(), tmpUuid, this.questions);

        ModelAndView mv = new ModelAndView();
        mv.setViewName("redirect:/");
        return mv;
    }

}
