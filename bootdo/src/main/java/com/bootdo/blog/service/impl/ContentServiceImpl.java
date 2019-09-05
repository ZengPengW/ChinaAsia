package com.bootdo.blog.service.impl;

import com.bootdo.search.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.bootdo.blog.dao.ContentDao;
import com.bootdo.blog.domain.ContentDO;
import com.bootdo.blog.service.ContentService;



@Service
public class ContentServiceImpl implements ContentService {
	@Autowired
	private ContentDao bContentMapper;

	@Autowired
	private SearchService searchService;
	
	@Override
	public ContentDO get(Long cid){
		return bContentMapper.get(cid);
	}
	
	@Override
	public List<ContentDO> list(Map<String, Object> map){
		return bContentMapper.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return bContentMapper.count(map);
	}
	
	@Override
	public int save(ContentDO bContent){
	    int status=bContentMapper.save(bContent);
        try {
            searchService.save(bContent);//同步索引
        } catch (Exception e) {
            e.printStackTrace();
        }
        return status;
	}
	
	@Override
	public int update(ContentDO bContent){
        int status=bContentMapper.update(bContent);
        try {
            searchService.updateItemByIds(bContent.getCid());//同步索引
        } catch (Exception e) {
            e.printStackTrace();
        }
        return status;
	}
	
	@Override
	public int remove(Long cid){
        int status= bContentMapper.remove(cid);
        try {
            searchService.deleteItemByIds(cid);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return status;
    }
	
	@Override
	public int batchRemove(Long[] cids){
        int status=bContentMapper.batchRemove(cids);
        try {
            searchService.deleteItemByIds(cids);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return status;
	}
	
}
