package com.tianjie.study.y2021.io.socketrpc.rcpprovider;

import com.tianjie.study.util.SpringUtil;
import com.tianjie.study.y2021.io.socketrpc.annotation.YrRpcClient;
import com.tianjie.study.y2021.io.socketrpc.annotation.YrRpcService;
import lombok.SneakyThrows;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class YrRpcServiceContext implements ApplicationContextAware {

    private Map<String,Object> RPC_SERVICES = new HashMap();

    public Object getService(String name){
        return RPC_SERVICES.get(name);
    }

    @SneakyThrows
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        Map<String, Object> services = applicationContext.getBeansWithAnnotation(YrRpcService.class);
        for (Object value : services.values()) {
            YrRpcService yrRpcService = value.getClass().getAnnotation(YrRpcService.class);
            RPC_SERVICES.put(yrRpcService.publisher().getName(),value);

        }
    }
}
