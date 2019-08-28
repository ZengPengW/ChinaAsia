package com.bootdo.api.domain;

import java.io.Serializable;
import java.util.Date;



/**
 * 
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2019-08-27 10:45:50
 */
public class TitleClassDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//标题id
	private Integer tid;
	//分类
	private String tname;

	/**
	 * 设置：标题id
	 */
	public void setTid(Integer tid) {
		this.tid = tid;
	}
	/**
	 * 获取：标题id
	 */
	public Integer getTid() {
		return tid;
	}
	/**
	 * 设置：分类
	 */
	public void setTname(String tname) {
		this.tname = tname;
	}
	/**
	 * 获取：分类
	 */
	public String getTname() {
		return tname;
	}
}
