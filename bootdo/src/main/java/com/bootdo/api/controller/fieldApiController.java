package com.bootdo.api.controller;

import com.bootdo.api.domain.ApiDo;
import com.bootdo.api.service.ApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
public class fieldApiController {
    @Autowired
    private ApiService apiService;

    @PostMapping("/api/update/updateFieldApi")
    @ResponseBody
    public Map<String,Integer> updateImagesApi(ApiDo apiDo){
        Map<String,Integer> map=new HashMap<>();
        try {
            apiService.updateFieldApi(apiDo);
            map.put("code",0);
        }catch (Exception e){
            map.put("code",1);
        }
        return map;
    }


    @RequestMapping("/api/add/fieldApiAdd")
    public String imageApiAddPage(Model model){
        return "api/add/fieldApiAdd";
    }


    @PostMapping("/api/add/addFieldApi")
    @ResponseBody
    public Map<String,Integer> addImagesApi(ApiDo apiDo){
        Map<String,Integer> map=new HashMap<>();
        try {
            apiService.saveFieldApi(apiDo);
            map.put("code",0);
        }catch (Exception e){
            map.put("code",1);
        }
        return map;
    }
}
