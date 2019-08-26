package com.bootdo.api.controller;

import com.bootdo.api.domain.ApiDo;
import com.bootdo.api.pojo.ImageAndParam;
import com.bootdo.api.pojolist.ImageAndParamList;
import com.bootdo.api.utils.ApiList;
import com.bootdo.api.service.ApiService;
import com.bootdo.api.utils.JsonUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

@Controller
public class ImageApiController {
    @Autowired
    private ApiService apiService;

    //**********************************************************************************************************
    // 通用图片api
    @PostMapping("/api/update/updateImagesApi")
    @ResponseBody
    public Map<String,Integer> updateImagesApi(ApiDo apiDo){
        Map<String,Integer> map=new HashMap<>();
        try {
            apiService.updateImagesApi(apiDo);
            map.put("code",0);
        }catch (Exception e){
            map.put("code",1);
        }
        return map;
    }





    @PostMapping("/api/add/addImagesApi")
    @ResponseBody
    public Map<String,Integer> addImagesApi(ApiDo apiDo){
        Map<String,Integer> map=new HashMap<>();
        try {
            apiService.saveImagesApi(apiDo);
            map.put("code",0);
        }catch (Exception e){
            map.put("code",1);
        }
        return map;
    }
    //**********************************************************************************************************

    //带参数的图片api
    //**********************************************************************************************************
    @PostMapping("/api/add/addImagesAndParamApi")
    @ResponseBody
    public Map<String,Integer> addImagesAndParamApi(ApiDo apiDo,ImageAndParamList imgs){
        Map<String,Integer> map=new HashMap<>();
        try {
            Iterator<ImageAndParam> it =imgs.getImgs().iterator();
            while(it.hasNext()){
                ImageAndParam x = it.next();
                if(StringUtils.isBlank(x.getAlt())
                        &&StringUtils.isBlank(x.getHref())
                        &&StringUtils.isBlank(x.getSrc())
                        &&StringUtils.isBlank(x.getTitle())){
                    it.remove();
                }
            }
            String json = JsonUtils.objectToJson(imgs);
            apiDo.setData(json);
            apiService.apiSave(apiDo);
            map.put("code",0);
        }catch (Exception e){
            map.put("code",1);
        }
        return map;
    }

    @PostMapping("/api/update/updateImagesAndParamApi")
    @ResponseBody
    public Map<String,Integer> updateImagesAndParamApi(ApiDo apiDo, ImageAndParamList imgs){
        Map<String,Integer> map=new HashMap<>();
        try {
            Iterator<ImageAndParam> it =imgs.getImgs().iterator();
            while(it.hasNext()){
                ImageAndParam x = it.next();
                if(StringUtils.isBlank(x.getAlt())
                        &&StringUtils.isBlank(x.getHref())
                        &&StringUtils.isBlank(x.getSrc())
                        &&StringUtils.isBlank(x.getTitle())){
                    it.remove();
                }
            }
            String json = JsonUtils.objectToJson(imgs);
            apiDo.setData(json);
            apiService.apiUpdate(apiDo);
            map.put("code",0);
        }catch (Exception e){
            map.put("code",1);
        }
        return map;
    }

    //**********************************************************************************************************
}
