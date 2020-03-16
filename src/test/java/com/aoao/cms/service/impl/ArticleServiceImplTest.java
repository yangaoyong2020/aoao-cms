package com.aoao.cms.service.impl;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.aoao.cms.domain.Article;
import com.aoao.cms.service.ArticleService;
import com.github.pagehelper.PageInfo;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:spring-beans.xml")
public class ArticleServiceImplTest {

	@Autowired
	private ArticleService articleservice;
	@Test
	public void testInsert() {
		Article article = new Article();
		article.setTitle("test");
		article.setUserId(1);
		article.setChannelId(1);
		article.setCategoryId(1);
		article.setContent("aaaaaaaaa");
		article.setUpdated(new Date());
		articleservice.insert(article);
	}

	@Test
	public void testSelects() {
		PageInfo<Article> info = articleservice.selects(null, 1, 10);
		System.out.println(info.getList());
	}

}
