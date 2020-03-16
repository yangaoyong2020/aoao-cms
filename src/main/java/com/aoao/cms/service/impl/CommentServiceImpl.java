package com.aoao.cms.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aoao.cms.dao.CommentDao;
import com.aoao.cms.domain.Article;
import com.aoao.cms.domain.Comment;
import com.aoao.cms.service.CommentService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
@Service
public class CommentServiceImpl implements CommentService {

	@Autowired
	private CommentDao commentdao;
	@Override
	public int insert(Comment comment) {
		
		try {
			//增加评论
			commentdao.insert(comment);
			//改变文章的评论数据+1
			int i = commentdao.updateArticle(comment.getArticleId());
			System.out.println(i+"11111111111111111111111111");
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return 0;
	}

	@Override
	public PageInfo<Comment> selects(Article article, Integer page, Integer pagesize) {
		PageHelper.startPage(page, pagesize);
		List<Comment> list = commentdao.selects(article);
		return new PageInfo<Comment>(list);
	}

	@Override
	public PageInfo<Article> selectsByCommentNum(Integer page, Integer pagesize) {
		PageHelper.startPage(page, pagesize);
		List<Article> list = commentdao.selectsByCommentNum();
		return new PageInfo<Article>(list) ;
	}

}
