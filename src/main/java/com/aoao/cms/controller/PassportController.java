package com.aoao.cms.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.aoao.cms.domain.User;
import com.aoao.cms.service.UserService;
import com.aoao.cms.util.CMSException;
import com.aoao.cms.util.Result;

/**
 * 
 * @ClassName: PassportController 
 * @Description: 系统的注册登录入口
 * @author: Administrator
 * @date: 2020年3月11日 上午10:35:45
 */
@Controller
@RequestMapping("passport")
public class PassportController {

	@Autowired
	private UserService userservice;
	//去注册
	@RequestMapping("reg")
	public String reg(){
		return "passport/reg";
	}
	
	//执行注册
	@PostMapping("reg")
	@ResponseBody
	public Result<User> reg(User user,Model m){
		   Result<User> result = new Result<User>();
		try {
			 if(userservice.insert(user)>0){//如果注册成功
				 result.setCode(200);//注册成功
				 result.setMsg("注册成功");
			 };
		} catch (CMSException e) {//如果是自定义异常
			result.setCode(300);
			result.setMsg("注册失败"+e.getMessage());
		}catch (Exception e) {//不可预知的异常
			e.printStackTrace();//把异常消息在控制台打印
			result.setCode(500);
			//给用户看的
			result.setMsg("注册失败,系统出现不可预知异常,请联系管理员");
		}
		return result;
	}
	
	//去登录 -普通用户
	@GetMapping("login")
	public String login(){
		return "passport/login";
	}
	
	 //去登录 -管理员用户
		@GetMapping("admin/login")
		public String adminLogin(){
			return "passport/adminLogin";
		}
		
	//普通用户执行登录
	@PostMapping("login")
	@ResponseBody
	public Result<User> login(User formUser,Model m,HttpSession session){
		Result<User> result = new Result<User>();
		try {
			//去登录 如果成功 咋返回用户的基本信息
			User user = userservice.login(formUser);
			if(null!=user){//有该用户
				result.setCode(200);
				result.setMsg("登录成功");
				if(user.getRole()==0){//根据角色判断 村不同session
					session.setAttribute("user", user);
				}else{
					session.setAttribute("admin", user);
				}
			}
			
		}catch (CMSException e) {//如果是自定义异常
			result.setCode(300);
			result.setMsg("登录失败"+e.getMessage());
		}catch (Exception e) {//不可预知的异常
			e.printStackTrace();//把异常消息在控制台打印
			result.setCode(500);
			//给用户看的
			result.setMsg("登录失败,系统出现不可预知异常,请联系管理员");
		}
		return result;
	}
	
	//管理员执行登录
		@PostMapping("admin/login")
		@ResponseBody
		public Result<User> adminlogin(User formUser,Model m,HttpSession session){
			Result<User> result = new Result<User>();
			try {
				//去登录 如果成功 咋返回用户的基本信息
				User user = userservice.login(formUser);
				if(null!=user && user.getRole().equals("1")){
					//登录成功 把用户的信息 存入session
					result.setCode(200);
					result.setMsg("登录成功");
					session.setAttribute("user", user);
				}
			}catch (CMSException e) {//如果是自定义异常
				result.setCode(300);
				result.setMsg("登录失败"+e.getMessage());
			}catch (Exception e) {//不可预知的异常
				e.printStackTrace();//把异常消息在控制台打印
				result.setCode(500);
				//给用户看的
				result.setMsg("登录失败,系统出现不可预知异常,请联系管理员");
			}
			return result;
		}
	
	//注销用户
	@GetMapping("logout")
	public String logout(HttpSession session){
		//清除session
		session.invalidate();
		//回到系统首页
		return "redirect:/";
	}
	
	//校验用户是否存在
	@ResponseBody
	@PostMapping("checkName")
	public boolean checkname(String username){
		return userservice.selectByUsername(username)==null;
	}
}
