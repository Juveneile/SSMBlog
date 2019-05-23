package com.hxx.controller.admin;

import com.hxx.entity.BlogType;
import com.hxx.entity.PageBean;
import com.hxx.service.BlogService;
import com.hxx.service.BlogTypeService;
import com.hxx.util.ResponseUtil;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName BlogTypeAdminController
 * @Description //TODO 管理员博客类别Controller层
 * @Author haoxx
 * @Date 2019/4/8 - 13:22
 * @Version 1.0
 */
@Controller
@RequestMapping("/admin/blogType")
public class BlogTypeAdminController {
    @Autowired
    private BlogTypeService blogTypeService;
    @Autowired
    private BlogService blogService;
    @RequestMapping("/list")
    public String list(@RequestParam(value="page",required=false)String page, @RequestParam(value="rows",required=false)String rows, HttpServletResponse response)throws Exception{
        PageBean pageBean=new PageBean(Integer.parseInt(page),Integer.parseInt(rows));
        Map<String,Object> map=new HashMap<String,Object>();
        map.put("start", pageBean.getStart());
        map.put("size", pageBean.getPageSize());
        List<BlogType> blogTypeList=blogTypeService.list(map);
        Long total=blogTypeService.getTotal(map);
        JSONObject result=new JSONObject();
        JSONArray jsonArray=JSONArray.fromObject(blogTypeList);
        result.put("rows", jsonArray);
        result.put("total", total);
        ResponseUtil.write(response, result);
        return null;
    }
    /**
     *@author haoxx
     *@Description //TODO 添加或者修改博客类别信息
     *@Date 2019/4/8 - 13:34
     *@Param [blogType, response]
     *@return java.lang.String
     */
    @RequestMapping("/save")
    public String save(BlogType blogType,HttpServletResponse response)throws Exception{
        int resultTotal=0;
        if(blogType.getId()==null){
            resultTotal=blogTypeService.add(blogType);
        }else{
            resultTotal=blogTypeService.update(blogType);
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
     *@Description //TODO 博客类别信息删除
     *@Date 2019/4/8 - 13:35
     *@Param [ids, response]
     *@return java.lang.String
     */
    @RequestMapping("/delete")
    public String delete(@RequestParam(value="ids",required=false)String ids,HttpServletResponse response)throws Exception{
        String []idsStr=ids.split(",");
        JSONObject result=new JSONObject();
        for(int i=0;i<idsStr.length;i++){
            if(blogService.getBlogByTypeId(Integer.parseInt(idsStr[i]))>0){
                result.put("exist", "博客类别下有博客，不能删除！");
            }else{
                blogTypeService.delete(Integer.parseInt(idsStr[i]));
            }
        }
        result.put("success", true);
        ResponseUtil.write(response, result);
        return null;
    }
}
