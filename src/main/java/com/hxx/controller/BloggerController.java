package com.hxx.controller;

import com.hxx.entity.Blogger;
import com.hxx.service.BloggerService;
import com.hxx.util.CryptographyUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * @ClassName BloggerController
 * @Description //TODO 博主Controller层
 * @Author haoxx
 * @Date 2019/4/3 - 15:33
 * @Version 1.0
 */
@Controller
public class BloggerController {
    @Autowired
    private BloggerService bloggerService;
    @RequestMapping("/login")
    public String login(Blogger blog, HttpServletRequest request){
        //shiro框架的安全验证工具
        Subject subject= SecurityUtils.getSubject();
        //生成认证令牌  blog 为用户输入内容
        UsernamePasswordToken token=new UsernamePasswordToken(blog.getUserName(), CryptographyUtil.md5(blog.getPassword(), "hxx"));
        try{
            subject.login(token); // 登录验证
            return "redirect:/back.jsp";
        }catch(Exception e){
            e.printStackTrace();
            request.setAttribute("blogger", blog);
            request.setAttribute("errorInfo", "用户名或者密码错误！");
            return "login";
        }
    }
    @RequestMapping("/main")
    public String main(){
        return "admin/main";
    }

    @RequestMapping("/userLogin")
    public String userLogin(){
        return "login";
    }
    /**
     *@author haoxx
     *@Description //TODO  关于博主
     *@Date 2019/4/4 - 22:05
     *@Param []
     *@return org.springframework.web.servlet.ModelAndView
     */
    @RequestMapping("/aboutMe")
    public ModelAndView aboutMe()throws Exception{
        ModelAndView mav=new ModelAndView();
        mav.addObject("pageTitle", "关于博主");
        mav.addObject("mainPage", "foreground/blogger/info.jsp");
        mav.setViewName("mainTemp");
        return mav;
    }
}
