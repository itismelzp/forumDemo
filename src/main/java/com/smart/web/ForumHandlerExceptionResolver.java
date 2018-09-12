package com.smart.web;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by lizhiping03 on 2018/9/12.
 */
public class ForumHandlerExceptionResolver extends
        SimpleMappingExceptionResolver {
    protected ModelAndView doResolveException(HttpServletRequest httpRequest, HttpServletResponse httpResponse,
            Object o, Exception e) {
        return super.doResolveException(httpRequest, httpResponse, o, e);
    }
}
