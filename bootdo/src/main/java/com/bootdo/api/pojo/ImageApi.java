package com.bootdo.api.pojo;

import java.io.Serializable;
import java.util.List;

/**
 * 图片地址通用接口
 */
public class ImageApi implements Serializable {
    private List<String> imgs; //存放图片地址

    public List<String> getImgs() {
        return imgs;
    }

    public void setImgs(List<String> imgs) {
        this.imgs = imgs;
    }
}
