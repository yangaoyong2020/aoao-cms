package com.aoao.cms.dao;

import java.util.List;

import com.aoao.cms.domain.Article;
import com.aoao.cms.domain.Comment;

public interface CommentDao {

	//增加评论
	int insert(Comment comment);
	
	//查询评论 根据文章
	List<Comment> selects(Article article);
	
	
	//查询评论 按照评论数量排序
		
    List<Article> selectsByCommentNum();
	
	//让评论数增加一
	int updateArticle(Integer articleId);
}
