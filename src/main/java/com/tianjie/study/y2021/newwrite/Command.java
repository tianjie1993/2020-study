package com.tianjie.study.y2021.newwrite;

public interface Command<T,Y extends Context> {

    T execute(Y context);
}
