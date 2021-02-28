package com.tianjie.study.y2021.cglib;

import com.tianjie.study.model.LittleBird;
import com.tianjie.study.y2020.clone.DeepCloneUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.cglib.beans.BeanCopier;
import org.springframework.cglib.core.Converter;

/**
 * -XX:+TraceClassLoading -XX:+TraceClassUnloading
 * 查看类加载信息
 *
 * 类加载主要消耗jvm metaspaces 空间信息
 *
 * 拷贝测试
 * @author tian.jie
 * @version 1.0
 * @date 2021-02-25 6:30
 */
public class App {
    public static void main(String[] args) throws Exception {
        BeanCopier beanCopier = BeanCopier.create(LittleBird.class,LittleBird.class,false);

        long start = System.currentTimeMillis();


//        LittleBird littleBird = new LittleBird();
//        littleBird.setName("adsfad");
//        LittleBird littleBird1 = new LittleBird();
//        beanCopier.copy(littleBird,littleBird1,null);
//        littleBird.setName("hahah");
//        System.out.println(littleBird1.getName());


//        LittleBird littleBird2 = new LittleBird();
//        littleBird2.setName("2222");
//            LittleBird littleBird3 = new LittleBird();
//            BeanUtils.copyProperties(littleBird2,littleBird3);
//        littleBird2.setName("22221111");
//        System.out.println(littleBird3.getName());

        for(;;){
            LittleBird littleBird = new LittleBird();
            littleBird.setName("adsfad");
            LittleBird littleBird1 = new LittleBird();
            BeanUtils.copyProperties(littleBird,littleBird1);
        }
        //性能明显高于BeanUtils 和流拷贝
//        for (int i = 0; i < 10000; i++) {
//            LittleBird littleBird = new LittleBird();
//            littleBird.setName("adsfad");
//            LittleBird littleBird1 = new LittleBird();
//            beanCopier.copy(littleBird,littleBird1,null);
//
//        }
//        for (int i = 0; i < 10000; i++) {
//            System.out.println(i);
//            LittleBird littleBird = new LittleBird();
//            littleBird.setName("adsfad");
//            LittleBird littleBird1 = new LittleBird();
//            BeanUtils.copyProperties(littleBird,littleBird1);
//
//        }
//        for (int i = 0; i < 10000; i++) {
//            System.out.println(i);
//            LittleBird littleBird = new LittleBird();
//            littleBird.setName("adsfad");
//            LittleBird LittleBird1 =  DeepCloneUtil.clone(littleBird);

//        }
//        System.out.println("结束用时："+ (System.currentTimeMillis()-start));
//
//
    }
}
