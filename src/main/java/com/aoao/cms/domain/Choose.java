package com.aoao.cms.domain;

import java.io.Serializable;
import java.util.Date;

public class Choose implements Serializable {

	/**
	 * @fieldName: serialVersionUID
	 * @fieldType: long
	 * @Description: TODO
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private Date chooseDate;
	private Integer articleId;
	private String option;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Date getChooseDate() {
		return chooseDate;
	}
	public void setChooseDate(Date chooseDate) {
		this.chooseDate = chooseDate;
	}
	public Integer getArticleId() {
		return articleId;
	}
	public void setArticleId(Integer articleId) {
		this.articleId = articleId;
	}
	public String getOption() {
		return option;
	}
	public void setOption(String option) {
		this.option = option;
	}
	public Choose() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "Choose [id=" + id + ", chooseDate=" + chooseDate + ", articleId=" + articleId + ", option=" + option
				+ "]";
	}
	
}
