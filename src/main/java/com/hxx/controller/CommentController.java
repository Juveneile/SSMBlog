package com.hxx.controller;

import com.hxx.entity.Blog;
import com.hxx.entity.Comment;
import com.hxx.service.BlogService;
import com.hxx.service.CommentService;
import com.hxx.util.ResponseUtil;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @ClassName CommentController
 * @Description //TODO 评论Controller层
 * @Author haoxx
 * @Date 2019/4/7 - 17:01
 * @Version 1.0
 */

@Controller
@RequestMapping("/comment")
public class CommentController {
    @Autowired
    private CommentService commentService;

    @Autowired
    private BlogService blogService;

    /**
     * @return java.lang.String
     * @author haoxx
     * @Description //TODO  添加或者修改评论
     * @Date 2019/4/7 - 17:03
     * @Param [comment, imageCode, request, response, session]
     */
    @RequestMapping("/save")
    public String save(Comment comment, @RequestParam("imageCode") String imageCode, HttpServletRequest request,
                       HttpServletResponse response, HttpSession session) throws Exception {
        String sRand = (String) session.getAttribute("sRand");
        JSONObject result = new JSONObject();
        int resultTotal = 0;
        if (!imageCode.equals(sRand)) {
            result.put("success", false);
            result.put("errorInfo", "验证码填写错误!");
        } else {
            String userIp = request.getRemoteAddr(); // 获取用户IP
            comment.setUserIp(userIp);
            if (comment.getId() == null) {
                resultTotal = commentService.add(comment);
                // 博客的回复次数加1
                Blog blog = blogService.findById(comment.getBlog().getId());
                blog.setReplyHit(blog.getReplyHit() + 1);
                blogService.update(blog);
            } else {

            }
        }
        if (resultTotal > 0) {
            result.put("success", true);
        } else {
            result.put("success", false);
        }
        ResponseUtil.write(response, result);
        return null;
    }
}
