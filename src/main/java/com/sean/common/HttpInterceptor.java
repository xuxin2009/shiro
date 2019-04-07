package com.sean.common;

import com.sean.util.JsonMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * Created by Administrator on 2019/4/6.
 */
@Slf4j
public class HttpInterceptor extends HandlerInterceptorAdapter{

    private  static final String STARTTIME = "requestStartTime";
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String url = request.getRequestURI().toString();
        Map requestParam = request.getParameterMap();
        long startTime = System.currentTimeMillis();
        request.setAttribute(STARTTIME,startTime);
        log.warn("request start-->url:{},param:{}",url, JsonMapper.object2String(requestParam));
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        super.postHandle(request, response, handler, modelAndView);
        String url = request.getRequestURI().toString();
        Map requestParam = request.getParameterMap();
        long endTime = System.currentTimeMillis();
        long startTime =(long) request.getAttribute(STARTTIME);
        log.warn("request postHandle-->url:{},param:{},useTime:{}",url, JsonMapper.object2String(requestParam),endTime-startTime);

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        super.afterCompletion(request, response, handler, ex);
        String url = request.getRequestURI().toString();
        Map requestParam = request.getParameterMap();
        long endTime = System.currentTimeMillis();
        long startTime =(long) request.getAttribute(STARTTIME);
        log.warn("request finish-->url:{},param:{},useTime:{}",url, JsonMapper.object2String(requestParam),endTime-startTime);
    }
}
