package com.tianjie.study.spring.ioc;

import com.tianjie.study.Application;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * @version v1.0
 * @description TODO
 * @auth tian.jie
 * @date {date} {time}
 */
public class TJDispatcherServlet extends HttpServlet {

    //配置文件容器
    private Properties properties = new Properties();

    private Map<String,Object> controllerMap = new HashMap<>();


    private Map<String,Object> beanMap = new HashMap<>();

    private Map<String, Method> handleMapping = new HashMap<>();



    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //6.映射到容器中的方法
        doMyDispatcher( req,  resp);
    }

    private void doMyDispatcher(HttpServletRequest req, HttpServletResponse resp) {
        String uri = req.getRequestURI();
        String servletPath = req.getServletPath();
        Method method = handleMapping.get(uri.replace(servletPath,""));
        try {
            if(null==method){
                resp.getWriter().write("404 Not Found");
            }else{
                   Object o =  method.invoke(controllerMap.get(method.getDeclaringClass().getSimpleName()),req,resp);
                resp.getWriter().write(o.toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }

    @Override
    public void init() throws ServletException {
        //1.读取并加载配置
        doLoadProperties();
        //2.读取类并装载到容器中
        doLoadBeanToContainer(Application.class.getPackage().getName());
        //3.进行DI
        doDi();
        //4.请求路由mapping
        doLoadRequestMapping();
    }

    private void doLoadRequestMapping() {
        for (String s : controllerMap.keySet()) {
            Method[] methods = controllerMap.get(s).getClass().getDeclaredMethods();
            for (Method method : methods) {
                RequestMapping requestMapping = method.getAnnotation(RequestMapping.class);
                if(null==requestMapping){
                    continue;
                }
                handleMapping.put(requestMapping.value()[0],method);
            }
        }
    }

    private void doDi() {
        for (String s : controllerMap.keySet()) {
            Field[] fields = controllerMap.get(s).getClass().getDeclaredFields();
            for (Field field : fields) {
                if(!field.isAnnotationPresent(Autowired.class)){
                    continue;
                }
                Object bean = beanMap.get(field.getType().getName());
                try {
                    field.setAccessible(true);
                    field.set(controllerMap.get(s),bean);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }

        }
    }

    private void doLoadBeanToContainer(String scanPackage) {
        try {
            String url = "/" +scanPackage.replaceAll("\\.","/");
            URL clzUrl = this.getClass().getResource(url);
            File file = new File(clzUrl.getFile());
            File[] files = file.listFiles();
            for (File file1 : files) {
                if(file1.isDirectory()){
                    doLoadBeanToContainer(scanPackage+"."+file1.getName());
                }else{
                    if(!file1.getName().endsWith(".class")){
                        continue;
                    }
                    String clzName = scanPackage+"."+file1.getName().replace(".class","");
                    Class<?> clz = Class.forName(clzName);
                    Object instance = clz.newInstance();
                    if(clz.isAnnotationPresent(TJController.class)){
                        controllerMap.put(clz.getSimpleName(),instance);
                    }

                    if(clz.isAnnotationPresent(TJService.class)){
                        beanMap.put(clz.getSimpleName(),instance);
                        Class<?> []interfaces = clz.getInterfaces();
                        for (Class<?> anInterface : interfaces) {
                            beanMap.put(anInterface.getName(),instance);
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private Boolean filename(){
        return true;
    }

    private void doLoadProperties() {
        InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("application.properties");
        try {
            properties.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
