package com.ksga.qmaker;

import com.ksga.qmaker.appuser.AppUser;
import com.ksga.qmaker.appuser.UserRole;
import com.ksga.qmaker.repository.AppUserRepository;
import com.ksga.qmaker.quiz.models.Question;
import com.ksga.qmaker.repository.QuestionRepository;
import com.ksga.qmaker.quiz.models.Quiz;
import com.ksga.qmaker.repository.QuizRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.UUID;

@Component
@Slf4j
public class InitializeApplication implements CommandLineRunner {

    private final QuizRepository quizRepository;
    private final QuestionRepository questionRepository;
    private final AppUserRepository appUserRepository;

    public InitializeApplication(QuizRepository quizRepository, QuestionRepository questionRepository, AppUserRepository appUserRepository) {
        this.quizRepository = quizRepository;
        this.questionRepository = questionRepository;
        this.appUserRepository = appUserRepository;
    }

    private static UUID tmpUser = null;

    public void drop(){
        questionRepository.drop();
        quizRepository.drop();
        appUserRepository.drop();
    }

    @Override
    public void run(String... args) {
        log.info("Initializing table");

        try {
            drop();
            appUserRepository.restart("app_users");
            quizRepository.restart("quizzes");
            questionRepository.restart("questions");

            log.info("Tables created");
        } catch (Exception e) {
            log.info("Unable to refresh quizzes table: {}", e.getMessage());
        }

        initUser();
        initQuiz();
    }

    private void initUser() {
        AppUser appUser = AppUser.builder()
                .id(UUID.fromString("b760ce1d-ef95-48ac-b51f-8a9d07908a1a"))
                .dateCreated(LocalDateTime.now())
                .lastUpdated(LocalDateTime.now())
                .firstname("Dayan")
                .lastname("Eam")
                .email("eam.dayan@gmail.com")
                .password("asd")
                .isEnabled(false)
                .isLocked(false)
                .userRole(UserRole.USER)
                .build();
        appUserRepository.insert(appUser.getId(), appUser.getFirstname(), appUser.getLastname(), appUser.getEmail(), appUser.getPassword(), appUser.getDateCreated(), appUser.getLastUpdated(), appUser.getUserRole().toString());
        tmpUser = appUser.getId();
    }

    private void initQuiz() {
        if (tmpUser == null) return;
//        appUserRepository.findById(tmpUser);

        Quiz ooadQuiz = quizRepository.insert(UUID.randomUUID(), "OOAD quiz", tmpUser);
        quizRepository.insert(UUID.randomUUID(), "Linux Shell", tmpUser);
        quizRepository.insert(UUID.randomUUID(), "Docker", tmpUser);

        Question question = questionRepository.insert(Question.builder()
                .questionPrompt("2+2")
                .correctAnswer("4")
                .givenAnswer("5")
                .pointsAwarded(1)
                .build(), ooadQuiz.getId());
        System.out.println(question);
    }


}
