package com.bootdo.api.controller;

import com.bootdo.api.domain.ApiDo;
import com.bootdo.api.pojo.ImageAndParam;
import com.bootdo.api.pojo.IndexImage;
import com.bootdo.api.pojolist.ImageAndParamList;
import com.bootdo.api.utils.ApiList;
import com.bootdo.api.service.ApiService;
import com.bootdo.api.utils.JsonUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.velocity.runtime.directive.Foreach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;

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

    //主页轮播图片
    //**********************************************************************************************************
    @PostMapping("/api/add/addIndexImagesAndParamApi")
    @ResponseBody
    public Map<String,Integer> addIndexImagesAndParamApi(ApiDo apiDo,ImageAndParamList imgs){
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
            List<IndexImage> indexImages=new ArrayList<>();
            List<ImageAndParam> params = imgs.getImgs();

            IndexImage ims=null;
            for (ImageAndParam imageAndParam:params) {
                ims=new IndexImage();

                String[] href = imageAndParam.getHref().trim().split(",");
                String alt = imageAndParam.getAlt();
                String src = imageAndParam.getSrc();
                String[] bodys = imageAndParam.getTitle().split("\\|\\|\\|");

                ims.setAlt(alt);
                ims.setSrc(src);

                List<String> hs=new ArrayList<>();
                for (String h:href) {
                    if (h!=null&&h.trim().length()>0){
                        hs.add(h);
                    }
                }
                ims.setUrl(hs);

                ims.setHead(bodys[0]);
                List<String> bs = new ArrayList<>();
                for (int i = 1; i < bodys.length; i++) {
                    if (bodys[i]!=null&&bodys[i].trim().length()>0){
                        bs.add(bodys[i]);
                    }
                }
                ims.setBody(bs);


                indexImages.add(ims);

            }

            String json = JsonUtils.objectToJson(indexImages);
            apiDo.setData(json);
            apiService.apiSave(apiDo);
            map.put("code",0);
        }catch (Exception e){
            map.put("code",1);
            e.printStackTrace();
        }
        return map;
    }

    @PostMapping("/api/update/updateIndexImagesAndParamApi")
    @ResponseBody
    public Map<String,Integer> updateIndexImagesAndParamApi(ApiDo apiDo,ImageAndParamList imgs){
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
            List<IndexImage> indexImages=new ArrayList<>();
            List<ImageAndParam> params = imgs.getImgs();

            IndexImage ims=null;
            for (ImageAndParam imageAndParam:params) {
                ims=new IndexImage();

                String[] href = imageAndParam.getHref().trim().split(",");
                String alt = imageAndParam.getAlt();
                String src = imageAndParam.getSrc();
                String[] bodys = imageAndParam.getTitle().split("\\|\\|\\|");

                ims.setAlt(alt);
                ims.setSrc(src);

                List<String> hs=new ArrayList<>();
                for (String h:href) {
                    if (h!=null&&h.trim().length()>0){
                        hs.add(h);
                    }
                }
                ims.setUrl(hs);

                ims.setHead(bodys[0]);
                List<String> bs = new ArrayList<>();
                for (int i = 1; i < bodys.length; i++) {
                    if (bodys[i]!=null&&bodys[i].trim().length()>0){
                        bs.add(bodys[i]);
                    }
                }
                ims.setBody(bs);


                indexImages.add(ims);

            }

            String json = JsonUtils.objectToJson(indexImages);
            apiDo.setData(json);
            apiService.apiUpdate(apiDo);
            map.put("code",0);
        }catch (Exception e){
            map.put("code",1);
            e.printStackTrace();
        }
        return map;
    }
    //**********************************************************************************************************
}
