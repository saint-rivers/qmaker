package com.ksga.qmaker.home;

import com.ksga.qmaker.quiz.QuizService;
import com.ksga.qmaker.quiz.models.Quiz;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@AllArgsConstructor
public class HomeController {
    private final QuizService quizService;

    @GetMapping
    public ModelAndView index(){
        List<Quiz> quizzes = quizService.findAllByUserId("");
        return new ModelAndView("index", "quizzes", quizzes);
    }
}
