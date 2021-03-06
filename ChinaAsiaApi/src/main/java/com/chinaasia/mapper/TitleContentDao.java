package com.chinaasia.mapper;


import com.chinaasia.pojo.TitleContentTable;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * 
 * @author zp
 * @email 1992lcg@163.com
 * @date 2019-08-27 11:03:42
 */
@Mapper
public interface TitleContentDao {

    List<TitleContentTable> selectDirectoryByParentIdAndClassName(Map<String, Object> map);

}
