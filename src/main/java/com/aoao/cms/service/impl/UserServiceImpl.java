package com.aoao.cms.service.impl;


import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aoao.cms.dao.UserDao;
import com.aoao.cms.domain.User;
import com.aoao.cms.service.UserService;
import com.aoao.cms.util.CMSException;
import com.aoao.cms.util.Md5Util;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yay.utils.StringUtil;
@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userdao;
	@Override
	public PageInfo<User> selects(User user, Integer page, Integer pagesize) {
		PageHelper.startPage(page, pagesize);
		List<User> list = userdao.selects(user);
		return new PageInfo<User>(list);
	}
	@Override
	public int update(User user) {
		// TODO Auto-generated method stub
		return userdao.update(user);
	}
	@Override
	public int insert(User user) {
		/*int i =10/0;*/
		//通过自定义规则 对注册用户进行校验
		//1用户名不能为空 长度在合理区间 
		if(!StringUtil.hasText(user.getUsername()))
		  throw new CMSException("用户名不能为空");	
		if(!(user.getUsername().length()>=2 && user.getUsername().length()<=10))
		  throw new CMSException("用户名在二到10之间");	
		 User findUser = this.selectByUsername(user.getUsername());
		if(null!=findUser){
			throw new CMSException("用户名已经被注册");	
		}
		//2密码验证
		if(!StringUtil.hasText(user.getPassword()))
	      throw new CMSException("密码不能为空");	
		if(!(user.getPassword().length()>=6 && user.getUsername().length()<=10))
	      throw new CMSException("密码在6到10之间");	
		//3确认密码和第一次输入密码一样
		if(!StringUtil.hasText(user.getRepassword()))
		  throw new CMSException("确认密码不能为空");	
		if(!user.getRepassword().equals(user.getPassword())){
		  throw new CMSException("确认密码和第一次输入密码一样");	
		}
		//4对用户密码进行加密处理
		user.setPassword(Md5Util.encode(user.getPassword()));
		//初始用户的注册信息
		user.setCreated(new Date());//注册时间
		user.setNickname(user.getUsername());//默认用户昵称为用户名
		user.setLocked("0");//默认用户状态是可用的
		return userdao.insert(user);
	}
	@Override
	public User selectByUsername(String username) {
		
		return userdao.selectByUsername(username);
	}
	
	//登录
	@Override
	public User login(User user) {
		//1校验用户名不能为空
		if(!StringUtil.hasText(user.getUsername()))
		  throw new CMSException("用户名不能为空");	
		//2检查用户名是否存在
		User u = this.selectByUsername(user.getUsername());
		if(null==u){
		  throw new CMSException("该用户名不存在");	
		} 
		//比较密码是否一致  数据库存储的是不是加密后的密码
		  //对登录的密码再进行加密 在和数据库的密码进行比较
		if(!Md5Util.encode(user.getPassword()).equals(u.getPassword()))
		   throw new CMSException("密码不正确,请重新输入");	
		
		//判断账户是否被禁用
		if(u.getLocked().equals("1"))
		   throw new CMSException("当前账户被禁用 不能登录");
		return u;
	}

	
}
