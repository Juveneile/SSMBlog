package com.hxx.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.hxx.entity.Blog;
import com.hxx.entity.PageBean;
import com.hxx.service.BlogService;
import com.hxx.util.PageUtil;
import com.hxx.util.StringUtil;

/**
 * @ClassName IndexContrller
 * @Description //TODO
 * @Author haoxx
 * @Date 2019/4/4 - 16:33
 * @Version 1.0
 */
@Controller
@RequestMapping("/")
public class IndexContrller {
    @Autowired
    private BlogService blogService;
    /**
     *@author haoxx
     *@Description //TODO 请求主页
     *@Date 2019/4/4 - 16:34
     *@Param [page]
     *@return org.springframework.web.servlet.ModelAndView
     */
    @RequestMapping("/index")
    public ModelAndView index(@RequestParam(value="page",required=false)String page,@RequestParam(value="typeId",required=false)String typeId,@RequestParam(value="releaseDateStr",required=false)String releaseDateStr, HttpServletRequest request)throws Exception{
        ModelAndView mav=new ModelAndView();
        if(StringUtil.isEmpty(page)){
            page="1";
        }
        PageBean pageBean=new PageBean(Integer.parseInt(page),10);
        Map<String,Object> map=new HashMap<String,Object>();
        map.put("start", pageBean.getStart());
        map.put("size", pageBean.getPageSize());
        map.put("typeId", typeId);
        map.put("releaseDateStr", releaseDateStr);
        //从数据库获取本页的所有博客
        List<Blog> blogList=blogService.list(map);
        //循环获取每个博客的缩略图列表
        for(Blog blog:blogList){
            //存储一个博客中的图片连接的列表
            List<String> imageList=blog.getImageList();
            //获取文章内容
            String blogInfo=blog.getContent();
            //jsoup读取，选择图片链接部分
            Document doc=Jsoup.parse(blogInfo);
            Elements jpgs=doc.select("img[src$=.jpg]");
            //循环添加到图片列表
            for(int i=0;i<jpgs.size();i++){
                Element jpg=jpgs.get(i);
                imageList.add(jpg.toString());
                if(i==2){
                    break;
                }
            }
        }
        mav.addObject("blogList", blogList);
        StringBuffer param=new StringBuffer();
        if(StringUtil.isNotEmpty(typeId)){
            param.append("typeId="+typeId+"&");
        }
        if(StringUtil.isNotEmpty(releaseDateStr)){
            param.append("releaseDateStr="+releaseDateStr+"&");
        }
        //分页信息，设置伪静态
        mav.addObject("pageCode", PageUtil.genPagination(request.getContextPath()+"/index.html", blogService.getTotal(map), Integer.parseInt(page), 10, param.toString()));
        mav.addObject("pageTitle", "八棵树-博客");
        mav.addObject("mainPage", "foreground/blog/list.jsp");
        mav.setViewName("mainTemp");
        return mav;
    }
    /**
     *@author haoxx
     *@Description //TODO 源码下载
     *@Date 2019/4/4 - 22:15
     *@Param []
     *@return org.springframework.web.servlet.ModelAndView
     */
    @RequestMapping("/download")
    public ModelAndView download()throws Exception{
        ModelAndView mav=new ModelAndView();
        mav.addObject("pageTitle", "源码下载");
        mav.addObject("mainPage", "foreground/system/download.jsp");
        mav.setViewName("mainTemp");
        return mav;
    }
}
