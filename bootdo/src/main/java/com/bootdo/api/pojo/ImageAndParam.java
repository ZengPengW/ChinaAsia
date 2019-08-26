package com.bootdo.api.pojo;

import java.io.Serializable;

/**
 * 带参数的图片对象
 */
public class ImageAndParam implements Serializable {
    private String src;  // 图片地址
    private String href; //a 标签指向地址
    private String alt;//错误信息
    private String title; //内容

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public String getAlt() {
        return alt;
    }

    public void setAlt(String alt) {
        this.alt = alt;
    }
}
