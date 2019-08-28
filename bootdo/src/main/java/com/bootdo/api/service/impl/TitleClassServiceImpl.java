package com.bootdo.api.service.impl;

import com.bootdo.api.dao.TitleClassDao;
import com.bootdo.api.domain.TitleClassDO;
import com.bootdo.api.service.TitleClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;




@Service
public class TitleClassServiceImpl implements TitleClassService {
	@Autowired
	private TitleClassDao classDao;
	
	@Override
	public TitleClassDO get(Integer tid){
		return classDao.get(tid);
	}
	
	@Override
	public List<TitleClassDO> list(Map<String, Object> map){
		return classDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return classDao.count(map);
	}
	
	@Override
	public int save(TitleClassDO titleClassDO){
		return classDao.save(titleClassDO);
	}
	
	@Override
	public int update(TitleClassDO titleClassDO){
		return classDao.update(titleClassDO);
	}
	
	@Override
	public int remove(Integer tid){
		return classDao.remove(tid);
	}
	
	@Override
	public int batchRemove(Integer[] tids){
		return classDao.batchRemove(tids);
	}
	
}
