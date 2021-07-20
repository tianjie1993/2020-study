package com.tianjie.study.y2021.newwrite;

public class ContextFactoty {

    public static <T> T requiresAndInit(Class<? extends AbstractContext> clz,Object initData,String ...requires){
        try {
            Context context = clz.newInstance().requires(requires);
            context.init(initData);
            return (T) context;
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;

    }
}
