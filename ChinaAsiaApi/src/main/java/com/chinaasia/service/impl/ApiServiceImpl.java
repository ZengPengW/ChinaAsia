package com.chinaasia.service.impl;


import com.chinaasia.mapper.ApiDao;
import com.chinaasia.pojo.ApiDo;
import com.chinaasia.service.ApiService;
import com.chinaasia.utils.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class ApiServiceImpl implements ApiService {
    @Autowired
    private ApiDao apiDao;

    @Override
    public List<ApiDo> selectAll() {
        return apiDao.selectAll();
    }


    @Override
    public ApiDo selectById(Long id) {
        return apiDao.selectById(id);
    }

    @Override
    public List<ApiDo> selectByName(String name) {
        return apiDao.selectByName(name);
    }


}
