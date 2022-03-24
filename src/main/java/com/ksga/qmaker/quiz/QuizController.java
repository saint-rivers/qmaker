package com.ksga.qmaker.quiz;

import com.ksga.qmaker.quiz.models.Question;
import com.ksga.qmaker.quiz.models.QuestionRequest;
import com.ksga.qmaker.quiz.models.Quiz;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

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
        mv.addObject("isUpdating", false);
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
    public ModelAndView postQuiz(Quiz quiz) {
        quizService.saveQuestionsToQuiz(quiz.getId());
        quizService.updateQuizName(quiz);
        ModelAndView mv = new ModelAndView();
        mv.setViewName("redirect:/");
        return mv;
    }

    @GetMapping("/edit")
    public ModelAndView getUpdateForm(
            @RequestParam("id") String quizId,
            @RequestParam("q") Integer questionId
    ) {
        // get quiz data
        Question question = quizService.findQuestionById(questionId);
        question.setQuizId(UUID.fromString(quizId));
        List<Question> questions = quizService.findQuestionsByQuizId(UUID.fromString(quizId));
        Quiz fetchedQuiz = quizService.findQuizById(UUID.fromString(quizId));

        System.out.println("hey");
        ModelAndView mv = new ModelAndView("pages/quizMaker");
        mv.addObject("isUpdating", true);
        mv.addObject("newQuestion", question);
        mv.addObject("quiz", fetchedQuiz);
        mv.addObject("questions", questions);
        mv.addObject("questionId", question.getId());
        return mv;
    }

    @PostMapping("/edit")
    public ModelAndView update(
            QuestionRequest question,
            @RequestParam("id") String quizId,
            @RequestParam("q") Integer questionId
    ) {
        ModelAndView mv = new ModelAndView("redirect:/quiz?id=" + quizId);
        quizService.updateQuestion(questionId, question);
        return mv;
    }

    @PostMapping("/delete")
    public ModelAndView delete(@RequestParam("id") String quizId, @RequestParam("q") String questionId) {
        quizService.deleteQuestion(questionId);
        return new ModelAndView("redirect:/quiz?id=" + quizId);
    }
}
