package com.aoao.cms.controller;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.UUID;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.aoao.cms.domain.Article;
import com.aoao.cms.domain.User;
import com.aoao.cms.service.ArticleService;
import com.github.pagehelper.PageInfo;
/**
 * 
 * @ClassName: MyController 
 * @Description: 个人中心 
 * @author: Administrator
 * @date: 2020年3月4日 下午2:53:05
 */
@RequestMapping("my")
@Controller
public class MyController {
	@Autowired
	private ArticleService articleservice;
	
	
	
     //进入个人中心的首页
	// 对外提供的三种映射
	@RequestMapping(value={"","/","index"})
	public String index(){
		
		
		return "my/index";
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
		return articleservice.select(id);
	}
	
	
	//我的文章
	@RequestMapping("articles")
	public String articles(Model m,HttpSession session,@RequestParam(defaultValue="1")Integer page,@RequestParam(defaultValue="3")Integer pagesize){
		Article article = new Article();
		User user = (User) session.getAttribute("user");//获取当前用户人发变的文章
		article.setUserId(user.getId());
         
		PageInfo<Article> info = articleservice.selects(article, page, pagesize);
		m.addAttribute("info",info);
		return "my/articles";
	}
	
	//去发布文章
	@GetMapping("publis")
	public String publis(){
		return "my/publis";
	}
	
	//发布文章
	@ResponseBody
	@PostMapping("publis")
	public boolean publis(Model m,MultipartFile file,Article article,HttpSession session ){
		//文件上传
		if(null!=file &&!file.isEmpty()){
			//储存地方
			String path="d:/pic/";
			
			 //文件的原始名称
			String filename = file.getOriginalFilename();
			//为了防止文件重名 需要改变文件的名字
			String newFilename=UUID.randomUUID()+filename.substring(filename.lastIndexOf("."));
			
			File f= new File(path, newFilename);
			
			//把文件写入硬盘
			try {
				file.transferTo(f);
				article.setPicture(newFilename);//文件的名称
			} catch (IllegalStateException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}	
		}
		//文章的初始化数据
		User user = (User) session.getAttribute("user");
		article.setUserId(user.getId());//发布人
		article.setCreated(new Date());
		article.setHits(0);//点击默认0
		article.setDeleted(0);//默认删除
		article.setHot(0);//默认非热门
		article.setStatus(0);//默认待审核
		return articleservice.insert(article)>0;
	}
}
