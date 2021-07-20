package com.tianjie.study.y2021.newwrite;

public interface Context<T> {


    void init(T t);

    String name();
}
