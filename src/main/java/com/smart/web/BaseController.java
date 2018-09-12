package com.smart.web;

import com.smart.cons.CommonConstant;
import com.smart.domain.User;
import org.springframework.util.Assert;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by lizhiping03 on 2018/9/12.
 */
public class BaseController {

    protected static final String ERROR_MSG_KEY = "errorMsg";

    protected User getSessionUser(HttpServletRequest request) {
        return (User) request.getSession().getAttribute(CommonConstant.USER_CONTEXT);
    }

    protected void setSessionUser(HttpServletRequest request, User user) {
        request.getSession().setAttribute(CommonConstant.USER_CONTEXT, user);
    }

    public final String getAppbaseUrl(HttpServletRequest request, String url) {
        Assert.hasLength(url, "url cann't be null");
        Assert.isTrue(url.startsWith("/"), "must start with '/'");
        return request.getContextPath() + url;
    }
}
