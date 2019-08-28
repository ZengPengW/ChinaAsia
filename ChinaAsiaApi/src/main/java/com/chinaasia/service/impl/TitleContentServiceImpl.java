package com.chinaasia.service.impl;


import com.chinaasia.mapper.TitleContentDao;
import com.chinaasia.pojo.TitleContentTable;
import com.chinaasia.service.TitleContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class TitleContentServiceImpl implements TitleContentService {
    @Autowired
    private TitleContentDao titleContentDao;

    @Override
    public List<TitleContentTable> selectDirectoryByParentIdAndClassName(Map<String, Object> map) {
        return titleContentDao.selectDirectoryByParentIdAndClassName(map);
    }
}
