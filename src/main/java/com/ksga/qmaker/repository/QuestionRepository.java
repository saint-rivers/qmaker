package com.ksga.qmaker.repository;

import com.ksga.qmaker.base.BaseRepository;
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
            "points_awarded INTEGER, " +
            "    quiz_id        uuid\n" +
            "        constraint questions_quizzes_id_fk\n" +
            "            references quizzes\n" +
            "            on update cascade on delete cascade" +
            ");")
    void createTable();

    @Update("DROP TABLE questions")
    void drop();

    @Select("INSERT INTO questions (question, correct_answer, given_answer, points_awarded, quiz_id) " +
            "VALUES (#{question.questionPrompt}, #{question.correctAnswer}, #{question.givenAnswer}, #{question.pointsAwarded}, #{quizId}) " +
            "RETURNING id, question, correct_answer, given_answer, points_awarded;")
    Question insert(@Param("question") Question type, UUID quizId);

    List<Question> findAll();

    Question findById(Integer id);
}
