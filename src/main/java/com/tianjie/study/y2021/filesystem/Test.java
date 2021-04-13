package com.tianjie.study.y2021.filesystem;

import org.springframework.aop.MethodBeforeAdvice;
import org.springframework.aop.framework.Advised;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import java.lang.reflect.Method;

@Component
public class Test implements ApplicationContextAware {
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        for (String beanName : applicationContext.getBeanDefinitionNames()) {
                Object bean = applicationContext.getBean(beanName);
                if(!(bean instanceof Advised)){
                  continue;
                }
                if(bean==this){
                    continue;
                }

            ((Advised)bean).addAdvice(new MethodBeforeAdvice() {
                @Override
                public void before(Method method, Object[] args, Object target) throws Throwable {
//                    System.out.println(111);
//                    method.invoke(target,args);
                }
            });

        }
    }
}
