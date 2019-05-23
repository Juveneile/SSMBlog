package com.hxx.service.Impl;

import com.hxx.dao.BlogDao;
import com.hxx.entity.Blog;
import com.hxx.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @ClassName BlogServiceImpl
 * @Description //TODO 博客Service实现类
 * @Author haoxx
 * @Date 2019/4/4 - 16:05
 * @Version 1.0
 */
@Service("blogService")
public class BlogServiceImpl implements BlogService {

    @Autowired
    private BlogDao blogDao;

    public List<Blog> countList() {
        return blogDao.countList();
    }

    public List<Blog> list(Map<String, Object> map) {
        return blogDao.list(map);
    }

    public Long getTotal(Map<String, Object> map) {
        return blogDao.getTotal(map);
    }

    public Blog findById(Integer id) {
        return blogDao.findById(id);
    }

    public Integer update(Blog blog) {
        return blogDao.update(blog);
    }

    public Blog getLastBlog(Integer id) {
        return blogDao.getLastBlog(id);
    }

    public Blog getNextBlog(Integer id) {
        return blogDao.getNextBlog(id);
    }

    public Integer add(Blog blog) {
        return blogDao.add(blog);
    }

    public Integer delete(Integer id) {
        return blogDao.delete(id);
    }

    public Integer getBlogByTypeId(Integer typeId) {
        return blogDao.getBlogByTypeId(typeId);
    }
}
