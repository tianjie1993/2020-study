package com.tianjie.study.y2020.jackson;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.ContextualSerializer;
import com.fasterxml.jackson.databind.ser.ResolvableSerializer;

import java.io.IOException;

public class EnumSerializer extends JsonSerializer<Object> implements ContextualSerializer, ResolvableSerializer {
        private String fieldName;
        private IsEnum isEnum;

        public EnumSerializer() {
        }

        public EnumSerializer(final String fieldName, IsEnum isEnum) {
            this.fieldName = fieldName;
            this.isEnum = isEnum;
        }

        public void serialize(final Object s, final JsonGenerator jsonGenerator, final SerializerProvider serializerProvider) throws IOException, JsonProcessingException {
            jsonGenerator.writeObject(s);
             System.out.println("hahahdahdafd");

//            try {
//                RedisTemplate redisTemplate = SpringUtil.getBean("redisTemplate");
////                String itemKey = CacheNames.DICT_ITEM_KEY+isEnum.value()+s;
//                String itemKey = isEnum.value()+s;
//                Object itemName = redisTemplate.opsForValue().get(itemKey);
//                if(null==itemName){
////                    itemName = SpringUtil.getBean(DictItemService.class).getItemName(isEnum.value(),String.valueOf(s));
//                    redisTemplate.opsForValue().set(itemKey,itemName,10L, TimeUnit.MINUTES);
//                }
//                jsonGenerator.writeStringField(this.fieldName + "Name", String.valueOf(itemName));
//            } catch (Exception var6) {
//            }

        }

        public JsonSerializer<?> createContextual(final SerializerProvider serializerProvider, final BeanProperty beanProperty) throws JsonMappingException {
            System.out.println("初始化---");
            if (beanProperty != null) {
                IsEnum isEnum = beanProperty.getAnnotation(IsEnum.class);
                if (isEnum == null) {
                    isEnum = beanProperty.getContextAnnotation(IsEnum.class);
                }

                return isEnum != null ? new EnumSerializer(beanProperty.getName(),  isEnum) : serializerProvider.findValueSerializer(beanProperty.getType(), beanProperty);
            } else {
                return serializerProvider.findNullValueSerializer(beanProperty);
            }
        }

    @Override
    public void resolve(SerializerProvider provider) throws JsonMappingException {
            System.out.println("后去到了序列化provider");
    }
}
