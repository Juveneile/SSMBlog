package com.hxx.controller.admin;

import com.hxx.entity.Comment;
import com.hxx.entity.PageBean;
import com.hxx.service.CommentService;
import com.hxx.util.ResponseUtil;
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
 * @ClassName CommentAdminController
 * @Description //TODO 管理员评论Controller层
 * @Author haoxx
 * @Date 2019/4/8 - 13:51
 * @Version 1.0
 */

@Controller
@RequestMapping("/admin/comment")
public class CommentAdminController {
    @Autowired
    private CommentService commentService;
    
    /**
     *@author haoxx
     *@Description //TODO  分页查询评论信息
     *@Date 2019/4/8 - 13:52
     *@Param [page, rows, state, response]
     *@return java.lang.String
     */
    @RequestMapping("/list")
    public String list(@RequestParam(value="page",required=false)String page, @RequestParam(value="rows",required=false)String rows, @RequestParam(value="state",required=false)String state, HttpServletResponse response)throws Exception{
        PageBean pageBean=new PageBean(Integer.parseInt(page),Integer.parseInt(rows));
        Map<String,Object> map=new HashMap<String,Object>();
        map.put("state", state); // 评论状态
        map.put("start", pageBean.getStart());
        map.put("size", pageBean.getPageSize());
        List<Comment> commentList=commentService.list(map);
        Long total=commentService.getTotal(map);
        JSONObject result=new JSONObject();
        JsonConfig jsonConfig=new JsonConfig();
        jsonConfig.registerJsonValueProcessor(java.util.Date.class, new DateJsonValueProcessor("yyyy-MM-dd"));
        JSONArray jsonArray=JSONArray.fromObject(commentList, jsonConfig);
        result.put("rows", jsonArray);
        result.put("total", total);
        ResponseUtil.write(response, result);
        return null;
    }
    /**
     *@author haoxx
     *@Description //TODO 评论审核
     *@Date 2019/4/8 - 13:58
     *@Param [ids, state, response]
     *@return java.lang.String
     */
    @RequestMapping("/review")
    public String review(@RequestParam(value="ids",required=false)String ids,@RequestParam(value="state",required=false)Integer state,HttpServletResponse response)throws Exception{
        String []idsStr=ids.split(",");
        for(int i=0;i<idsStr.length;i++){
            Comment comment=new Comment();
            comment.setId(Integer.parseInt(idsStr[i]));
            comment.setState(state);
            commentService.update(comment);
        }
        JSONObject result=new JSONObject();
        result.put("success", true);
        ResponseUtil.write(response, result);
        return null;
    }
    /**
     *@author haoxx
     *@Description //TODO 评论信息删除
     *@Date 2019/4/8 - 14:25
     *@Param [ids, response]
     *@return java.lang.String
     */
    @RequestMapping("/delete")
    public String delete(@RequestParam(value="ids",required=false)String ids,HttpServletResponse response)throws Exception{
        String []idsStr=ids.split(",");
        JSONObject result=new JSONObject();
        for(int i=0;i<idsStr.length;i++){
            commentService.delete(Integer.parseInt(idsStr[i]));
        }
        result.put("success", true);
        ResponseUtil.write(response, result);
        return null;
    }
}
