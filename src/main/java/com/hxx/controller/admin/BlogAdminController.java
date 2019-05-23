package com.hxx.controller.admin;

import com.hxx.entity.Blog;
import com.hxx.entity.PageBean;
import com.hxx.lucene.BlogIndex;
import com.hxx.service.BlogService;
import com.hxx.util.ResponseUtil;
import com.hxx.util.StringUtil;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName BlogAdminController
 * @Description //TODO
 * @Author haoxx
 * @Date 2019/4/7 - 19:37
 * @Version 1.0
 */

@Controller
@RequestMapping("/admin/blog")
public class BlogAdminController {
    @Autowired
    private BlogService blogService;

    private BlogIndex blogIndex=new BlogIndex();
    /**
     *@author haoxx
     *@Description //TODO 添加或者修改博客信息
     *@Date 2019/4/7 - 19:38
     *@Param [blog, response]
     *@return java.lang.String
     */
    @RequestMapping("/save")
    public String save(Blog blog, HttpServletResponse response)throws Exception{
        int resultTotal=0;
        if(blog.getId()==null){
            resultTotal=blogService.add(blog);
            blogIndex.addIndex(blog);
        }else{
            resultTotal=blogService.update(blog);
          //  blogIndex.updateIndex(blog); // 更新博客索引
        }
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
     *@Description //TODO 分页查询博客信息
     *@Date 2019/4/8 - 12:22
     *@Param [page, rows, s_blog, response]
     *@return java.lang.String
     */
    @RequestMapping("/list")
    public String list(@RequestParam(value="page",required=false)String page, @RequestParam(value="rows",required=false)String rows, Blog s_blog, HttpServletResponse response)throws Exception{
        PageBean pageBean=new PageBean(Integer.parseInt(page),Integer.parseInt(rows));
        Map<String,Object> map=new HashMap<String,Object>();
        map.put("title", StringUtil.formatLike(s_blog.getTitle()));
        map.put("start", pageBean.getStart());
        map.put("size", pageBean.getPageSize());
        List<Blog> blogList=blogService.list(map);
        Long total=blogService.getTotal(map);
        JSONObject result=new JSONObject();
        JsonConfig jsonConfig=new JsonConfig();
        jsonConfig.registerJsonValueProcessor(java.util.Date.class, new DateJsonValueProcessor("yyyy-MM-dd"));
        JSONArray jsonArray=JSONArray.fromObject(blogList, jsonConfig);
        result.put("rows", jsonArray);
        result.put("total", total);
        ResponseUtil.write(response, result);
        return null;
    }
    /**
     *@author haoxx
     *@Description //TODO 博客信息删除
     *@Date 2019/4/8 - 12:50
     *@Param [ids, response]
     *@return java.lang.String
     */
    @RequestMapping("/delete")
    public String delete(@RequestParam(value="ids",required=false)String ids,HttpServletResponse response)throws Exception{
        String []idsStr=ids.split(",");
        //批量删除
        for(int i=0;i<idsStr.length;i++){
            //删除博客
            blogService.delete(Integer.parseInt(idsStr[i]));
            //删除博客索引
            blogIndex.deleteIndex(idsStr[i]);
        }
        //传递数据
        JSONObject result=new JSONObject();
        result.put("success", true);
        ResponseUtil.write(response, result);
        return null;
    }
    /**
     *@author haoxx
     *@Description //TODO 通过Id查找实体
     *@Date 2019/4/8 - 13:08
     *@Param [id, response]
     *@return java.lang.String
     */
    @RequestMapping("/findById")
    public String findById(@RequestParam(value="id")String id,HttpServletResponse response)throws Exception{
        Blog blog=blogService.findById(Integer.parseInt(id));
        JSONObject result=JSONObject.fromObject(blog);
        ResponseUtil.write(response, result);
        return null;
    }
}
