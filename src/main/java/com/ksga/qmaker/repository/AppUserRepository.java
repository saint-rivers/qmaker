package com.ksga.qmaker.repository;

import com.ksga.qmaker.appuser.AppUser;
import com.ksga.qmaker.base.BaseRepository;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.time.LocalDateTime;
import java.util.UUID;

@Mapper
public interface AppUserRepository extends BaseRepository<AppUser> {

    @Update("CREATE TABLE app_users (" +
            "id UUID NOT NULL PRIMARY KEY, " +
            "firstname TEXT NOT NULL, " +
            "lastname TEXT NOT NULL, " +
            "email TEXT NOT NULL UNIQUE, " +
            "password TEXT NOT NULL, " +
            "date_created TIMESTAMP NOT NULL, " +
            "last_updated TIMESTAMP NOT NULL, " +
            "role TEXT NOT NULL, " +
            "is_enabled BOOL DEFAULT FALSE, " +
            "is_locked BOOL DEFAULT FALSE " +
            ")")
    void createTable();

    @Update("DROP TABLE app_users")
    void drop();

    @Select("SELECT * FROM app_users WHERE id = #{id}")
    AppUser findById(UUID id);

    @Insert("INSERT INTO app_users (id, firstname, lastname, email, password, date_created, last_updated, role)" +
            "VALUES (#{id}, #{firstname}, #{lastname}, #{email}, #{password}, #{dateCreated}, #{lastUpdated}, #{userRole} )")
    void insert(UUID id, String firstname, String lastname, String email, String password, LocalDateTime dateCreated, LocalDateTime lastUpdated, String userRole);
}
