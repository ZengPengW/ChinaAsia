package com.bootdo.api.controller;

import com.bootdo.api.dao.ApiDao;
import com.bootdo.api.domain.ApiDo;
import com.bootdo.api.service.ApiService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class ApiController {

    @Autowired
    private ApiService apiService;

    @RequestMapping("/api/list")
    public String apiList(Model model,Long page,Integer rows,String likeName){
        if (page==null)page=1l; //第几页
        if (rows==null)rows=6; //每页多少条
        //设置分页信息
        PageHelper.startPage(Math.toIntExact(page), rows);
        List<ApiDo> list;
        if (likeName!=null&&likeName.trim().length()>0){
            System.out.println(likeName);
            list=apiService.selectLikeName(likeName);
        }else {
            list = apiService.selectAll();
        }

        //取分页信息
        PageInfo<ApiDo> pageInfo=new PageInfo<>(list);
        //遍历打印结果集
       // System.out.println("查询总记录数:"+pageInfo.getTotal());
        Long totalPage=pageInfo.getTotal()%rows==0?pageInfo.getTotal()/rows:pageInfo.getTotal()/rows+1;
        model.addAttribute("totalPage", totalPage);
        model.addAttribute("rows",rows);
        model.addAttribute("page",page);
        model.addAttribute("list", list);
        model.addAttribute("likeName",likeName==null?"null":likeName);
        //设置页码
        List<Long> node=new ArrayList<>();

        if (page-2>0&&page+2<totalPage){
            for (long i = page-2; i <=page+2; i++) {
                node.add(i);
            }
        }else if (page-1>0&&page+3<totalPage){
            for (long i = page-1; i <=page+3; i++) {
                node.add(i);
            }
        }else if (page-3>0&&page+1<totalPage){
            for (long i = page-3; i <=page+1; i++) {
                node.add(i);
            }
        }else if (page-4>0&&page==totalPage){
            for (long i = page-4; i <=page; i++) {
                node.add(i);
            }
        }else {
            for (long i = 1; i <=totalPage; i++) {
                node.add(i);
            }
        }
        model.addAttribute("node", node);
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

    @RequestMapping("/api/edit/{pagename}/{id}")
    public String apiEdit(@PathVariable(value = "pagename") String pagename, @PathVariable(value = "id")Long id,Model model){
        ApiDo apiDo = apiService.selectById(id);
        model.addAttribute("apido", apiDo);
        return "api/edit/"+pagename;
    }


}
