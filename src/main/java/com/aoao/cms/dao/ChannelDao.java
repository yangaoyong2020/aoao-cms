package com.aoao.cms.dao;

import java.util.List;

import com.aoao.cms.domain.Category;
import com.aoao.cms.domain.Channel;

/**
 * 
 * @ClassName: ChannelDao 
 * @Description: 栏目
 * @author: Administrator
 * @date: 2020年3月5日 上午10:06:17
 */
public interface ChannelDao {
	
	//查询所有栏目
   List<Channel> selects();
   
   //查询五个栏目下的分类
   List<Category> selectsByChannelId(Integer channelId);
}
