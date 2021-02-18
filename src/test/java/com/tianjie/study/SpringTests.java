package com.tianjie.study;

import com.tianjie.study.y2020.mysql.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

//@SpringBootTest
class SpringTests {

    @Autowired
    private UserService userService;


//    @Test
    void contextLoads() {

    }


//    @Test
    public void headlessTest() throws Exception {
        //设置Headless模式
        System.setProperty("java.awt.headless","false");
        BufferedImage bi = new BufferedImage(200, 100,BufferedImage.TYPE_INT_RGB);
        Graphics g = bi.getGraphics();
        String s ="Headless模式测试";
        g.drawString(new String(s.getBytes(),"GB2312"), 50, 50);
        ImageIO.write(bi,"jpeg", new File("test.jpg"));
    }

}
