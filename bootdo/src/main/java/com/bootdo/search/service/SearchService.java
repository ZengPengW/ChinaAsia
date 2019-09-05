package com.bootdo.search.service;


import com.bootdo.api.utils.ChinaAsiaResult;
import com.bootdo.blog.domain.ContentDO;
import com.bootdo.search.utils.SearchResult;

import java.util.List;
import java.util.Map;


public interface SearchService {
	/**
	 * 导入所有文章数据到索引库中
	 * @return
	 */
	public ChinaAsiaResult importAllSearchItems()throws Exception ;
	
	/**
	 *根据 搜索条件搜索结果
	 * @param queryString 查询的主要条件
	 * @param page 查询当前页码
	 * @param rows 每页显示的条数
	 * @return
	 * @throws Exception
	 */
	public SearchResult search(String queryString, Integer page, Integer rows)throws Exception;
	/**
	 * 根据id更新索引
	 * @param itemIds
	 * @return
	 * @throws Exception
	 */
	public ChinaAsiaResult updateItemByIds(Long id) throws Exception;
	
	/**
	 * 根据id删除索引
	 */
	public ChinaAsiaResult deleteItemByIds(Long... itemIds) throws Exception;

    /**
     * 保存索引
     */

    public ChinaAsiaResult save(ContentDO contentDO)throws Exception ;



}
