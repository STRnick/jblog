package com.douzone.jblog.vo;

public class CategoryVo {
	private long no;
	private String categoryName;
	private String description;
	private String blogId;

	public long getNo() {
		return no;
	}

	public void setNo(long no) {
		this.no = no;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getBlogId() {
		return blogId;
	}

	public void setBlogId(String blogId) {
		this.blogId = blogId;
	}

	@Override
	public String toString() {
		return "CategoryVo [no=" + no + ", categoryName=" + categoryName + ", description=" + description + ", blogId="
				+ blogId + "]";
	}

}
