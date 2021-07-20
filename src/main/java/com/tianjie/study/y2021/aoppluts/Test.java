package com.tianjie.study.y2021.aoppluts;

import org.springframework.aop.AfterAdvice;
import org.springframework.aop.AfterReturningAdvice;
import org.springframework.aop.MethodBeforeAdvice;
import org.springframework.aop.framework.Advised;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.support.TransactionSynchronizationManager;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.Method;

@Component
public class Test implements ApplicationContextAware {
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        for (String beanName : applicationContext.getBeanDefinitionNames()) {
            Object bean = applicationContext.getBean(beanName);
            if (!(bean instanceof Advised)) {
                continue;
            }
            if (bean == this) {
                continue;
            }
//            RestController annotation = bean.getClass().getAnnotation(RestController.class);
//            if (null == annotation) {
//                continue;
//            }

            ((Advised) bean).addAdvice((AfterReturningAdvice) (returnValue, method, args, target) -> {
                returnValue = 1;
            });

        }
    }
}
