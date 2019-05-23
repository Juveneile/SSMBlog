package com.hxx.controller.admin;

import com.hxx.entity.Link;
import com.hxx.entity.PageBean;
import com.hxx.service.LinkService;
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
 * @ClassName LinkAdminController
 * @Description //TODO 管理员友情链接Controller层
 * @Author haoxx
 * @Date 2019/4/8 - 15:05
 * @Version 1.0
 */

@Controller
@RequestMapping("/admin/link")
public class LinkAdminController {
    @Autowired
    private LinkService linkService;

    /**
     *@author haoxx
     *@Description //TODO 分页查询友情链接信息
     *@Date 2019/4/8 - 15:06
     *@Param [page, rows, response]
     *@return java.lang.String
     */
    @RequestMapping("/list")
    public String list(@RequestParam(value="page",required=false)String page, @RequestParam(value="rows",required=false)String rows, HttpServletResponse response)throws Exception{
        PageBean pageBean=new PageBean(Integer.parseInt(page),Integer.parseInt(rows));
        Map<String,Object> map=new HashMap<String,Object>();
        map.put("start", pageBean.getStart());
        map.put("size", pageBean.getPageSize());
        List<Link> linkList=linkService.list(map);
        Long total=linkService.getTotal(map);
        JSONObject result=new JSONObject();
        JSONArray jsonArray=JSONArray.fromObject(linkList);
        result.put("rows", jsonArray);
        result.put("total", total);
        ResponseUtil.write(response, result);
        return null;
    }

    /**
     *@author haoxx
     *@Description //TODO 添加或者修改友情链接信息
     *@Date 2019/4/8 - 15:07
     *@Param [link, response]
     *@return java.lang.String
     */
    @RequestMapping("/save")
    public String save(Link link,HttpServletResponse response)throws Exception{
        int resultTotal=0;
        if(link.getId()==null){
            resultTotal=linkService.add(link);
        }else{
            resultTotal=linkService.update(link);
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
     *@Description //TODO 友情链接信息删除
     *@Date 2019/4/8 - 15:07
     *@Param [ids, response]
     *@return java.lang.String
     */
    @RequestMapping("/delete")
    public String delete(@RequestParam(value="ids",required=false)String ids,HttpServletResponse response)throws Exception{
        String []idsStr=ids.split(",");
        JSONObject result=new JSONObject();
        for(int i=0;i<idsStr.length;i++){
            linkService.delete(Integer.parseInt(idsStr[i]));
        }
        result.put("success", true);
        ResponseUtil.write(response, result);
        return null;
    }
}
