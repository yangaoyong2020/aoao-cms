package com.aoao.cms.service;

import java.util.List;

import com.aoao.cms.domain.Category;
import com.aoao.cms.domain.Channel;

public interface ChannelService {
	List<Channel> selects();
	

	   //查询五个栏目下的分类
	   List<Category> selectsByChannelId(Integer channelId);
}
