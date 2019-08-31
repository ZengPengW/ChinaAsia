package com.chinaasia.service;


import com.chinaasia.pojo.ApiDo;

import java.util.List;

public interface ApiService {
    /**
     * 查询所有
     * @return
     */
    public List<ApiDo> selectAll();


    /**
     * 根据id 查询接口
     * @param id
     * @return
     */
    public ApiDo selectById(Long id);

    /**
     * 根据名字查询
     * @param name
     * @return
     */
    public List<ApiDo> selectByName(String name);


}
