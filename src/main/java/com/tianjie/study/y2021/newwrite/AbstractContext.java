package com.tianjie.study.y2021.newwrite;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractContext<T> implements Context<T>{

    protected List<String> REQUIRE_INITMETHOD = new ArrayList<>();

    protected AbstractContext<T> requires(String ...methodNames){
        for (String methodName : methodNames) {
            REQUIRE_INITMETHOD.add(methodName);
        }
        return this;
    }

}
