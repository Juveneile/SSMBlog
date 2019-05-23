package com.hxx.entity;

/**
 * @ClassName PageBean
 * @Description //TODO 分页Model类
 * @Author haoxx
 * @Date 2019/4/4 - 16:37
 * @Version 1.0
 */
public class PageBean {
    private int page; // 第几页
    private int pageSize; // 每页记录数
    private int start;  // 起始页

    public PageBean(int page, int pageSize) {
        super();
        this.page = page;
        this.pageSize = pageSize;
    }

    public int getPage() {
        return page;
    }
    public void setPage(int page) {
        this.page = page;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getStart() {
        return (page-1)*pageSize;
    }
}
