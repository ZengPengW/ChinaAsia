package com.bootdo.api.service;

import com.bootdo.api.domain.TitleClassDO;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2019-08-27 10:45:50
 */
public interface TitleClassService {

    TitleClassDO get(Integer tid);
	
	List<TitleClassDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(TitleClassDO titleClassDO);
	
	int update(TitleClassDO titleClassDO);
	
	int remove(Integer tid);
	
	int batchRemove(Integer[] tids);
}
