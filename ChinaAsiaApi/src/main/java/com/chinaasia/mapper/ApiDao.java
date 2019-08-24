package com.chinaasia.mapper;


import com.chinaasia.pojo.ApiDo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ApiDao {
    public List<ApiDo> selectAll();

    public ApiDo selectById(Long id);


}
