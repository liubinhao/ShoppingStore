package com.shop.dev.exception;

import com.sun.xml.internal.ws.handler.HandlerException;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @ClassName GlobalExceptionResolver
 * @Author 刘树青
 * @Date 2018/11/16 14:46
 * @Version 1.0
 */
@Component
public class GlobalExceptionResolver implements HandlerExceptionResolver {
    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        if (ex instanceof MyException) {
            ModelAndView mv = new ModelAndView("redirect:https://api.bilibili.com/x/web-interface/nav?callback=jqueryCallback_bili_922395052746579&jsonp=jsonp&_=1542352028888");
            return mv;
        }
        return null;
    }
}
