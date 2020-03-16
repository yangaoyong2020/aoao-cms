package com.aoao.cms.service.impl;

import java.awt.color.CMMException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aoao.cms.dao.CollectDao;
import com.aoao.cms.domain.Collect;
import com.aoao.cms.service.CollectService;
import com.yay.utils.StringUtil;

@Service
public class CollectServiceImpl implements CollectService {

	@Autowired
	private CollectDao collectDao ;
	@Override
	public int insert(Collect collect) {
		if(StringUtil.isHttpUrl(collect.getUrl()))
			throw new CMMException("不是合法的url");
		return collectDao.insert(collect);
	}

	@Override
	public int delete(Integer id) {
		// TODO Auto-generated method stub
		return collectDao.delete(id);
	}

	@Override
	public List<Collect> selects(Integer userId) {
		// TODO Auto-generated method stub
		return collectDao.selects(userId);
	}

	@Override
	public Collect selectByTitleAndUserId(String title, Integer userId) {
		// TODO Auto-generated method stub
		return collectDao.selectByTitleAndUserId(title, userId);
	}

}
