package com.bootdo.api.pojo;

import java.io.Serializable;


/**
 * 
 * 
 * @author zp
 * @email 1992lcg@163.com
 * @date 2019-08-27 11:03:42
 */
public class TitleContentTable implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//标题id
	private Long cid;
	//标题名称
	private String tname;
	//0 无 1有
	private Integer hasChildren;
	//0 无 1 有
	private String parentName;
	//标题分类
	private String tclass;
	//携带的内容
	private String content;

	//级别
	private Integer level;

    public Long getCid() {
        return cid;
    }

    public void setCid(Long cid) {
        this.cid = cid;
    }

    public String getTname() {
        return tname;
    }

    public void setTname(String tname) {
        this.tname = tname;
    }

    public Integer getHasChildren() {
        return hasChildren;
    }

    public void setHasChildren(Integer hasChildren) {
        this.hasChildren = hasChildren;
    }

    public String getParentName() {
        return parentName;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
    }

    public String getTclass() {
        return tclass;
    }

    public void setTclass(String tclass) {
        this.tclass = tclass;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }
}
