package com.bootdo.search.controller;

import com.bootdo.api.utils.ChinaAsiaResult;
import com.bootdo.search.service.SearchService;
import com.bootdo.search.utils.SearchResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/*
solr 搜索
 */
@Controller
public class SearchController {

    @Value("${ITEM_ROWS}")
    private Integer ITEM_ROWS;

    @Autowired
    private SearchService searchService;

    //导入所有索引库
    @RequestMapping("/search/importAllSearchItems")
    @ResponseBody
    public ChinaAsiaResult importAllSearchItems(){
        ChinaAsiaResult chinaAsiaResult =null;
        try {
             chinaAsiaResult = searchService.importAllSearchItems();
        } catch (Exception e) {
            e.printStackTrace();
            chinaAsiaResult=new ChinaAsiaResult();
            chinaAsiaResult.setCode("1");
            chinaAsiaResult.setData("同步失败");
        }
        return chinaAsiaResult;
    }

    //搜索
    @RequestMapping("/search")
    @ResponseBody
    public SearchResult search(@RequestParam(defaultValue="1")Integer page, @RequestParam(name="q",required = false) String queryString, Model model) throws Exception {

        //查询商品
        SearchResult result = searchService.search(queryString, page, ITEM_ROWS);


        return result;
    }

    @RequestMapping("/search/synchronization")
    public String tongbu(){
        return "search/tongbu";
    }
}
