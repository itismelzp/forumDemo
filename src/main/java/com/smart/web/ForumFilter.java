package com.smart.web;


import com.smart.cons.CommonConstant;
import com.smart.domain.User;
import org.springframework.util.StringUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * Created by lizhiping03 on 2018/9/12.
 */
public class ForumFilter implements Filter {

    private static final String FILTERED_REQUEST = "@@session_context_filtered_request";

    // ① 不需要登录即可访问的URI资源
    private static final String[] INHERENT_ESCAPE_URIS = {
            "/index.html", "/register.html", "/login/doLogin.html",
            "/index.jsp", "/register.jsp", "/login.jsp",
            "/board/listBoardTopics-","/board/listTopicPosts-"
    };

    // ② 执行过滤
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        if (request != null && request.getAttribute(FILTERED_REQUEST) != null) {
            chain.doFilter(request, response);
        } else {
            request.setAttribute(FILTERED_REQUEST, Boolean.TRUE);
            HttpServletRequest httpRequest = (HttpServletRequest) request;
            User userContext = getSessionUser(httpRequest);

            if (userContext == null && !iSURILogin(httpRequest.getRequestURI(), httpRequest)) {
                String toUrl = httpRequest.getRequestURL().toString();
                if (!StringUtils.isEmpty(httpRequest.getQueryString().toString())) {
                    toUrl += "?" + httpRequest.getQueryString();
                }

                httpRequest.getSession().setAttribute(CommonConstant.LOGIN_TO_URL, toUrl);
                request.getRequestDispatcher("/login.jsp").forward(request, response);
                return;
            }
            chain.doFilter(request, response);
        }
    }

    private boolean iSURILogin(String requestURI, HttpServletRequest httpRequest) {
        if (httpRequest.getContextPath().equalsIgnoreCase(requestURI)
                || (httpRequest.getContextPath() + "/").equalsIgnoreCase(requestURI)) {
            return true;
        }
        for (String uri : INHERENT_ESCAPE_URIS) {
            if (requestURI != null && requestURI.contains(uri)) {
                return true;
            }
        }
        return false;
    }

    protected User getSessionUser(HttpServletRequest request) {
        return (User) request.getSession().getAttribute(CommonConstant.USER_CONTEXT);
    }

    public void init(FilterConfig filterConfig) throws ServletException {

    }

    public void destroy() {

    }
}
