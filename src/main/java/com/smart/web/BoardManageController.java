package com.smart.web;

import com.smart.cons.CommonConstant;
import com.smart.dao.Page;
import com.smart.domain.Board;
import com.smart.domain.Post;
import com.smart.domain.Topic;
import com.smart.domain.User;
import com.smart.service.ForumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * Created by lizhiping03 on 2018/9/13.
 */

@Controller
public class BoardManageController extends BaseController {

    private ForumService forumService;

    @Autowired
    public void setForumService(ForumService forumService) {
        this.forumService = forumService;
    }

    @RequestMapping(value = "/board/listBoardTopics-{boardId}", method = RequestMethod.GET)
    public ModelAndView listBoardTopics(@PathVariable Integer boardId, @RequestParam(value = "pageNo", required = false) Integer pageNo) {
        ModelAndView mav = new ModelAndView();
        Board board = forumService.getBoardById(boardId);
        pageNo = pageNo == null ? 1 : pageNo;
        Page pagedTopic = forumService.getPageTopics(boardId, pageNo, CommonConstant.PAGE_SIZE);
        mav.addObject("board", board);
        mav.addObject("pagedTopic", pagedTopic);
        mav.setViewName("/listBoardTopics");
        return mav;
    }

    @RequestMapping(value = "/board/addTopicPage-{boardId}", method = RequestMethod.GET)
    public ModelAndView addTopicPage(@PathVariable Integer boardId) {
        ModelAndView mav = new ModelAndView();
        mav.addObject("boardId", boardId);
        mav.setViewName("/addTopic");
        return mav;
    }

    @RequestMapping(value = "/board/addTopic", method = RequestMethod.POST)
    public String addTopic(HttpServletRequest request, Topic topic) {
        User user = getSessionUser(request);
        topic.setUser(user);
        Date now = new Date();
        topic.setCreateTime(now);
        topic.setLastPost(now);
        forumService.addTopic(topic);
        String targetUrl = "/board/listBoardTopics-" + topic.getBoardId() + ".html";
        return "redirect:" + targetUrl;
    }

    @RequestMapping(value = "/board/listTopicPosts-{topicId}", method = RequestMethod.GET)
    public ModelAndView listTopicPosts(@PathVariable Integer topicId, @RequestParam(value = "pageNo", required = false) Integer pageNo) {
        ModelAndView mav = new ModelAndView();
        Topic topic = forumService.getTopicByTopicId(topicId);
        pageNo = pageNo == null ? 1 : pageNo;
        Page pagedTopic = forumService.getPagedPosts(topicId, pageNo, CommonConstant.PAGE_SIZE);
        mav.addObject("topic", topic);
        mav.addObject("pagedPost", pagedTopic);
        mav.setViewName("/listTopicPosts");
        return mav;
    }

    @RequestMapping(value = "/board/addPost")
    public String addPost(HttpServletRequest request, Post post) {
        post.setCreateTime(new Date());
        post.setUser(getSessionUser(request));
        Topic topic = new Topic();
        int topicId = Integer.valueOf(request.getParameter("topicId"));
        if (topicId > 0) {
            topic.setTopicId(topicId);
            post.setTopic(topic);
        }
        forumService.addPost(post);
        String targetUrl = "/board/listTopicPosts-" + post.getTopic().getTopicId() + ".html";
        return "redirect:" + targetUrl;
    }

    @RequestMapping(value = "/board/removeBoard", method = RequestMethod.GET)
    public String removeBoard(@RequestParam("boardIds") String boardIds) {
        String[] arrIds = boardIds.split(",");
        for (int i = 0; i < arrIds.length; i++) {
            forumService.removeBoard(new Integer(arrIds[i]));
        }
        String targetUrl = "/index.html";
        return "redirect:" + targetUrl;
    }

    @RequestMapping(value = "/board/removeTopic", method = RequestMethod.GET)
    public String removeTopic(@RequestParam("topicIds") String topicIds, @RequestParam("boardId") String boardId) {
        String[] arrIds = topicIds.split(",");
        for (int i = 0; i < arrIds.length; i++) {
            forumService.removeTopic(new Integer(arrIds[i]));
        }
        String targetUrl = "/board/listBoardTopics-" + boardId + ".html";
        return "redirect:" + targetUrl;
    }

    @RequestMapping(value = "/board/makeDigestTopic", method = RequestMethod.GET)
    public String makeDigestTopic(@RequestParam("topicIds") String topicIds, @RequestParam("boardId") String boardId) {
        String[] arrIds = topicIds.split(",");
        for (int i = 0; i < arrIds.length; i++) {
            forumService.makeDigestTopic(new Integer(arrIds[i]));
        }
        String targetUrl = "/board/listBoardTopics-" + boardId + ".html";
        return "redirect:" + targetUrl;
    }
}
