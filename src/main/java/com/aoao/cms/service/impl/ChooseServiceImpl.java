package com.aoao.cms.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aoao.cms.dao.ChooseDao;
import com.aoao.cms.domain.Choose;
import com.aoao.cms.service.ChooseService;
@Service
public class ChooseServiceImpl implements ChooseService {

	@Autowired
	private ChooseDao choosedao;
	@Override
	public int insert(Choose choose) {
		
		return choosedao.insert(choose);
	}

}
