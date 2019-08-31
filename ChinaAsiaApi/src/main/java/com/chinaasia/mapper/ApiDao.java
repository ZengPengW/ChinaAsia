package com.chinaasia.mapper;


import com.chinaasia.pojo.ApiDo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface ApiDao {
    public List<ApiDo> selectAll();

    public ApiDo selectById(Long id);

    public List<ApiDo> selectByName(String name);

}
