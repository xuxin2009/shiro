package com.sean.controller;

import com.sean.common.JsonData;
import com.sean.param.TestVo;
import com.sean.util.BeanValidator;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.MapUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * Created by Administrator on 2019/4/3.
 */
@Controller
@RequestMapping("/test")
@Slf4j
public class TestController {

    @RequestMapping("/hello.json")
    @ResponseBody
    public JsonData hello()
    {
        return JsonData.fail("test JsonData");
    }

    @RequestMapping("/validate.json")
    @ResponseBody
    public JsonData validate(TestVo vo)
    {
        Map<String ,String> map = BeanValidator.validateObject(vo);
        if(MapUtils.isNotEmpty(map))
        {
            for (Map.Entry<String ,String > entry : map.entrySet()) {
                log.info("{}-->{}",entry.getKey(),entry.getValue());
            }
        }
        return JsonData.success();
    }
}
