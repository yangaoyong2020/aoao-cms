package com.aoao.cms.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aoao.cms.dao.SlideDao;
import com.aoao.cms.domain.Slide;
import com.aoao.cms.service.SlideService;
@Service
public class SlideServiceImpl implements SlideService {

	@Autowired
	private SlideDao slidedao;
	@Override
	public List<Slide> selects() {
		
		return slidedao.selects();
	}

}
