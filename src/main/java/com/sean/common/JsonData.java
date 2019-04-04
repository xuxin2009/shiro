package com.sean.common;


import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2019/4/4.
 */
@Getter
@Setter
public class JsonData {
    private String msg;

    private  Object data;

    private boolean ret;

    public JsonData(boolean ret)
    {
        this.ret = ret;
    }
    public static JsonData success(Object data,String msg)
    {
        JsonData jsonData = new JsonData(true);
        jsonData.data = data;
        jsonData.msg = msg;
        return jsonData;
    }

    public static JsonData success(Object data)
    {
        JsonData jsonData = new JsonData(true);
        jsonData.data = data;
        return jsonData;
    }

    public static JsonData success()
    {
        JsonData jsonData = new JsonData(true);
        return jsonData;
    }
    public static JsonData fail(String msg)
    {
        JsonData jsonData = new JsonData(false);
        jsonData.msg = msg;
        return jsonData;
    }
    public static JsonData fail() {
        JsonData jsonData = new JsonData(false);
        return jsonData;
    }

    public Map<String,Object> toMap()
    {
        Map<String,Object> result = new HashMap<>();
        result.put("ret",ret);
        result.put("msg",msg);
        result.put("data",data);
        return result;
    }
}
