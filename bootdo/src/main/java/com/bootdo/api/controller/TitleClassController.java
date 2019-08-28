package com.bootdo.api.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.bootdo.api.domain.ApiDo;
import com.bootdo.api.domain.TitleClassDO;
import com.bootdo.api.service.TitleClassService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.R;

/**
 * 
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2019-08-27 10:45:50
 */
 
@Controller
public class TitleClassController {
	@Autowired
	private TitleClassService classService;

	@PostMapping("/add/titleclass")
    @ResponseBody
    public Map<String,Integer> addtitleclass(TitleClassDO titleClassDO){
        Map<String,Integer> map=new HashMap<>();
        try {
            System.out.println(titleClassDO.getTname());
            classService.save(titleClassDO);
            map.put("code",0);
        }catch (Exception e){
            e.printStackTrace();
            map.put("code",1);
        }
        return map;
    }

	
}
