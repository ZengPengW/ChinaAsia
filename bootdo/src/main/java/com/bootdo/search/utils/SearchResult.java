package com.bootdo.search.utils;

import com.bootdo.blog.domain.ContentDO;

import java.io.Serializable;
import java.util.List;

public class SearchResult implements Serializable {
	private List<ContentDO> contentList;//商品列表
	private long totalCount;//总记录数
	private long pageCount;//总页数

    public List<ContentDO> getContentList() {
        return contentList;
    }

    public void setContentList(List<ContentDO> contentList) {
        this.contentList = contentList;
    }

    public long getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(long totalCount) {
        this.totalCount = totalCount;
    }

    public long getPageCount() {
        return pageCount;
    }

    public void setPageCount(long pageCount) {
        this.pageCount = pageCount;
    }
}