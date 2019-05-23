package com.hxx.service.Impl;

import com.hxx.dao.BloggerDao;
import com.hxx.entity.Blogger;
import com.hxx.service.BloggerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @ClassName BloggerServiceImpl
 * @Description //TODO 博主Service实现类
 * @Author haoxx
 * @Date 2019/4/3 - 15:29
 * @Version 1.0
 */
@Service("bloggerService")
public class BloggerServiceImpl implements BloggerService {
    @Autowired
    private BloggerDao bloggerDao;
    /**
     *@author haoxx
     *@Description //TODO 具体实现方法
     *@Date 2019/4/3 - 16:27
     *@Param [userName]
     *@return com.hxx.entity.Blogger
     */
    public Blogger getByUserName(String userName) {
        return bloggerDao.getByUserName(userName);
    }
    /**
     *@author haoxx
     *@Description //TODO 
     *@Date 2019/4/3 - 22:26
     *@Param []
     *@return com.hxx.entity.Blogger
     */
    public Blogger find() {
        return bloggerDao.find();
    }

    public Integer update(Blogger blogger){
        return bloggerDao.update(blogger);
    }
}
