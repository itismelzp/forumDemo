package com.smart.web;

import com.smart.domain.Board;
import com.smart.domain.User;
import com.smart.service.ForumService;
import com.smart.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * Created by lizhiping03 on 2018/9/13.
 */

@Controller
public class ForumManageController extends BaseController {

    private ForumService forumService;

    private UserService userService;

    @Autowired
    public void setForumService(ForumService forumService) {
        this.forumService = forumService;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public ModelAndView listAllBoards() {
        ModelAndView mav = new ModelAndView();
        List<Board> boards = forumService.getAllBoards();
        mav.addObject("boards", boards);
        mav.setViewName("/listAllBoards");
        return mav;
    }

    @RequestMapping(value = "/forum/addBoardPage", method = RequestMethod.GET)
    public String addBoardPage() {
        return "/addBoard";
    }

    @RequestMapping(value = "/forum/addBoard", method = RequestMethod.POST)
    public String addBoard(Board board) {
        forumService.addBoard(board);
        return "/addBoardSuccess";
    }

    @RequestMapping(value = "/forum/setBoardManagerPage", method = RequestMethod.GET)
    public ModelAndView setBoardManagerPage() {
        ModelAndView mav = new ModelAndView();
        List<Board> boards = forumService.getAllBoards();
        List<User> users = userService.getAllUsers();
        mav.addObject("boards", boards);
        mav.addObject("users", users);
        mav.setViewName("/setBoardManager");
        return mav;
    }

    @RequestMapping(value = "/forum/setBoardManager", method = RequestMethod.POST)
    public ModelAndView setBoardManager(@RequestParam("userName") String userName, @RequestParam("boardId") String boardId) {
        ModelAndView mav = new ModelAndView();
        User user = userService.getUserByUserName(userName);
        if (user == null) {
            mav.addObject("errorMsg", "the userNmae(" + userName + ") is not exist");
            mav.setViewName("/fail");
        } else {
            Board board = forumService.getBoardById(Integer.parseInt(boardId));
            user.getManBoards().add(board);
            userService.update(user);
            mav.setViewName("/success");
        }
        return mav;
    }

    @RequestMapping(value = "/forum/userLockManagePage", method = RequestMethod.GET)
    public ModelAndView userLockManagePage(String context) {
        System.out.println("pathContext: "+context);
        ModelAndView mav = new ModelAndView();
        List<User> users = userService.getAllUsers();
        mav.addObject("users", users);
        mav.setViewName("/userLockManage");
        return mav;
    }

    @RequestMapping(value = "/forum/userLockManage", method = RequestMethod.POST)
    public ModelAndView userLockManage(@RequestParam("userName") String userName, @RequestParam("locked") String locked) {
        ModelAndView mav = new ModelAndView();
        User user = userService.getUserByUserName(userName);
        if (user == null) {
            mav.addObject("errorMsg", "the username(" + userName + ") is not exist");
            mav.setViewName("/fail");
        } else {
            user.setLocked(Integer.parseInt(locked));
            userService.update(user);
            mav.setViewName("/success");
        }
        return mav;
    }
}
