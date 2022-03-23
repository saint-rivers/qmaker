package com.ksga.qmaker.quiz;

import com.ksga.qmaker.quiz.models.QuizRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/quiz")
@AllArgsConstructor
public class QuizController {

    private final QuizService quizService;

    @GetMapping
    public ModelAndView createQuizPage(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("pages/quizMaker");
        modelAndView.addObject("quiz", new QuizRequest());
        return modelAndView;
    }

    @PostMapping(consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public ModelAndView postQuiz(QuizRequest quiz){
        System.out.println(quiz.getQuizName());
        System.out.println(quiz.getQuestions());

        quizService.createNewQuiz(quiz.toQuiz(), quiz.getQuestions());

        ModelAndView mv = new ModelAndView();
        mv.setViewName("redirect:/");
        return mv;
    }

}
