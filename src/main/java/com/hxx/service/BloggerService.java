package com.hxx.service;

import com.hxx.entity.Blogger;

/**
 * @ClassName BloggerService
 * @Description //TODO 博主Service接口
 * @Author haoxx
 * @Date 2019/4/3 - 15:28
 * @Version 1.0
 */
public interface BloggerService {
    /**
     * @return
     * @author haoxx
     * @Description //TODO 通过用户名查询用户
     * @Date 2019/4/3 - 16:25
     * @Param
     */
    public Blogger getByUserName(String userName);

    /**
     * @return com.hxx.entity.Blogger
     * @author haoxx
     * @Description //TODO 查询博主信息
     * @Date 2019/4/3 - 22:20
     * @Param []
     */
    public Blogger find();

    /**
     *@author haoxx
     *@Description //TODO 更新博主信息
     *@Date 2019/4/8 - 14:53
     *@Param [blogger]
     *@return java.lang.Integer
     */
    public Integer update(Blogger blogger);
}
