package com.bootdo.search.service.impl;


import com.bootdo.api.utils.ChinaAsiaResult;
import com.bootdo.blog.dao.ContentDao;
import com.bootdo.blog.domain.ContentDO;
import com.bootdo.search.dao.SearchDao;
import com.bootdo.search.service.SearchService;
import com.bootdo.search.utils.SearchResult;
import org.apache.commons.lang.StringUtils;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.solr.core.SolrTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Service
public class SearchServiceimpl implements SearchService {

	@Autowired
	private ContentDao bContentMapper;
//
//	@Autowired
//	private SolrTemplate solrTemplate;

    @Autowired
    private SolrClient solrClient;

	@Autowired
    private SearchDao searchDao;

	//同步索引库
	@Override
	public ChinaAsiaResult importAllSearchItems() throws Exception {
		List<ContentDO> contentDOList = bContentMapper.selectAll(null);

		List<SolrInputDocument> docs = new ArrayList<>(contentDOList.size() + 1);
		SolrInputDocument document = null;
		for (ContentDO searchItem : contentDOList) {
			document = new SolrInputDocument();
			document.addField("id", searchItem.getCid().toString());
            document.addField("title", searchItem.getTitle());
            document.addField("content", searchItem.getContent());
			document.addField("type", searchItem.getType());
			document.addField("tags", searchItem.getTags());
			document.addField("categories", searchItem.getCategories());
			document.addField("author", searchItem.getAuthor());
            Date gtmCreate = searchItem.getGtmCreate();
            document.addField("gtm_create", gtmCreate.getTime());
            document.addField("image", searchItem.getImage());
			docs.add(document);
		}

        solrClient.add(docs);
		solrClient.commit();
		ChinaAsiaResult chinaAsiaResult=new ChinaAsiaResult();
		chinaAsiaResult.setCode("0");
		chinaAsiaResult.setData("同步成功");
		return  chinaAsiaResult;
	}


	@Override
	public SearchResult search(String queryString, Integer page, Integer rows) throws Exception {
		SolrQuery solrQuery = new SolrQuery();
		// 设置查询主要条件
		if (StringUtils.isNotBlank(queryString))
			solrQuery.setQuery(queryString);
		else
			solrQuery.setQuery("*:*");
		// 设置分页
		if (page == null)
			page = 1;
		if (rows == null)
			rows = 60;
		solrQuery.setStart((page - 1) * rows);
		solrQuery.setRows(rows);
		// 设置搜索域
		solrQuery.set("df", "item_keywords");

		// 设置高亮
		solrQuery.setHighlight(true);
		solrQuery.setHighlightSimplePre("<em style='color:red'>");
		solrQuery.setHighlightSimplePost("</em>");
		solrQuery.addHighlightField("content");
        solrQuery.addHighlightField("title");

		// 查询
		SearchResult searchResult = searchDao.search(solrQuery);
		// 设置总页数
		Long pageCount = 0l;
		pageCount = searchResult.getTotalCount() % rows == 0 ? searchResult.getTotalCount() / rows
				: (searchResult.getTotalCount() / rows) + 1;
		searchResult.setPageCount(pageCount);

		return searchResult;
	}

	@Override
	public ChinaAsiaResult updateItemByIds(Long id) throws Exception {
		return searchDao.updateItemByIds(id);
	}

	@Override
	public ChinaAsiaResult deleteItemByIds(Long... itemIds) throws Exception {
		
		List<String> ids = new ArrayList<>();
		for (Long id : itemIds) {
			ids.add(id.toString());
		}

        solrClient.deleteById(ids);
        solrClient.commit();
        ChinaAsiaResult chinaAsiaResult=new ChinaAsiaResult();
        chinaAsiaResult.setCode("0");
        chinaAsiaResult.setData("删除成功");
        return  chinaAsiaResult;
	}

    @Override
    public ChinaAsiaResult save(ContentDO contentDO) throws Exception {

        SolrInputDocument document=new SolrInputDocument();
        if (contentDO!=null) {

            document=new SolrInputDocument();
            document.addField("id", contentDO.getCid().toString());
            document.addField("title", contentDO.getTitle());
            document.addField("content", contentDO.getContent());
            document.addField("type", contentDO.getType());
            document.addField("tags", contentDO.getTags());
            document.addField("categories",contentDO.getCategories() );
            document.addField("author", contentDO.getAuthor());
            Date gtmCreate = contentDO.getGtmCreate();
            document.addField("gtm_create",gtmCreate.getTime() );
            document.addField("image",contentDO.getImage());

            solrClient.add(document);
            solrClient.commit();

        }

        ChinaAsiaResult chinaAsiaResult=new ChinaAsiaResult();
        chinaAsiaResult.setCode("0");
        chinaAsiaResult.setData("更新成功");
        return  chinaAsiaResult;
    }
}