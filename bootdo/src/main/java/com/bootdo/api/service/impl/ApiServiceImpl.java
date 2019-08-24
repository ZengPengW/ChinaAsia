package com.bootdo.api.service.impl;

import com.bootdo.api.dao.ApiDao;
import com.bootdo.api.domain.ApiDo;
import com.bootdo.api.pojo.FieldApi;
import com.bootdo.api.pojo.ImageApi;
import com.bootdo.api.service.ApiService;
import com.bootdo.api.utils.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class ApiServiceImpl implements ApiService {
    @Autowired
    private ApiDao apiDao;

    @Override
    public List<ApiDo> selectAll() {
        return apiDao.selectAll();
    }

    @Override
    public void deleteById(Long id) {
        apiDao.deleteById(id);
    }

    //待修改
    @Override
    public void saveImagesApi(ApiDo apiDo) {

        apiDo.setCreated(new Date());
        apiDo.setUpdated(apiDo.getCreated());
        apiDo.setStatus(0);

        String imgs=apiDo.getData();
        // System.out.println(imgs);
        String []ims=imgs.split(",");
        List<String> list=new ArrayList<>();
        for (String s: ims) {
            if(s!=null&&s.trim().length()>0){
                //    System.out.println(s);
                list.add(s);
            }
        }
        if (list.size()>0){
            ImageApi imageApi=new ImageApi();
            imageApi.setImgs(list);
            apiDo.setData(JsonUtils.objectToJson(imageApi));
        }

        apiDao.saveApi(apiDo);
    }

    @Override
    public ApiDo selectById(Long id) {
        return apiDao.selectById(id);
    }

    @Override
    public void updateImagesApi(ApiDo apiDo) {
        ApiDo apiDo1 = apiDao.selectById(apiDo.getId());
        apiDo.setCreated(apiDo1.getCreated());
        apiDo.setUpdated(new Date());
        apiDo.setStatus(apiDo1.getStatus());
        String imgs=apiDo.getData();
       // System.out.println(imgs);
        String []ims=imgs.split(",");
        List<String> list=new ArrayList<>();
        for (String s: ims) {
            if(s!=null&&s.trim().length()>0){
            //    System.out.println(s);
                list.add(s);
            }
        }
        if (list.size()>0){
            ImageApi imageApi=new ImageApi();
            imageApi.setImgs(list);
            apiDo.setData(JsonUtils.objectToJson(imageApi));
        }

        apiDao.updateApi(apiDo);
    }

    @Override
    public List<ApiDo> selectLikeName(String likeName) {
        return apiDao.selectLikeName(likeName);
    }

    @Override
    public void updateFieldApi(ApiDo apiDo) {
        ApiDo apiDo1 = apiDao.selectById(apiDo.getId());
        apiDo.setCreated(apiDo1.getCreated());
        apiDo.setUpdated(new Date());
        apiDo.setStatus(apiDo1.getStatus());
        String field=apiDo.getData();
        // System.out.println(imgs);
        String []fields=field.split(",");
        List<String> list=new ArrayList<>();
        for (String s: fields) {
            if(s!=null&&s.trim().length()>0){
                //    System.out.println(s);
                list.add(s);
            }
        }
        if (list.size()>0){
            FieldApi fieldApi=new FieldApi();
            fieldApi.setField(list);
            apiDo.setData(JsonUtils.objectToJson(fieldApi));
        }

        apiDao.updateApi(apiDo);
    }

    @Override
    public void saveFieldApi(ApiDo apiDo) {
        apiDo.setCreated(new Date());
        apiDo.setUpdated(apiDo.getCreated());
        apiDo.setStatus(0);

        String field=apiDo.getData();
        // System.out.println(imgs);
        String []fields=field.split(",");
        List<String> list=new ArrayList<>();
        for (String s: fields) {
            if(s!=null&&s.trim().length()>0){
                //    System.out.println(s);
                list.add(s);
            }
        }
        if (list.size()>0){
            FieldApi fieldApi=new FieldApi();
            fieldApi.setField(list);
            apiDo.setData(JsonUtils.objectToJson(fieldApi));
        }

        apiDao.saveApi(apiDo);
    }
}
