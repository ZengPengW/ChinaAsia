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
import java.util.List;
import java.util.Map;

@Controller
public class ApiController {

    @Autowired
    private ApiService apiService;

    @RequestMapping("/api/list")
    public String apiList(Model model){
        List<ApiDo> list = apiService.selectAll();
        model.addAttribute("list", list);
        return "api/list/api_list";
    }


    @PostMapping("/api/delete")
    @ResponseBody
    public Map<String, Integer> deleteById(Long id){
        Map<String,Integer> map=new HashMap<>();
        try {
            apiService.deleteById(id);
            map.put("code",0);
        }catch (Exception e){
            map.put("code",1);
        }
        return map;
    }
}
