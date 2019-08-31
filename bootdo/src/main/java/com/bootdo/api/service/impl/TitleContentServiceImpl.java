package com.bootdo.api.service.impl;

import com.bootdo.api.pojo.TitleContentTable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.bootdo.api.dao.TitleContentDao;
import com.bootdo.api.domain.TitleContentDO;
import com.bootdo.api.service.TitleContentService;



@Service
public class TitleContentServiceImpl implements TitleContentService {
	@Autowired
	private TitleContentDao titleContentDao;
	
	@Override
	public TitleContentDO get(Long cid){
		return titleContentDao.get(cid);
	}
	
	@Override
	public List<TitleContentDO> list(Map<String, Object> map){
		return titleContentDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return titleContentDao.count(map);
	}
	
	@Override
	public int save(TitleContentDO titleContent){
	    if (titleContent.getHasParent()==null||titleContent.getHasParent()<0||titleContent.getLevel()==0)
	        titleContent.setHasParent(0);
	    else{
	        TitleContentDO parent=new TitleContentDO();
	        parent.setCid((long)titleContent.getHasParent());
            parent.setHasChildren(1);
            titleContentDao.update(parent);
        }

	    titleContent.setHasChildren(0);
		return titleContentDao.save(titleContent);
	}
	
	@Override
	public int update(TitleContentDO titleContent){
		return titleContentDao.update(titleContent);
	}
	
	@Override
	public int remove(Long cid){

		return titleContentDao.remove(cid);
	}
	
	@Override
	public int batchRemove(Long[] cids){
		return titleContentDao.batchRemove(cids);
	}

    @Override
    public Integer selectMaxLevelByClassId(Integer tid) {
        return titleContentDao.selectMaxLevelByClassId(tid);
    }

    @Override
    public List<TitleContentDO> selectParentListByLevel(TitleContentDO titleContentDO) {
        return  titleContentDao.selectParentListByLevel(titleContentDO);
    }

    @Override
    public List<TitleContentTable> selectTitleContentList() {
        return  titleContentDao.selectTitleContentList();
    }

    @Override
    public List<TitleContentTable> selectTitleContentListByLikeName(String likeName) {
        return titleContentDao.selectTitleContentListByLikeName(likeName);
    }

}
