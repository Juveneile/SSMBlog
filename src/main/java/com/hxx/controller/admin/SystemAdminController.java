package com.hxx.controller.admin;

import com.hxx.entity.Blog;
import com.hxx.entity.BlogType;
import com.hxx.entity.Blogger;
import com.hxx.entity.Link;
import com.hxx.service.BlogService;
import com.hxx.service.BlogTypeService;
import com.hxx.service.BloggerService;
import com.hxx.service.LinkService;
import com.hxx.util.ResponseUtil;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.support.RequestContextUtils;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @ClassName SystemAdminController
 * @Description //TODO 管理员系统Controller层
 * @Author haoxx
 * @Date 2019/4/8 - 15:36
 * @Version 1.0
 */

@Controller
@RequestMapping("/admin/system")
public class SystemAdminController {

    @Autowired
    private BloggerService bloggerService;

    @Autowired
    private LinkService linkService;

    @Autowired
    private BlogTypeService blogTypeService;

    @Autowired
    private BlogService blogService;

    /**
     *@author haoxx
     *@Description //TODO 刷新系统缓存
     *@Date 2019/4/8 - 15:38
     *@Param [request, response]
     *@return java.lang.String
     */
    @RequestMapping("/refreshSystem")
    public String refreshSystem(HttpServletRequest request, HttpServletResponse response)throws Exception{
        ServletContext application= RequestContextUtils.getWebApplicationContext(request).getServletContext();

        Blogger blogger=bloggerService.find(); // 获取博主信息
        blogger.setPassword(null);
        application.setAttribute("blogger", blogger);

        List<Link> linkList=linkService.list(null); // 查询所有的友情链接信息
        application.setAttribute("linkList", linkList);

        List<BlogType> blogTypeCountList=blogTypeService.countList(); // 查询博客类别以及博客的数量
        application.setAttribute("blogTypeCountList", blogTypeCountList);

        List<Blog> blogCountList=blogService.countList(); // 根据日期分组查询博客
        application.setAttribute("blogCountList", blogCountList);

        JSONObject result=new JSONObject();
        result.put("success", true);
        ResponseUtil.write(response, result);
        return null;
    }
}
