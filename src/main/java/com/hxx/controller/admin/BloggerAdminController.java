package com.hxx.controller.admin;

import com.hxx.entity.Blog;
import com.hxx.entity.Blogger;
import com.hxx.service.BloggerService;
import com.hxx.util.CryptographyUtil;
import com.hxx.util.DateUtil;
import com.hxx.util.ResponseUtil;
import net.sf.json.JSONObject;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;

/**
 * @ClassName BloggerAdminController
 * @Description //TODO 管理员博主Controller层
 * @Author haoxx
 * @Date 2019/4/3 - 15:34
 * @Version 1.0
 */
@Controller
@RequestMapping("/admin/blogger")
public class BloggerAdminController {
    @Autowired
    private BloggerService bloggerService;

    /**
     * @return java.lang.String
     * @author haoxx
     * @Description //TODO 后台分发界面
     * @Date 2019/4/7 - 20:02
     * @Param [target]
     */
    @RequestMapping("/dispatcher/{target}")
    public String dispatcher(@PathVariable("target") String target) {
        return "admin/" + target;
    }

    /**
     * @return java.lang.String
     * @author haoxx
     * @Description //TODO 查询博主信息
     * @Date 2019/4/8 - 14:45
     * @Param [response]
     */
    @RequestMapping("/find")
    public String find(HttpServletResponse response) throws Exception {
        Blogger blogger = bloggerService.find();
        JSONObject jsonObject = JSONObject.fromObject(blogger);
        ResponseUtil.write(response, jsonObject);
        return null;
    }

    /**
     * @return java.lang.String
     * @author haoxx
     * @Description //TODO 修改博主信息
     * @Date 2019/4/8 - 14:52
     * @Param [imageFile, blogger, request, response]
     */
    @RequestMapping("/save")
    public String save(@RequestParam("imageFile") MultipartFile imageFile, Blogger blogger, HttpServletRequest request, HttpServletResponse response) throws Exception {
        if (!imageFile.isEmpty()) {
            String filePath = request.getServletContext().getRealPath("/");
            //当前时间名做图片名称
            String imageName = DateUtil.getCurrentDateStr() + "." + imageFile.getOriginalFilename().split("\\.")[1];
            imageFile.transferTo(new File(filePath + "static/userImages/" + imageName));
            blogger.setImageName(imageName);
        }
        int resultTotal = bloggerService.update(blogger);
        StringBuffer result = new StringBuffer();
        if (resultTotal > 0) {
            result.append("<script language='javascript'>alert('修改成功！');</script>");
        } else {
            result.append("<script language='javascript'>alert('修改失败！');</script>");
        }
        ResponseUtil.write(response, result);
        return null;
    }
    /**
     *@author haoxx
     *@Description //TODO 修改博主密码
     *@Date 2019/4/8 - 15:31
     *@Param [newPassword, response]
     *@return java.lang.String
     */
    @RequestMapping("/modifyPassword")
    public String modifyPassword(String newPassword,HttpServletResponse response)throws Exception{
        Blogger blogger=new Blogger();
        blogger.setPassword(CryptographyUtil.md5(newPassword,"hxx"));
        int resultTotal=bloggerService.update(blogger);
        JSONObject result=new JSONObject();
        if(resultTotal>0){
            result.put("success", true);
        }else{
            result.put("success", false);
        }
        ResponseUtil.write(response, result);
        return null;
    }
    /**
     *@author haoxx
     *@Description //TODO 注销
     *@Date 2019/4/8 - 15:35
     *@Param []
     *@return java.lang.String
     */
    @RequestMapping("/logout")
    public String logout()throws Exception{
        SecurityUtils.getSubject().logout();
        return "redirect:/login.jsp";
    }
}
