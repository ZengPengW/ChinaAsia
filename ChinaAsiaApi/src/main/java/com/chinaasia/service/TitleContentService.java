package com.chinaasia.service;



import com.chinaasia.pojo.TitleContentTable;

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
    List<TitleContentTable> selectDirectoryByParentIdAndClassName(Map<String, Object> map);


}
