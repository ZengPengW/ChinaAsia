package com.bootdo.api.dao;


import com.bootdo.api.domain.TitleClassDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2019-08-27 10:45:50
 */
@Mapper
public interface TitleClassDao {

    public TitleClassDO get(Integer tid);

    public List<TitleClassDO> list(Map<String, Object> map);

    public int count(Map<String, Object> map);

    public	int save(TitleClassDO titleClassDO);

    public	int update(TitleClassDO titleClassDO);

    public	int remove(Integer tid);

    public	int batchRemove(Integer[] tids);
}
