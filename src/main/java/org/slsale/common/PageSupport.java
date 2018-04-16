package org.slsale.common;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author takooya
 * @Description
 * @Date:created in 18:49 2018/4/11
 */
public class PageSupport {
    /**
     * 总记录数
     */
    private Integer totalCount = 0;
    /**
     * 总页数
     */
    private Integer pageCount;
    /**
     * 每页显示多少条
     */
    private Integer pageSize = 10;
    /**
     * 当前页
     */
    private Integer page;
    /**
     * 当前页之前页或之后页 显示页数超链接(显示页数偏移量)
     */
    private Integer number = 3;
    /**
     * 当前页列表
     */
    private List items = new ArrayList();

    /**
     * 获取总记录数
     */
    public Integer getTotalCount() {
        return totalCount;
    }

    /**
     * 计算总页数
     */
    public void setTotalCount(Integer totalCount) {
        if (totalCount > 0) {
            this.totalCount = totalCount;
            if (this.totalCount % this.pageSize == 0) {
                this.pageCount = totalCount / pageSize;
            } else if (this.totalCount % this.pageSize > 0) {
                this.pageCount = totalCount / pageSize + 1;
            } else {
                this.pageCount = 0;
            }
        }
    }

    public Integer getPageCount() {
        return pageCount;
    }

    public void setPageCount(Integer pageCount) {
        this.pageCount = pageCount;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public List getItems() {
        return items;
    }

    public void setItems(List items) {
        this.items = items;
    }

    public Integer getPrev() {
        return page - 1;
    }

    public Integer getNext() {
        return page + 1;
    }

    public Integer getLast() {
        return pageCount;
    }

    /**
     * 判断是否有前一页
     */
    public boolean getIsPrev() {
        return page > 0 ? true : false;
    }

    /**
     * 判断是否有后一页
     */
    public boolean getIsNext() {
        return null != pageCount && page < pageCount ? true : false;
    }

    /**
     * 显示当前页 之前num条的下标
     */
    public List<Integer> getPrevPages() {
        List<Integer> list = new ArrayList<>();
        Integer frontStart = page > number ? page - number : 1;
        for (Integer i = frontStart; i < page; i++) {
            list.add(i);
        }
        return list;
    }

    /**
     * 显示当前页 之后num条的下标:当前页6 则7,8,9
     */
    public List<Integer> getNextPages() {
        List<Integer> list = new ArrayList<>();
        pageCount = (pageCount == null ? 1 : pageCount);
        Integer endCount = number < pageCount && (page + number) < pageCount
                ? page + number : pageCount;
        for (Integer i = page + 1; i <= endCount; i++) {
            list.add(i);
        }
        return list;
    }
}
