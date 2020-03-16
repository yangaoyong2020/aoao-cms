package com.aoao.cms.service;

import java.util.List;

import com.aoao.cms.domain.Article;
import com.aoao.cms.domain.Comment;
import com.github.pagehelper.PageInfo;

public interface CommentService {
	//增加评论
		int insert(Comment comment);
		
		//查询评论 根据文章
		PageInfo<Comment> selects(Article article,Integer page,Integer pagesize);
		
		//查询评论 按照评论数量排序
		
	    PageInfo<Article> selectsByCommentNum(Integer page,Integer pagesize);
}
