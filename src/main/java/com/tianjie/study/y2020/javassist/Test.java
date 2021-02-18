package com.tianjie.study.y2020.javassist;

import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;

class Hello {
    public void say() {
        System.out.println("Hello");
    }
}
public class Test {
    public static void main(String[] args) throws Exception {
        ClassPool cp = ClassPool.getDefault();
        CtClass cc = cp.get("com.tianjie.study.javassist.Hello");
        CtMethod m = cc.getDeclaredMethod("say");
//        m.setBody("{System.out.println(\"shit\");}");
        m.insertBefore("System.out.println(\"fuck\");");
        Class c = cc.toClass();
        Hello h = (Hello) c.newInstance();
        h.say();
    }


}