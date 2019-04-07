package com.sean.common;

import com.sean.exception.ParamException;
import com.sean.exception.PermissionException;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.builder.ParameterExpression;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Administrator on 2019/4/4.
 */
@Slf4j
public class SpringExceptionResolver implements HandlerExceptionResolver {

    @Override
    public ModelAndView resolveException(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) {
        String url = httpServletRequest.getRequestURL().toString();
        ModelAndView mv;
        String defaultMsg = "System error";

        //系统中所有的接口请求都用.json结尾，页面请求都用.page结尾
        if(url.endsWith(".json"))
        {
            if(e instanceof PermissionException || e instanceof ParamException)
            {
                JsonData result = JsonData.fail(e.getMessage());
                mv = new ModelAndView("jsonView",result.toMap());
            }else
            {
                log.error("unknown json exception ,url:"+url,e);
                JsonData result = JsonData.fail(defaultMsg);
                mv = new ModelAndView("jsonView",result.toMap());
            }
        }else if(url.endsWith(".page"))
        {
            log.error("unknown page exception ,url:"+url,e);
           JsonData result = JsonData.fail(defaultMsg);
            mv = new ModelAndView("exception",result.toMap());
        }else
        {
            log.error("unknown  exception ,url:"+url,e);
            JsonData result = JsonData.fail(defaultMsg);
            mv = new ModelAndView("jsonView",result.toMap());
        }
        return mv;
    }
}
