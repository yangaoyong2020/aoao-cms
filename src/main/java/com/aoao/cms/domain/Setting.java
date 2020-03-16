package com.aoao.cms.domain;
/**
 * 
 * @ClassName: Setting 
 * @Description: 系统配置表
 * @author: Administrator
 * @date: 2020年3月3日 上午11:39:54
 */
public class Setting {

	private Integer id;
	private String siteDomain;
	private String siteName;
	private Integer article_list_size;//文章显示的t条目
	private Integer slide_size;//显示多少个广告
	private String adminUsername;
	private String adminpassword;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getSiteDomain() {
		return siteDomain;
	}
	public void setSiteDomain(String siteDomain) {
		this.siteDomain = siteDomain;
	}
	public String getSiteName() {
		return siteName;
	}
	public void setSiteName(String siteName) {
		this.siteName = siteName;
	}
	public Integer getArticle_list_size() {
		return article_list_size;
	}
	public void setArticle_list_size(Integer article_list_size) {
		this.article_list_size = article_list_size;
	}
	public Integer getSlide_size() {
		return slide_size;
	}
	public void setSlide_size(Integer slide_size) {
		this.slide_size = slide_size;
	}
	public String getAdminUsername() {
		return adminUsername;
	}
	public void setAdminUsername(String adminUsername) {
		this.adminUsername = adminUsername;
	}
	public String getAdminpassword() {
		return adminpassword;
	}
	public void setAdminpassword(String adminpassword) {
		this.adminpassword = adminpassword;
	}
	public Setting(Integer id, String siteDomain, String siteName, Integer article_list_size, Integer slide_size,
			String adminUsername, String adminpassword) {
		super();
		this.id = id;
		this.siteDomain = siteDomain;
		this.siteName = siteName;
		this.article_list_size = article_list_size;
		this.slide_size = slide_size;
		this.adminUsername = adminUsername;
		this.adminpassword = adminpassword;
	}
	public Setting() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "Setting [id=" + id + ", siteDomain=" + siteDomain + ", siteName=" + siteName + ", article_list_size="
				+ article_list_size + ", slide_size=" + slide_size + ", adminUsername=" + adminUsername
				+ ", adminpassword=" + adminpassword + "]";
	}
	
}
