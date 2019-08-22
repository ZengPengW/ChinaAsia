package com.bootdo.api.service;

import com.bootdo.api.domain.ApiDo;

import java.util.List;

public interface ApiService {
    /**
     * 查询所有
     * @return
     */
    public List<ApiDo> selectAll();

    /**
     * 根据id删除 api
     * @param id
     */
    public void deleteById(Long id);

}
