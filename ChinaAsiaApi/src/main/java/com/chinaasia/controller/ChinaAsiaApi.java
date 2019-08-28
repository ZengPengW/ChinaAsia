package com.chinaasia.controller;

import com.chinaasia.pojo.ApiDo;
import com.chinaasia.pojo.TitleContentTable;
import com.chinaasia.service.ApiService;
import com.chinaasia.service.TitleContentService;
import com.chinaasia.utils.ChinaAsiaResult;
import com.chinaasia.utils.JsonUtils;
import org.apache.commons.collections.map.HashedMap;
import org.apache.http.HttpResponse;
import org.omg.CORBA.StringHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class ChinaAsiaApi {

    @Autowired
    private ApiService apiService;

    @Autowired
    private TitleContentService titleContentService;

    @GetMapping("/api/{id}")
    public void getApi(@PathVariable(value = "id",required = false) Long id, HttpServletRequest request, HttpServletResponse response) throws IOException {
       response.setCharacterEncoding("utf-8");
       response.setContentType("application/json; charset=utf-8");
       ChinaAsiaResult result =new ChinaAsiaResult();
       try {
           if (id==null){

               result.setCode("1");
               result.setData("错误的接口");

           }else {
               ApiDo apiDo = apiService.selectById(id);
               //response.getWriter().print(apiDo.getData());
               result.setCode("0");
               result.setData(apiDo.getData());
           }
       }catch (Exception e){
               result.setCode("1");
               result.setData("错误的接口");
       }finally {
           String json = JsonUtils.objectToJson(result);
           response.getWriter().print(json);
       }




    }

    //通过父级目录得到子目录
    @GetMapping("/directory/{className}/{parentId}")
    public ChinaAsiaResult getDirectory(@PathVariable String className,@PathVariable Long parentId,HttpServletResponse response){
        response.setCharacterEncoding("utf-8");
        response.setContentType("application/json; charset=utf-8");
        ChinaAsiaResult result =new ChinaAsiaResult();
        try {
            Map <String,Object> map=new HashMap<>();
            map.put("className",className);
            map.put("parentId",parentId);
            List<TitleContentTable> list = titleContentService.selectDirectoryByParentIdAndClassName(map);
            result.setCode("0");
            result.setData(list);
        }catch (Exception e){
            result.setCode("1");
            result.setData("查询错误！！！");
            e.printStackTrace();
        }

      return result;

    }

}
