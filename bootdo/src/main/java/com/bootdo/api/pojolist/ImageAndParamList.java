package com.bootdo.api.pojolist;

import com.bootdo.api.pojo.ImageAndParam;
import com.bootdo.api.utils.ApiList;

import java.util.List;

/**
 * 带参数的图片的bean
 */
public class ImageAndParamList  {
    private List<ImageAndParam> imgs;

    public List<ImageAndParam> getImgs() {
        return imgs;
    }

    public void setImgs(List<ImageAndParam> imgs) {
        this.imgs = imgs;
    }
}
