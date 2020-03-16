package com.aoao.cms.service;

import java.util.List;

import com.aoao.cms.domain.Article;
import com.github.pagehelper.PageInfo;

public interface ArticleService {
	  //增加文章
		int insert(Article article);
		//文章列表
		PageInfo<Article> selects(Article article,Integer page,Integer pagesize);
		
		Article select(Integer id);
		//更新文章 hot
		int update(Article article);
}
