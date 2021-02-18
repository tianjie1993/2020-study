package com.tianjie.study.y2020.json.fastjson;

import com.alibaba.fastjson.serializer.JSONSerializer;
import com.alibaba.fastjson.serializer.ObjectSerializer;
import org.springframework.web.bind.annotation.RestController;

import javax.naming.Name;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Type;

@RestController
public class ModelValueSerializer implements ObjectSerializer {
    @Override
    public void write(JSONSerializer serializer, Object object, Object fieldName, Type fieldType,
                      int features) throws IOException {
        try{
            Field bufField = serializer.out.getClass().getDeclaredField("buf");
            bufField.setAccessible(true);
            char[] buf = (char[]) bufField.get(serializer.out);
            int start = serializer.toString().length();
            String text = object + "元"+"*,*"+fieldName+"name"+"*:*"+123;
            serializer.write(text);
            int end = start + text.length();
            for (int i = start; i < end; i++) {
                if(buf[i] == '*'){
                    buf[i]='\"';
                }
            }

        }catch (Exception e){
        }
    }

}
