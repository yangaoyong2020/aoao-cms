package com.aoao.cms.dao;

import java.util.List;

import com.aoao.cms.domain.User;

public interface UserDao {
     //用户列表
	List<User> selects(User user);
	
	//更新用户
	int update(User user);
	
	//增加
	int insert(User user);
	
	//根据用户查询 用户是否存在
	User selectByUsername(String username);
}
