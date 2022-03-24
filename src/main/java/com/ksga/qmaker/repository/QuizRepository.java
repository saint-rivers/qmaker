package com.ksga.qmaker.repository;

import com.ksga.qmaker.base.BaseRepository;
import com.ksga.qmaker.config.typehandler.UuidTypeHandler;
import com.ksga.qmaker.quiz.models.Quiz;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.UUID;

/**
 * @author dayan
 */
@Mapper
public interface QuizRepository extends BaseRepository<Quiz> {

    @Update("CREATE TABLE quizzes (" +
            "id uuid NOT NULL PRIMARY KEY, " +
            "name text NOT NULL, " +
            "owner_id uuid NOT NULL " +
            ")")
    void createTable();

    @Update("DROP TABLE quizzes")
    void drop();

    @Select("SELECT EXISTS (" +
            "SELECT FROM pg_tables " +
            "WHERE schemaname = 'public' AND tablename='quizzes' " +
            ")")
    Boolean tableExists();

    @Select("SELECT * FROM quizzes")
    List<Quiz> findAll();

    @Select("SELECT * FROM quizzes WHERE ownerId = #{ownerId}")
    List<Quiz> findAllByOwnerId(UUID ownerId);

    @Select("INSERT INTO quizzes (id, name, owner_id) VALUES (#{quizId}, #{quizName}, #{ownerId})" +
            "RETURNING id, name;")
    @Result(column = "id", property = "id", id = true, typeHandler = UuidTypeHandler.class)
//    @Result(column = "owner_id", property = "ownerId", id = true, typeHandler = UuidTypeHandler.class)
    @Result(column = "name", property = "name")
    Quiz insert(UUID quizId, String quizName, UUID ownerId);

    @Select("SELECT id, name FROM quizzes WHERE id=#{quizId}")
    @Result(property = "id", column = "id", id = true, typeHandler = UuidTypeHandler.class)
    Quiz findById(@Param("quizId") UUID quizId);
}
