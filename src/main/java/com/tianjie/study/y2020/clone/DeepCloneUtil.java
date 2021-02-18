package com.tianjie.study.y2020.clone;

import lombok.Data;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class DeepCloneUtil {

    public static void main(String[] args) throws Exception {
        Student s= new Student();
        Map<String,String> a = new HashMap<>();
        a.put("ad","asd");
        Map<String,String> b = clone(a);
        System.out.println(1);
    }

    public static  <T> T clone(T object) throws Exception {
//        try {
//
//        }catch (Exception e){
//            throw new RuntimeException("ad");
//        }

        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(bos);
        oos.writeObject(object);

        ByteArrayInputStream bis = new ByteArrayInputStream(bos.toByteArray());
        ObjectInputStream ois = new ObjectInputStream(bis);
        return (T) ois.readObject();

    }


}
@Data
class Student implements Serializable{
    private String name;
}
