package com.hxx.dao;

import com.hxx.entity.Blogger;

/**
 * @ClassName BloggerDao
 * @Description //TODO 博主Dao接口
 * @Author haoxx
 * @Date 2019/4/3 - 15:26
 * @Version 1.0
 */
public interface BloggerDao {
    /**
     * @return com.hxx.entity.Blogger
     * @author haoxx
     * @Description //TODO 通过用户名查询用户
     * @Date 2019/4/3 - 16:20
     * @Param [userName]
     */
    public Blogger getByUserName(String userName);

    /**
     * @return com.hxx.entity.Blogger
     * @author haoxx
     * @Description //TODO 查询博主信息
     * @Date 2019/4/3 - 22:21
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
