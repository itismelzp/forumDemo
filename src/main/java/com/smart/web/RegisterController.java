package com.smart.web;

import com.smart.domain.User;
import com.smart.exception.UserExistException;
import com.smart.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by lizhiping03 on 2018/9/12.
 */

@Controller
public class RegisterController extends BaseController {

    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ModelAndView register(HttpServletRequest request, User user) {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("/success");
        try {
            userService.register(user);
        } catch (UserExistException e) {
            e.printStackTrace();
            mav.addObject("errorMsg", "username is exsited, please use another name");
            mav.setViewName("forward:/register.jsp");
        }
        setSessionUser(request, user);
        return mav;
    }

}
