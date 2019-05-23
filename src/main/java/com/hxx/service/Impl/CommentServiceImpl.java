package com.hxx.service.Impl;

import com.hxx.dao.CommentDao;
import com.hxx.entity.Comment;
import com.hxx.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @ClassName CommentServiceImpl
 * @Description //TODO 评论Service实现类
 * @Author haoxx
 * @Date 2019/4/7 - 16:35
 * @Version 1.0
 */

@Service("commentService")
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentDao commentDao;

    public List<Comment> list(Map<String, Object> map) {
        return commentDao.list(map);
    }

    public int add(Comment comment) {
        return commentDao.add(comment);
    }

    public Long getTotal(Map<String,Object> map){
       return commentDao.getTotal(map);
    }

    public int update(Comment comment){
        return commentDao.update(comment);
    }

    public Integer delete(Integer id){
        return commentDao.delete(id);
    }

}
