package com.aoao.cms.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aoao.cms.dao.ArticleDao;
import com.aoao.cms.domain.Article;
import com.aoao.cms.service.ArticleService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
@Service
public class ArticleServiceImpl implements ArticleService {

	@Autowired
	private ArticleDao articledao;
	@Override
	public int insert(Article article) {
		
		return articledao.insert(article);
	}

	@Override
	public PageInfo<Article> selects(Article article,Integer page,Integer pagesize) {
		PageHelper.startPage(page, pagesize);
		List<Article> list = articledao.selects(article);
		return new PageInfo<Article>(list);
	}

	@Override
	public Article select(Integer id) {
		// TODO Auto-generated method stub
		return articledao.select(id);
	}

	@Override
	public int update(Article article) {
		// TODO Auto-generated method stub
		return articledao.update(article);
	}

}
