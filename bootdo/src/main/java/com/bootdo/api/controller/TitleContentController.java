package com.bootdo.api.controller;

import com.bootdo.api.domain.ApiDo;
import com.bootdo.api.domain.TitleClassDO;
import com.bootdo.api.domain.TitleContentDO;
import com.bootdo.api.pojo.TitleContentTable;
import com.bootdo.api.service.TitleClassService;
import com.bootdo.api.service.TitleContentService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.velocity.runtime.directive.Foreach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * 
 * 
 * @author zp
 * @email 1992lcg@163.com
 * @date 2019-08-27 11:03:42
 */
 
@Controller
public class TitleContentController {
	@Autowired
	private TitleContentService titleContentService;

	@Autowired
	private TitleClassService titleClassService;

    //增加页
    @RequestMapping("/api/titleContent/add/{page}")
    public String imageApiAddPage(Model model, @PathVariable(value = "page") String page){
        List<TitleClassDO> list = titleClassService.list(null);
        model.addAttribute("list",list);
        return "api/add/"+page;
    }

    //修改页
    @RequestMapping("/api/titleContent/edit/{page}")
    public String imageApiEditPage(Model model, @PathVariable(value = "page") String page, Long cid){
        TitleContentDO contentDO = titleContentService.get(cid);
        model.addAttribute("contentDO",contentDO);
        return "api/edit/"+page;
    }

    @RequestMapping("/api/titleContent/level/{tid}")
    @ResponseBody
    public  Map selectMaxLevelByClassId(@PathVariable(value = "tid",required = false) Integer tid){
        Map map=new HashMap();
        Integer level =null;
        try {
            if (tid==null){
                level=0;
            }else {
                level = titleContentService.selectMaxLevelByClassId(tid);
                if (level==null){
                    level=0;
                }

            }
            map.put("level",level);
            map.put("code",0);
        }catch (Exception e){
            e.printStackTrace();
            map.put("msg","错误");
            map.put("code",1);
        }

        return map;



    }


    @RequestMapping("/api/titleContent/parent")
    @ResponseBody
    public  Map selectParentListByLevel(TitleContentDO titleContentDO){
        Map map=new HashMap();

        try {
            List<TitleContentDO> titleContentDOS = titleContentService.selectParentListByLevel(titleContentDO);
            map.put("code",0);
            map.put("list",titleContentDOS);
        }catch (Exception e){
            e.printStackTrace();
            map.put("msg","错误");
            map.put("code",1);
        }

        return map;



    }

    @PostMapping("/add/titlecontent")
    @ResponseBody
    public Map addTitlecontent(TitleContentDO titleContentDO){
        Map map=new HashMap();
        try {
            titleContentService.save(titleContentDO);
            map.put("code",0);
        }catch (Exception e){
            e.printStackTrace();
            map.put("msg","错误");
            map.put("code",1);
        }
        return  map;
    }

    @RequestMapping("/titlecontent/list")
    public  String titlecontentList(Model model,Long page,Integer rows,String likeName){


        if (page==null)page=1l; //第几页
        if (rows==null)rows=6; //每页多少条
        //设置分页信息
        PageHelper.startPage(Math.toIntExact(page), rows);
        List<TitleContentTable> list =null;
        if (likeName!=null&&likeName.trim().length()>0){
            System.out.println(likeName);
            list=titleContentService.selectTitleContentListByLikeName(likeName);
        }else {
            list = titleContentService.selectTitleContentList();
        }

        //取分页信息
        PageInfo<TitleContentTable> pageInfo=new PageInfo<>(list);
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

        if (page==1&&totalPage>=5){
            for (long i = 1; i <=5; i++) {
                node.add(i);
            }
        }else if (page-2>0&&page+2<=totalPage){
            for (long i = page-2; i <=page+2; i++) {
                node.add(i);
            }
        }else if (page-1>0&&page+3<=totalPage){
            for (long i = page-1; i <=page+3; i++) {
                node.add(i);
            }
        }else if (page-3>0&&page+1<=totalPage){
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
        model.addAttribute("pagename","titleContentEdit");
        return "api/list/titleContentList";

    }

    @PostMapping("/titlecontent/delete")
    @ResponseBody
    public Map deleteByCid(Integer cid){
        if (cid==null)return null;
        Map map=new HashMap();
        Map msg=new HashMap();

        try {

            List<Long> cids=new ArrayList<>();
          //  cids.add(Long.valueOf(cid));

            Queue<Long> queue=new LinkedList<>();
            queue.offer(Long.valueOf(cid));
            //把所有相关的节点加入队列
            while (!queue.isEmpty()){
                long c=queue.poll();
                map.put("hasParent",c);
                List<TitleContentDO> list = titleContentService.list(map);
                if (list!=null&&list.size()>0){
                    for (TitleContentDO t:list) {
                        cids.add(t.getCid());
                        queue.offer(t.getCid());
                    }
                }

            }

          //  TitleContentDO titleContentDO = titleContentService.get(Long.valueOf(cid));
            titleContentService.remove(Long.valueOf(cid));

            if (cids.size()>0){
                Long [] cidss=new Long[cids.size()];
                int i=0;
                for (Long t:cids) {
                    cidss[i++]=t;
                }
                titleContentService.batchRemove(cidss);
            }

            msg.put("code",0);
        }catch (Exception e){
            msg.put("code",1);
            e.printStackTrace();
        }

        return  msg;


    }

    @PostMapping("/edit/titlecontent")
    @ResponseBody
    public Map update(TitleContentDO titleContentDO){
        Map msg=new HashMap();
        try {
            titleContentService.update(titleContentDO);
            msg.put("code",0);
        }catch (Exception e){
            msg.put("code",1);
        }
        return  msg;

    }
}
