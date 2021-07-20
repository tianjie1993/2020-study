package com.tianjie.study.common;

import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.method.support.ModelAndViewContainer;
import org.springframework.web.servlet.mvc.method.annotation.RequestResponseBodyMethodProcessor;


public class TestValueHandle implements HandlerMethodReturnValueHandler {

    private RequestResponseBodyMethodProcessor requestResponseBodyMethodProcessor;


    public TestValueHandle(RequestResponseBodyMethodProcessor requestResponseBodyMethodProcessor) {
        this.requestResponseBodyMethodProcessor = requestResponseBodyMethodProcessor;
    }

    @Override
    public boolean supportsReturnType(MethodParameter methodParameter) {
        return true;
    }

    @Override
    public void handleReturnValue(Object o, MethodParameter methodParameter, ModelAndViewContainer modelAndViewContainer, NativeWebRequest nativeWebRequest) throws Exception {
        o="121";
        requestResponseBodyMethodProcessor.handleReturnValue(o,methodParameter,modelAndViewContainer,nativeWebRequest);
    }
}
