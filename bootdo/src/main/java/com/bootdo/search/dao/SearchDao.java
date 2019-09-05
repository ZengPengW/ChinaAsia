package com.bootdo.search.dao;



import com.bootdo.api.utils.ChinaAsiaResult;
import com.bootdo.blog.dao.ContentDao;
import com.bootdo.blog.domain.ContentDO;
import com.bootdo.search.utils.SearchResult;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.solr.core.SolrTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 从索引库中搜索商品的dao
 * @author zp
 *
 */
@Repository
public class SearchDao {

//    @Autowired
//    private SolrTemplate solrTemplate;

    @Autowired
    private HttpSolrClient solrClient;

    @Autowired
    private ContentDao bContentMapper;
	/**
	 * 根据查询条件查询结果集
	 * @param query
	 * @return
	 * @throws Exception
	 */
	
	public SearchResult search(SolrQuery query)throws Exception{

		QueryResponse queryResponse = solrClient.query(query);
		SolrDocumentList results = queryResponse.getResults();
		//封装返回的结果集
		SearchResult searchResult=new SearchResult();
		searchResult.setTotalCount(results.getNumFound());
		
		List<ContentDO> list=new ArrayList<ContentDO>();
        ContentDO item=null;
		
		//取高亮
		Map<String, Map<String, List<String>>> highlighting = queryResponse.getHighlighting();
		
		for (SolrDocument solrDocument : results) {

			item=new ContentDO();
			item.setCid(Long.parseLong((String) solrDocument.get("id")));
			//item.setContent(solrDocument.get("content").toString());
           // item.setTitle(solrDocument.get("title").toString());
			item.setType( solrDocument.get("type")!=null?solrDocument.get("type").toString():"null");
			item.setImage(solrDocument.get("image")!=null?solrDocument.get("image").toString():"null");
            item.setTags(solrDocument.get("tags")!=null?solrDocument.get("tags").toString():"null");
            item.setCategories(solrDocument.get("categories")!=null?solrDocument.get("categories").toString():"null");
            item.setAuthor(solrDocument.get("author").toString());
            long date1=(long)solrDocument.get("gtm_create");
            Date date =new Date(date1);
            item.setGtmCreate(date);
			//取高亮
			List<String> listContent = highlighting.get(solrDocument.get("id")).get("content");
            List<String> listTitle = highlighting.get(solrDocument.get("id")).get("title");
			String highlightingString="";
			if(listContent!=null&&listContent.size()>0) {
				highlightingString=listContent.get(0);
			}else {
				highlightingString=(String) solrDocument.get("content");
			}
            item.setContent(highlightingString);

            if(listTitle!=null&&listTitle.size()>0) {
                highlightingString=listTitle.get(0);
            }else {
                highlightingString=(String) solrDocument.get("title");
            }
            item.setTitle(highlightingString);
			//item.setCategory_name((String)solrDocument.get("item_desc"));
			list.add(item);
		}
		searchResult.setContentList(list);
		return searchResult;
		
	}
	
	/**
	 * 更新索引库
	 * @param
	 * @return
	 * @throws Exception
	 */
	public ChinaAsiaResult updateItemByIds(Long id) throws Exception{
		ContentDO items = bContentMapper.get(id);
		SolrInputDocument document=new SolrInputDocument();
		if (items!=null) {

				document=new SolrInputDocument();
				document.addField("id", items.getCid().toString());
				document.addField("title", items.getTitle());
				document.addField("content", items.getContent());
				document.addField("type", items.getType());
				document.addField("tags", items.getTags());
				document.addField("categories",items.getCategories() );
                document.addField("author", items.getAuthor());
                Date gtmCreate = items.getGtmCreate();
                document.addField("gtm_create",gtmCreate.getTime() );
                document.addField("image",items.getImage());

            solrClient.add(document);
            solrClient.commit();

		}

        ChinaAsiaResult chinaAsiaResult=new ChinaAsiaResult();
        chinaAsiaResult.setCode("0");
        chinaAsiaResult.setData("更新成功");
        return  chinaAsiaResult;
	}
}
