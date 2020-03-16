package com.aoao.cms.service;

import java.util.List;

import com.aoao.cms.domain.User;
import com.github.pagehelper.PageInfo;

public interface UserService {

	  //用户列表
	  PageInfo<User> selects(User user,Integer page,Integer pagesize);
	  
	//更新用户
	int update(User user);
		
	//增加
		int insert(User user);
		
	//根据用户查询 用户是否存在
		User selectByUsername(String username);
		
		//登录
      User login(User user);
}
