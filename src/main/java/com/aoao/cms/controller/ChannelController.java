package com.aoao.cms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.aoao.cms.domain.Category;
import com.aoao.cms.domain.Channel;
import com.aoao.cms.service.ChannelService;
/**
 * 
 * @ClassName: ChannelController 
 * @Description:栏目控制器
 * @author: Administrator
 * @date: 2020年3月5日 上午10:23:20
 */
@Controller
@RequestMapping("channel")
public class ChannelController {

	//你好啊
	@Autowired
	private ChannelService channelservice;
	//查询所有的栏目
	@ResponseBody
	@RequestMapping("channels")
	public List<Channel> channels(){
		return channelservice.selects();
	}
	
	@ResponseBody
	@RequestMapping("selectsByChannelId")
	public List<Category> selectsByChannelId(Integer channelId){
		return channelservice.selectsByChannelId(channelId);
	}
}
