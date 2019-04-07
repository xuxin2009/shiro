package com.sean.util;

import lombok.extern.slf4j.Slf4j;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.ser.impl.SimpleFilterProvider;
import org.codehaus.jackson.type.TypeReference;

/**
 * Created by Administrator on 2019/4/6.
 */
@Slf4j
public class JsonMapper {

    private static ObjectMapper objectMapper = new ObjectMapper();
    static {
        objectMapper.disable(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES);
        objectMapper.configure(SerializationConfig.Feature.FAIL_ON_EMPTY_BEANS,true);
        objectMapper.setFilters(new SimpleFilterProvider().setFailOnUnknownId(false));
        objectMapper.setSerializationInclusion(JsonSerialize.Inclusion.NON_EMPTY);
    }

    public static <T> String object2String(T object)
    {
        if(object == null)
        {
            return null;
        }
        try {
            return (object instanceof String ? (String) object : objectMapper.writeValueAsString(object));
        }catch (Exception e)
        {
            log.warn("parse Object to String exception  ,error:{}",e);
            return null;
        }
    }

    public static <T> T string2Object(String string, TypeReference<T> typeReference)
    {
        if(string == null || typeReference == null)
        {
            return null;
        }
        try{
            return (T)(typeReference.getType().equals(String.class) ? string : objectMapper.readValue(string,typeReference));
        }catch (Exception e)
        {
            log.warn("parse string to object exception ,string:{}, TypeReference<T> ,error:{}",string,typeReference.getType(),e);
            return null;
        }
    }
}
