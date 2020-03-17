package com.aoao.cms.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.aoao.cms.domain.Article;
import com.aoao.cms.domain.Category;
import com.aoao.cms.domain.Channel;
import com.aoao.cms.domain.Collect;
import com.aoao.cms.domain.Comment;
import com.aoao.cms.domain.Slide;
import com.aoao.cms.domain.User;
import com.aoao.cms.service.ArticleService;
import com.aoao.cms.service.ChannelService;
import com.aoao.cms.service.CollectService;
import com.aoao.cms.service.CommentService;
import com.aoao.cms.service.SlideService;
import com.github.pagehelper.PageInfo;

/**
 * 
 * @ClassName: IndexController 
 * @Description: 系统首页入口
 * @author: Administrator
 * @date: 2020年3月9日 上午11:19:53
 */
@Controller
public class IndexController {

	@Autowired
	private ChannelService channelservice;
	
	@Autowired
	private ArticleService articleService;
	
	@Autowired
	private SlideService slideService;
	
	@Autowired
	private CommentService commentService;
	
	@Autowired
	private CollectService collectService;
	
	@RequestMapping(value={"","/","index"})
	public String index(Model m,Article article,@RequestParam(defaultValue="1")Integer page,@RequestParam(defaultValue="5")Integer pagesize){
		article.setStatus(1);//只显示审核过的文章
		article.setDeleted(0);//只显示未删除的
		//封装article查询条件
		m.addAttribute("article",article);
		//查询左侧栏目
		List<Channel> channels = channelservice.selects();
		m.addAttribute("channels",channels);
		
		//如果栏目ID 不为空 则查询其下所有的分类
		if(article.getChannelId()!=null){
		  List<Category> categorys = channelservice.selectsByChannelId(article.getChannelId());
		  System.out.println(categorys);
		  m.addAttribute("categorys",categorys);
		}
		//如果栏目ID 为空 则查询其下默认的热点文章
		if(article.getChannelId()==null){
			article.setHot(1);
			//查询轮播图
			List<Slide> slides = slideService.selects();
			m.addAttribute("slides",slides);
		}
		
		//查询所有的文章
		PageInfo<Article> info = articleService.selects(article, page, pagesize);
		m.addAttribute("info",info);
		
		//在右侧显示10篇文章
		Article article2 = new Article();
		article2.setStatus(1);
		article2.setDeleted(0);
		PageInfo<Article> lastArticles = articleService.selects(article2, 1, 10);
		m.addAttribute("lastArticles",lastArticles);
		
		
		return "index/index";
	}
	
	/**
	 * 
	 * @Title: articleDetail 
	 * @Description: 文章详情
	 * @param id
	 * @return
	 * @return: String
	 */
	@RequestMapping("articleDetail")
	public String articleDetail(HttpSession session,Model m,Integer id,@RequestParam(defaultValue="1")Integer page,@RequestParam(defaultValue="5")Integer pagesize){
		Article article = articleService.select(id);
		m.addAttribute("article",article);
		
		//查询处当前文章的评论信息
		PageInfo<Comment> info = commentService.selects(article, page, pagesize);
		
		//查询所有文章的评论
		PageInfo<Article> info2 = commentService.selectsByCommentNum( 1, 10);

		m.addAttribute("info",info);
		m.addAttribute("info2",info2);
		
		//查询该文章是否被收藏过
		User user = (User) session.getAttribute("user");
		Collect collect =null;
		if(null!=user){//如果用户已经登录 则查询是否被收藏过
		  collect = collectService.selectByTitleAndUserId(article.getTitle(), user.getId());
		}
		m.addAttribute("collect",collect);
		
		
		return "index/articleDetail";
	}
	//增加评论'
	@ResponseBody
	@RequestMapping("addComment")
	public boolean addConmment(Comment comment,Integer articleId,HttpSession session){
		User user = (User) session.getAttribute("user");

		if(null==user)
			return false;//没有登录的用户不能评论
			comment.setUserId(user.getId());
			comment.setArticleId(articleId);
			comment.setCreated(new Date());
		return commentService.insert(comment)>0;
	}
	
	//增加收藏'
		@ResponseBody
		@RequestMapping("collect")
		public boolean collect(Collect collect,HttpSession session){
			User user = (User) session.getAttribute("user");

			if(null==user)
				return false;//没有登录的用户不能收藏
			    collect.setUser(user);//收藏人
			    collect.setCreated(new Date());//收藏时间
			
			return collectService.insert(collect)>0;
		}
		
		//取消收藏'
				@ResponseBody
				@RequestMapping("deletecollect")
				public boolean deletecollect(Integer id){
					return collectService.delete(id)>0;
	}
	
	
}
