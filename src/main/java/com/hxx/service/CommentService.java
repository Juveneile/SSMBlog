package com.hxx.service;

import com.hxx.entity.Comment;

import java.util.List;
import java.util.Map;

/**
 * @ClassName CommentService
 * @Description //TODO 评论Service接口
 * @Author haoxx
 * @Date 2019/4/7 - 16:34
 * @Version 1.0
 */
public interface CommentService {
    /**
     *@author haoxx
     *@Description //TODO  查询用户评论信息
     *@Date 2019/4/7 - 16:34
     *@Param [map]
     *@return java.util.List<com.hxx.entity.Comment>
     */
    public List<Comment> list(Map<String,Object> map);

    /**
     *@author haoxx
     *@Description //TODO  添加评论
     *@Date 2019/4/7 - 17:04
     *@Param [comment]
     *@return int
     */
    public int add(Comment comment);
    /**
     *@author haoxx
     *@Description //TODO 获取总记录数
     *@Date 2019/4/8 - 13:53
     *@Param [map]
     *@return java.lang.Long
     */
    public Long getTotal(Map<String,Object> map);

    /**
     *@author haoxx
     *@Description //TODO 修改评论
     *@Date 2019/4/8 - 13:59
     *@Param [comment]
     *@return int
     */
    public int update(Comment comment);

    /**
     *@author haoxx
     *@Description //TODO 删除博客类别信息
     *@Date 2019/4/8 - 14:26
     *@Param [id]
     *@return java.lang.Integer
     */
    public Integer delete(Integer id);
}
