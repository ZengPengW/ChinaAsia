package com.bootdo.api.dao;

import com.bootdo.api.domain.TitleContentDO;

import java.util.List;
import java.util.Map;

import com.bootdo.api.pojo.TitleContentTable;
import org.apache.ibatis.annotations.Mapper;

/**
 * 
 * @author zp
 * @email 1992lcg@163.com
 * @date 2019-08-27 11:03:42
 */
@Mapper
public interface TitleContentDao {

	TitleContentDO get(Long cid);
	
	List<TitleContentDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(TitleContentDO titleContent);
	
	int update(TitleContentDO titleContent);
	
	int remove(Long cid);
	
	int batchRemove(Long[] cids);

	Integer selectMaxLevelByClassId(Integer tid);

	List<TitleContentDO> selectParentListByLevel(TitleContentDO titleContentDO);

	List<TitleContentTable> selectTitleContentList();

    List<TitleContentTable> selectTitleContentListByLikeName(String likeName);
}
