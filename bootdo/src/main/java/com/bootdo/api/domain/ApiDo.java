package com.bootdo.api.domain;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.xml.crypto.Data;
import java.io.Serializable;
import java.util.Date;

/**
 * api pojo
 *
 * create table api(
 * id bigint(20)   primary key auto_increment comment '接口id',
 * `name` varchar(20) comment '接口名称',
 * `desc` text comment '接口描述',
 * `data` text comment '数据'
 * );
 */

public class ApiDo implements Serializable {
    private  Long id;
    private  String name;  //名字
    private  String desc; //描述
    private  String data; //数据
    private  Integer status; // 状态
    private String pagename;//当前接口修改页

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date created; //创建时间
    private Date updated; //更新时间
    private String icon; //图片

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }

    public String getPagename() {
        return pagename;
    }

    public void setPagename(String pagename) {
        this.pagename = pagename;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
