package com.aoao.cms.dao;

import java.util.List;

import com.aoao.cms.domain.Article;

public interface ArticleDao {
     //增加文章
	int insert(Article article);
	//文章列表
	List<Article> selects(Article article);
	
	//单个文章显示内容
	Article select(Integer id);
	
	//更新文章 hot
	int update(Article article);
}
