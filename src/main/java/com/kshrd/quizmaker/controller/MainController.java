package com.kshrd.quizmaker.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MainController {

    @GetMapping({"/", "/index"})
    public ModelAndView index(){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("index");
        return mv;
    }

    @GetMapping("/quiz")
    public ModelAndView quiz(){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("pages/quiz-maker");
        return mv;
    }

}
