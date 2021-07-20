package com.tianjie.study.y2021.newwrite;

public class CommandFactory {

    public static <T> T createCmd(Class<? extends Command> clz){
        try {
            return (T) clz.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }
}
