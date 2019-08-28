package com.bootdo.api.service;

import com.bootdo.api.domain.TitleContentDO;
import com.bootdo.api.pojo.TitleContentTable;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author zp
 * @email 1992lcg@163.com
 * @date 2019-08-27 11:03:42
 */
public interface TitleContentService {
	
	TitleContentDO get(Long cid);
	
	List<TitleContentDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(TitleContentDO titleContent);
	
	int update(TitleContentDO titleContent);
	
	int remove(Long cid);
	
	int batchRemove(Long[] cids);

	//根据分类id查询最大目录级别
    Integer selectMaxLevelByClassId(Integer tid);

    //根据级别和分类进行查询父类列表
    List<TitleContentDO> selectParentListByLevel(TitleContentDO titleContentDO);

    //多表查询导航标题内容表
    List<TitleContentTable> selectTitleContentList();
    //like name
    List<TitleContentTable> selectTitleContentListByLikeName(String likeName);

}
