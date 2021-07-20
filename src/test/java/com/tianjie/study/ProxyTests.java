package com.tianjie.study;

import com.tianjie.study.y2020.mysql.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cglib.proxy.*;

import java.io.Serializable;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @version v1.0
 * @description TODO
 * @auth tian.jie
 * @date {date} {time}
 */
@SpringBootTest
public class ProxyTests {

    @Autowired
    private UserService userService;

    @Test
    private void test1(){
        System.out.println(1);
        userService.test();
    }


    @Test
    public void JdkProxy(){
        A a = new Aimp();
        Class aclz = A.class;
        InvocationHandler handler = new InvocationHandlerImpl(aclz);
        ClassLoader loader = a.getClass().getClassLoader();
        Class[] interfaces = a.getClass().getInterfaces();
//        A subject = (A) Proxy.newProxyInstance(loader, interfaces, handler);
        A proxyObject = (A) Proxy.newProxyInstance(aclz.getClassLoader(),new Class[] {aclz},handler);

        System.out.println("动态代理对象的类型："+proxyObject.getClass().getName());

        proxyObject.a();

    }

    @Test
    public void CglibProxy(){
        Enhancer enhancer = new Enhancer();
        //设置目标类的字节码文件
        enhancer.setSuperclass(Aimp.class);
        enhancer.setInterfaces(new Class[]{B.class});
        //设置回调函数
        enhancer.setCallbacks(new Callback[]{new MyMethodInterceptor(),new BDispatcher(new Bimp())});
        enhancer.setCallbackFilter(new CallbackFilter() {
            @Override
            public int accept(Method method) {
                if("b".equals(method.getName())){
                    return 1;
                }
                return 0;
            }
        });
        //这里的creat方法就是正式创建代理类
        A proxyDog = (A)enhancer.create();
        if(proxyDog instanceof B){
            ((B) proxyDog).b();
        }
        //调用代理类的eat方法
        proxyDog.a();

    }






}
interface B{

    void b();

}
interface A{

    void a();

}
class Aimp implements  A{

    @Override
    public void a() {
        System.out.println("i am a");
    }
}

class BDispatcher implements Dispatcher, Serializable {

    private B b;

    public BDispatcher(B b) {
        this.b = b;
    }

    @Override
    public Object loadObject() throws Exception {
        return this.b;
    }
}

class Bimp implements  B {

    @Override
    public void b() {
        System.out.println("i am B");
    }


}
class InvocationHandlerImpl  implements InvocationHandler {

    private A a;

    private Class clz;

    public InvocationHandlerImpl(A a) {
        this.a = a;
    }

    public InvocationHandlerImpl(Class clz) {
        this.clz = clz;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if (Object.class.equals(method.getDeclaringClass())) {
            return method.invoke(this, args);
        }
        System.out.println("aaa");
//        return method.invoke(this,args);
        return null;
    }

}
class MyMethodInterceptor implements MethodInterceptor {

    @Override
    public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
        if (Object.class.equals(method.getDeclaringClass())) {
            return "";
        }
        System.out.println("这里是对目标类进行增强！！！");
        //注意这里的方法调用，不是用反射哦！！！
        Object object = proxy.invokeSuper(obj, args);
        return object;
    }

}

class MyMethodInterceptor2 implements MethodInterceptor {

    @Override
    public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
        if (Object.class.equals(method.getDeclaringClass())) {
            return "";
        }
        System.out.println("22323232323！！！");
        //注意这里的方法调用，不是用反射哦！！！
        Object object = proxy.invokeSuper(obj, args);
        return object;
    }

}

