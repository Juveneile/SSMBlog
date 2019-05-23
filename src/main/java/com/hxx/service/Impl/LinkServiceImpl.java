package com.hxx.service.Impl;

import com.hxx.dao.LinkDao;
import com.hxx.entity.Link;
import com.hxx.service.LinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @ClassName LinkServiceImpl
 * @Description //TODO
 * @Author haoxx
 * @Date 2019/4/3 - 22:16
 * @Version 1.0
 */
@Service("linkService")
public class LinkServiceImpl implements LinkService {
    @Autowired
    private LinkDao linkDao;
    public List<Link> list(Map<String, Object> map) {
        return linkDao.list(map);
    }

    public Long getTotal(Map<String, Object> map) {
        return linkDao.getTotal(map);
    }

    public Integer add(Link link) {
        return linkDao.add(link);
    }

    public Integer update(Link link) {
        return linkDao.update(link);
    }

    public Integer delete(Integer id) {
        return linkDao.delete(id);
    }
}
