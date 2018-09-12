package com.smart.web;

import com.smart.domain.User;
import com.smart.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by lizhiping03 on 2018/9/12.
 */

@Controller
@RequestMapping("/login")
public class LoginController extends BaseController {

    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping("/doLogin")
    public ModelAndView login(HttpServletRequest request, User user) {
        User dbUser = userService.getUserByUserName(user.getUserName());
        ModelAndView mav = new ModelAndView();
//        mav
        return mav;
    }
}
