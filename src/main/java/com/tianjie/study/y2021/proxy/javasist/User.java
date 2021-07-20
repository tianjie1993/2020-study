package com.tianjie.study.y2021.proxy.javasist;

import lombok.Data;

import java.io.Serializable;

@Data
public class User implements Serializable {

    private StringBuilder name;

    public void say(){
        System.out.println("i am tianjie");
    }
}
