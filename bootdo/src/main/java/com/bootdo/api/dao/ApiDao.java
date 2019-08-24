package com.bootdo.api.dao;

import com.bootdo.api.domain.ApiDo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ApiDao {
    public List<ApiDo> selectAll();

    public void deleteById(Long id);

    public void saveApi(ApiDo apiDo);

    public ApiDo selectById(Long id);

    public void updateApi(ApiDo apiDo);

    public List<ApiDo> selectLikeName(String likeName);

}
