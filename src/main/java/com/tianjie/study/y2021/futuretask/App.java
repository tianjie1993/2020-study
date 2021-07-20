package com.tianjie.study.y2021.futuretask;

import com.tianjie.study.y2021.proxy.javasist.User;
import org.springframework.beans.BeanUtils;

import java.io.*;
import java.util.concurrent.*;
import java.util.function.Supplier;

public class App {
    public static void main(String[] args) throws ExecutionException, InterruptedException, IOException, ClassNotFoundException {
        StringBuilder name  = new StringBuilder("tianjie");

        User a = new User();
        a.setName(name);
        File  file = new File("D:/user.data");
        ObjectOutputStream  oos = new ObjectOutputStream(new FileOutputStream(file));
        oos.writeObject(a);

        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
        User b = (User) ois.readObject();
        System.out.println(b.getName());
    }
}
