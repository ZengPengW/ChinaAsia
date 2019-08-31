package com.chinaasia.controller;


import com.chinaasia.pojo.ContentDO;
import com.chinaasia.service.ContentService;
import com.chinaasia.utils.ChinaAsiaResult;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.management.Query;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

/**
 * 文章内容
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2017-09-09 10:03:34
 */
@Controller
@RequestMapping("/blog/bContent")
public class ContentController  {
	@Autowired
    ContentService bContentService;

	//根据类别查询
	@RequestMapping("/categories/{categories}")
    @ResponseBody
    public ChinaAsiaResult getContentBycategories(@PathVariable(value = "categories",required = false) String categories, Integer rows, Integer page, HttpServletResponse response){
        response.setCharacterEncoding("utf-8");
        response.setContentType("application/json; charset=utf-8");
	        if (page==null)page=1;
            Map<String,Object> map=new HashMap<>();
            map.put("categories",categories);
            List<ContentDO> list =new ArrayList<>();
            ChinaAsiaResult chinaAsiaResult=new ChinaAsiaResult();

            try {
                if (rows==null){
                    list = bContentService.selectAll(map);
                }else {
                    //设置分页信息
                    PageHelper.startPage(page, rows);
                    list = bContentService.selectAll(map);
                }

                if (list==null||list.size()==0){
                    chinaAsiaResult.setCode("1");
                    chinaAsiaResult.setData("无内容");
                }else {
                    chinaAsiaResult.setCode("0");
                    chinaAsiaResult.setData(list);
                }
            }catch (Exception e){
                chinaAsiaResult.setCode("1");
                chinaAsiaResult.setData("错误");
                e.printStackTrace();
            }


            return chinaAsiaResult;

    }

    //根据id查询
    @RequestMapping("/cid/{cid}")
    @ResponseBody
    public ChinaAsiaResult getContentById(@PathVariable(value = "cid") Long cid,HttpServletResponse response){

        response.setCharacterEncoding("utf-8");
        response.setContentType("application/json; charset=utf-8");

        List<ContentDO> list =new ArrayList<>();
        ChinaAsiaResult chinaAsiaResult=new ChinaAsiaResult();

        try {
            if (cid==null){
                throw  new RuntimeException();
            }
            Map<String,Object> map=new HashMap<>();
            map.put("cid",cid);
            list=bContentService.selectAll(map);
            if (list==null||list.size()==0){
                chinaAsiaResult.setCode("1");
                chinaAsiaResult.setData("无内容");
            }else {
                chinaAsiaResult.setCode("0");
                chinaAsiaResult.setData(list);
            }
        }catch (Exception e){
            chinaAsiaResult.setCode("1");
            chinaAsiaResult.setData("错误");
            e.printStackTrace();
        }


        return chinaAsiaResult;

    }
}
