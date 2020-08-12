package com.tianjie.study;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan({"com.tianjie.study.oracl","com.tianjie.study.mysql"})
public class Application {

//    @Bean
//    public ServletRegistrationBean MyServlet1(){
//        return new ServletRegistrationBean(new TJDispatcherServlet(),"/myspring/*");
//    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
