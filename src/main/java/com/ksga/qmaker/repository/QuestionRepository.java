package com.ksga.qmaker.repository;

import com.ksga.qmaker.base.BaseRepository;
import com.ksga.qmaker.config.typehandler.UuidTypeHandler;
import com.ksga.qmaker.quiz.models.Question;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.UUID;

@Mapper
public interface QuestionRepository extends BaseRepository<Question> {

    @Update("CREATE TABLE questions (" +
            "id SERIAL PRIMARY KEY," +
            "question TEXT NOT NULL," +
            "correct_answer TEXT NOT NULL," +
            "given_answer TEXT," +
            "is_saved BOOL DEFAULT false, " +
            "points_awarded INTEGER, " +
            "    quiz_id        uuid\n" +
            "        constraint questions_quizzes_id_fk\n" +
            "            references quizzes\n" +
            "            on update cascade on delete cascade" +
            ");")
    void createTable();

    @Update("DROP TABLE questions")
    void drop();

    @Select("INSERT INTO questions (question, correct_answer, given_answer, is_saved, points_awarded, quiz_id) " +
            "VALUES (#{questionPrompt}, #{correctAnswer}, #{givenAnswer}, #{isSaved}, #{pointsAwarded}, #{quizId}) " +
            "RETURNING id, question, correct_answer, given_answer, points_awarded;")
    Question insert(String questionPrompt, String correctAnswer, String givenAnswer, Boolean isSaved, Integer pointsAwarded, UUID quizId);

    List<Question> findAll();

    @Select("SELECT id, question, correct_answer, is_saved, points_awarded FROM questions WHERE quiz_id = #{quizId}")
    @Result(property = "id", column = "id")
    @Result(property = "questionPrompt", column = "question")
    @Result(property = "correctAnswer", column = "correct_answer")
    @Result(property = "pointsAwarded", column = "points_awarded")
    @Result(property = "isSaved", column = "is_saved")
    List<Question> findAllByQuizId(@Param("quizId") UUID quizId);

    Question findById(Integer id);
}
