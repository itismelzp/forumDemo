package com.smart.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by lizhiping03 on 2018/9/11.
 */
public class Page implements Serializable {

    private static int DEFAULT_PAGE_SIZE = 20;

    private int pageSize = DEFAULT_PAGE_SIZE;

    private long start;

    private List data;

    private long totalCount;

    public Page() {
        this(0, 0, DEFAULT_PAGE_SIZE, new ArrayList());
    }

    public Page(long start, long totalSize, int pageSize, List data) {
        this.pageSize = pageSize;
        this.start = start;
        this.totalCount = totalSize;
        this.data = data;
    }

    public long getTotalPageCount() {
        if (totalCount % pageSize == 0) {
            return totalCount / pageSize;
        } else {
            return totalCount;
        }
    }

    public long getCurrentPageNo() {
        return start / pageSize;
    }

    public boolean isHasNextPage() {
        return this.getCurrentPageNo() < this.getTotalPageCount();
    }

    public boolean isHasPreviousPage() {
        return this.getCurrentPageNo() > 1;
    }

    protected static int getStartOfPage(int pageNo) {
        return getStartOfPage(pageNo, DEFAULT_PAGE_SIZE);
    }

    public static int getStartOfPage(int pageNo, int pageSize) {
        return (pageNo - 1) * pageSize;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public long getStart() {
        return start;
    }

    public void setStart(long start) {
        this.start = start;
    }

    public List getData() {
        return data;
    }

    public void setData(List data) {
        this.data = data;
    }

    public long getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(long totalCount) {
        this.totalCount = totalCount;
    }
}
