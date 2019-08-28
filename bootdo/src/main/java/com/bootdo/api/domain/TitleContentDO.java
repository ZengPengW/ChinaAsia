package com.bootdo.api.domain;

import java.io.Serializable;


/**
 * 
 * 
 * @author zp
 * @email 1992lcg@163.com
 * @date 2019-08-27 11:03:42
 */
public class TitleContentDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//标题id
	private Long cid;
	//标题名称
	private String tname;
	//0 无 1有
	private Integer hasChildren;
	//0 无 1 有
	private Integer hasParent;
	//标题分类
	private Integer tid;
	//携带的内容
	private String content;

	//级别
	private Integer level;

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    /**
	 * 设置：标题id
	 */
	public void setCid(Long cid) {
		this.cid = cid;
	}
	/**
	 * 获取：标题id
	 */
	public Long getCid() {
		return cid;
	}
	/**
	 * 设置：标题名称
	 */
	public void setTname(String tname) {
		this.tname = tname;
	}
	/**
	 * 获取：标题名称
	 */
	public String getTname() {
		return tname;
	}
	/**
	 * 设置：0 无 1有
	 */
	public void setHasChildren(Integer hasChildren) {
		this.hasChildren = hasChildren;
	}
	/**
	 * 获取：0 无 1有
	 */
	public Integer getHasChildren() {
		return hasChildren;
	}
	/**
	 * 设置：0 无 1 有
	 */
	public void setHasParent(Integer hasParent) {
		this.hasParent = hasParent;
	}
	/**
	 * 获取：0 无 1 有
     * @return
     */
	public Integer getHasParent() {
		return hasParent;
	}
	/**
	 * 设置：标题分类
	 */
	public void setTid(Integer tid) {
		this.tid = tid;
	}
	/**
	 * 获取：标题分类
	 */
	public Integer getTid() {
		return tid;
	}
	/**
	 * 设置：携带的内容
	 */
	public void setContent(String content) {
		this.content = content;
	}
	/**
	 * 获取：携带的内容
	 */
	public String getContent() {
		return content;
	}
}
