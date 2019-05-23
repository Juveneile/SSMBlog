package com.hxx.service.Impl;

import com.hxx.dao.BlogTypeDao;
import com.hxx.entity.BlogType;
import com.hxx.service.BlogTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @ClassName BlogTypeServiceImpl
 * @Description //TODO 博客类型Service实现类
 * @Author haoxx
 * @Date 2019/4/4 - 16:03
 * @Version 1.0
 */
@Service("blogTypeService")
public class BlogTypeServiceImpl implements BlogTypeService {

    @Autowired
    private BlogTypeDao blogTypeDao;

    public List<BlogType> countList() {
        return blogTypeDao.countList();
    }

    public List<BlogType> list(Map<String, Object> map) {
        return blogTypeDao.list(map);
    }

    public Long getTotal(Map<String, Object> map) {
        return blogTypeDao.getTotal(map);
    }

    public Integer add(BlogType blogType) {
        return blogTypeDao.add(blogType);
    }

    public Integer update(BlogType blogType) {
        return blogTypeDao.update(blogType);
    }

    public Integer delete(Integer id) {
        return blogTypeDao.delete(id);
    }
}
