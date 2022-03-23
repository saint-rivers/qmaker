package com.ksga.qmaker.base;

import java.util.List;

public interface BaseRepository<T, I> {

    void createTable();

    void drop();
}
