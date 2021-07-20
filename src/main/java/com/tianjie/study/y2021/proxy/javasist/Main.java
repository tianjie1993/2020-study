package com.tianjie.study.y2021.proxy.javasist;

import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;
import javassist.NotFoundException;

public class Main {

    public static void main(String[] args) throws Exception {
        // 类库池, jvm中所加载的class
        ClassPool pool = ClassPool.getDefault();
        // 获取指定的Student类
        CtClass ctClass = pool.get("com.tianjie.study.y2021.proxy.javasist.User");
        // 获取sayHello方法
        CtMethod ctMethod = ctClass.getDeclaredMethod("say");
        // 在方法的代码后追加 一段代码
        ctMethod.insertAfter("System.out.println(\"I'm \" + 20 + \" years old.\");");
        // 使用当前的ClassLoader加载被修改后的类
        ctClass.toClass();

        User stu = new User();
        stu.say();
    }
}
