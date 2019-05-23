package com.hxx.dao;

import com.hxx.entity.Blog;

import java.util.List;
import java.util.Map;

/**
 * @ClassName BlogDao
 * @Description //TODO 博客Dao接口
 * @Author haoxx
 * @Date 2019/4/4 - 15:54
 * @Version 1.0
 */
public interface BlogDao {
    /**
     *@author haoxx
     *@Description //TODO 根据日期分月分组查询
     *@Date 2019/4/4 - 15:55
     *@Param []
     *@return java.util.List<com.hxx.entity.Blog>
     */
    public List<Blog> countList();
    /**
     *@author haoxx
     *@Description //TODO 分页查询博客
     *@Date 2019/4/4 - 16:35
     *@Param [map]
     *@return java.util.List<com.hxx.entity.Blog>
     */
    public List<Blog> list(Map<String,Object> map);
    /**
     *@author haoxx
     *@Description //TODO 获取总记录数
     *@Date 2019/4/4 - 16:36
     *@Param [map]
     *@return java.lang.Long
     */
    public Long getTotal(Map<String,Object> map);

    /**
     *@author haoxx
     *@Description //TODO 根据id查找实体
     *@Date 2019/4/7 - 15:39
     *@Param [id]
     *@return com.hxx.entity.Blog
     */
    public Blog findById(Integer id);

    /**
     *@author haoxx
     *@Description //TODO  更新博客信息
     *@Date 2019/4/7 - 15:39
     *@Param [blog]
     *@return java.lang.Integer
     */
    public Integer update(Blog blog);

    /**
     *@author haoxx
     *@Description //TODO 获取上一个博客
     *@Date 2019/4/7 - 16:13
     *@Param [id]
     *@return com.hxx.entity.Blog
     */
    public Blog getLastBlog(Integer id);

    /**
     *@author haoxx
     *@Description //TODO 获取下一个博客
     *@Date 2019/4/7 - 16:13
     *@Param [id]
     *@return com.hxx.entity.Blog
     */
    public Blog getNextBlog(Integer id);

    /**
     *@author haoxx
     *@Description //TODO 添加博客信息
     *@Date 2019/4/7 - 19:39
     *@Param [blog]
     *@return java.lang.Integer
     */
    public Integer add(Blog blog);

    /**
     *@author haoxx
     *@Description //TODO 删除博客信息
     *@Date 2019/4/8 - 12:51
     *@Param [id]
     *@return java.lang.Integer
     */
    public Integer delete(Integer id);

    /**
     *@author haoxx
     *@Description //TODO 查询指定的博客类别下的博客数量
     *@Date 2019/4/8 - 13:38
     *@Param [typeId]
     *@return java.lang.Integer
     */
    public Integer getBlogByTypeId(Integer typeId);
}
