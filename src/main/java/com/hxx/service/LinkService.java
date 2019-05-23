package com.hxx.service;

import com.hxx.entity.Link;

import java.util.List;
import java.util.Map;

/**
 * @ClassName LinkService
 * @Description //TODO 友情链接Service接口
 * @Author haoxx
 * @Date 2019/4/3 - 22:14
 * @Version 1.0
 */
public interface LinkService {
    /**
     *@author haoxx
     *@Description //TODO  查找友情链接信息
     *@Date 2019/4/3 - 22:15
     *@Param [map]
     *@return java.util.List<com.hxx.entity.Link>
     */
    public List<Link> list(Map<String,Object> map);

    /**
     *@author haoxx
     *@Description //TODO 获取总记录数
     *@Date 2019/4/8 - 15:10
     *@Param [map]
     *@return java.lang.Long
     */
    public Long getTotal(Map<String,Object> map);
    /**
     *@author haoxx
     *@Description //TODO 添加友情链接信息
     *@Date 2019/4/8 - 15:08
     *@Param [link]
     *@return java.lang.Integer
     */
    public Integer add(Link link);

    /**
     *@author haoxx
     *@Description //TODO 修改友情链接信息
     *@Date 2019/4/8 - 15:08
     *@Param [link]
     *@return java.lang.Integer
     */
    public Integer update(Link link);

    /**
     *@author haoxx
     *@Description //TODO 删除友情链接信息
     *@Date 2019/4/8 - 15:08
     *@Param [id]
     *@return java.lang.Integer
     */
    public Integer delete(Integer id);
}
