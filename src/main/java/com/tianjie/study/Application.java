package com.tianjie.study;

import com.tianjie.study.y2021.io.socketrpc.annotation.RpcAutowried;
import com.tianjie.study.y2021.io.socketrpc.rcpserviceapi.LittleBirdService;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.method.support.ModelAndViewContainer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.List;

@SpringBootApplication
@ComponentScan("com.tianjie")
@MapperScan({"com.tianjie.study.y2020.oracl","com.tianjie.study.y2020.mysql"})
public class Application  {

//    @Bean
//    public ServletRegistrationBean MyServlet1(){
//        return new ServletRegistrationBean(new TJDispatcherServlet(),"/myspring/*");
//    }


    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
