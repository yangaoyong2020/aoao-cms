package com.aoao.cms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.aoao.cms.domain.Article;
import com.aoao.cms.domain.User;
import com.aoao.cms.service.ArticleService;
import com.aoao.cms.service.UserService;
import com.github.pagehelper.PageInfo;
/**
 * 
 * @ClassName: AdminController 
 * @Description: 管理员中心
 * @author: Administrator
 * @date: 2020年3月6日 上午10:00:09
 */
@RequestMapping("admin")
@Controller
public class AdminController {
	@Autowired
	private ArticleService articleService;
	@Autowired
	private UserService userservice;
/**
 * 
 * @Title: Admin 
 * @Description: 进入管路元首页
 * @return
 * @return: String
 */
	@RequestMapping(value={"","/","index"})
	public String Admin(){
		return "admin/index";
	}
	
	/**
	 * 进入文章审核列表
	 */
	@RequestMapping("articles")
	public String articles(Model m,Article article,@RequestParam(defaultValue="1")Integer page,@RequestParam(defaultValue="5")Integer pagesize){
		PageInfo<Article> info = articleService.selects(article, page, pagesize);
		m.addAttribute("info",info);
		m.addAttribute("article",article);
		return "admin/articles";
	}
	
	//修改文章热门
	@ResponseBody
	@RequestMapping("update")
	public boolean update(Article article){
		return articleService.update(article)>0;
	}
	
	/**
	 * 
	 * @Title: articleDetail 
	 * @Description: 单个文章内容
	 * @param id
	 * @return
	 * @return: Article
	 */
	@ResponseBody
	@RequestMapping("articleDetail")
	public Article articleDetail(Integer id){
		return articleService.select(id);
	}
	
	//管理员 用户列表
	@RequestMapping("users")
	public String users(Model m,User user,@RequestParam(defaultValue="1")Integer page,@RequestParam(defaultValue="5")Integer pagesize){
		PageInfo<User> info = userservice.selects(user, page, pagesize);
		m.addAttribute("info",info);
		m.addAttribute("user",user);
		return "admin/users";
	}
	
	//更新用户
	@ResponseBody
	@RequestMapping("updateUser")
	public boolean updateUser(User user){
		return userservice.update(user)>0;
	}
	
}
