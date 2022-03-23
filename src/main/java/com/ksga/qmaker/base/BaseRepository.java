package com.ksga.qmaker.base;


import org.apache.ibatis.annotations.Select;

public interface BaseRepository<T> {

    void createTable();

    void drop();

    @Select("SELECT EXISTS (" +
            "SELECT FROM pg_tables " +
            "WHERE schemaname = 'public' AND tablename=#{tableName} " +
            ")")
    boolean exists(String tableName);

    default void restart(String tableName){
        if (this.exists(tableName)) drop();
        createTable();
    }
}
