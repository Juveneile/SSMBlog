package com.hxx.service;

import com.hxx.entity.BlogType;

import java.util.List;
import java.util.Map;

/**
 * @ClassName BlogTypeService
 * @Description //TODO
 * @Author haoxx
 * @Date 2019/4/4 - 16:01
 * @Version 1.0
 */
public interface BlogTypeService {
    /**
     *@author haoxx
     *@Description //TODO 查询所有博客类型，以及对应的博客数量
     *@Date 2019/4/4 - 16:01
     *@Param []
     *@return java.util.List<com.hxx.entity.BlogType>
     */
    public List<BlogType> countList();

    /**
     *@author haoxx
     *@Description //TODO 分页查询博客类别信息
     *@Date 2019/4/8 - 13:25
     *@Param [map]
     *@return java.util.List<com.hxx.entity.BlogType>
     */
    public List<BlogType> list(Map<String,Object> map);

    /**
     *@author haoxx
     *@Description //TODO 获取总记录数
     *@Date 2019/4/8 - 13:25
     *@Param [map]
     *@return java.lang.Long
     */
    public Long getTotal(Map<String,Object> map);

    /**
     *@author haoxx
     *@Description //TODO 添加博客类别信息
     *@Date 2019/4/8 - 13:36
     *@Param [blogType]
     *@return java.lang.Integer
     */
    public Integer add(BlogType blogType);

    /**
     *@author haoxx
     *@Description //TODO 修改博客类别信息
     *@Date 2019/4/8 - 13:36
     *@Param [blogType]
     *@return java.lang.Integer
     */
    public Integer update(BlogType blogType);

    /**
     *@author haoxx
     *@Description //TODO 删除博客类别信息
     *@Date 2019/4/8 - 13:36
     *@Param [id]
     *@return java.lang.Integer
     */
    public Integer delete(Integer id);
}
