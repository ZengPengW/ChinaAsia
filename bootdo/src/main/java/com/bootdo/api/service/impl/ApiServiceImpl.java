package com.bootdo.api.service.impl;

import com.bootdo.api.dao.ApiDao;
import com.bootdo.api.domain.ApiDo;
import com.bootdo.api.service.ApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    public void deleteById(Long id) {
        apiDao.deleteById(id);
    }
}
