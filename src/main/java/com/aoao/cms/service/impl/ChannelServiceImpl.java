package com.aoao.cms.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aoao.cms.dao.ChannelDao;
import com.aoao.cms.domain.Category;
import com.aoao.cms.domain.Channel;
import com.aoao.cms.service.ChannelService;
@Service
public class ChannelServiceImpl implements ChannelService {

	@Autowired
	private ChannelDao channeldao;
	@Override
	public List<Channel> selects() {
		
		return channeldao.selects();
	}
	@Override
	public List<Category> selectsByChannelId(Integer channelId) {
		// TODO Auto-generated method stub
		return channeldao.selectsByChannelId(channelId);
	}

}
