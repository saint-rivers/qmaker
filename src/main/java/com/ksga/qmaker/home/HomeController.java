package com.ksga.qmaker.home;

import com.ksga.qmaker.quiz.QuizService;
import com.ksga.qmaker.quiz.models.Quiz;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.UUID;


@Controller
public class HomeController {
    private final QuizService quizService;
    private final UUID tmpUserId = UUID.fromString("b760ce1d-ef95-48ac-b51f-8a9d07908a1a");

    public HomeController(QuizService quizService) {
        this.quizService = quizService;
    }

    @GetMapping
    public ModelAndView index(){
//        List<Quiz> quizzes = quizService.findAllByUserId("");
//        return new ModelAndView("index", "quizzes", quizzes);

        List<Quiz> quizzes = quizService.findAllByUserId(tmpUserId);
        return new ModelAndView("index", "quizzes", quizzes);
    }
}
