package com.tianjie.study.y2021.fastjson;


import com.tianjie.study.model.LittleBird;
import lombok.SneakyThrows;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.lang.reflect.Method;
import java.util.*;
import java.util.concurrent.ExecutionException;

public class App {

    public static void main(String[] args) throws Exception {
        final int[] i = {1};
        File file = new File("D:/test.txt");
        Thread wt1 = new Thread(new Runnable() {
            @SneakyThrows
            @Override
            public void run() {
                while (true){
                   i[0] =1;
                }
            }
        });
        Thread wt2 = new Thread(new Runnable() {
            @SneakyThrows
            @Override
            public void run() {
                while (true){
                    i[0] =2;
                }
            }
        });
        Thread wt3 = new Thread(new Runnable() {
            @SneakyThrows
            @Override
            public void run() {
                while (true){
                    if(i[0]==1 && i[0]==2){
                        System.out.println("判断正确");
                    }
                }
            }
        });
        wt1.start();
        wt2.start();
        wt3.start();
        wt3.join();
        wt1.join();
        wt2.join();



    }


    public static String minWindow(String s, String t) {
        final char[] chars = s.toCharArray();
        List<Map<Character,Integer>>zuhe = new ArrayList<>();

        for (int i = 0; i < chars.length; i++) {
           if(t.contains(chars[i]+"")){
               zuhe.add(new HashMap<>());
               for (Map<Character, Integer> characterIntegerMap : zuhe) {
                   characterIntegerMap.put(chars[i],i);
               }

           }
        }
        String result = s;
        Boolean has = false;
        for (Map<Character, Integer> characterIntegerMap : zuhe) {
            if(characterIntegerMap.keySet().size()!=t.length()){
                continue;
            }
            List<Integer> indexs = new ArrayList<>(characterIntegerMap.values());
            Collections.sort(indexs);
            String temp = s.substring(indexs.get(0),indexs.get(indexs.size()-1)+1);
            result = result.length()>temp.length()?temp:result;
            has = true;
        }
        if(has){
            return result;
        }
        return "";

    }




}
