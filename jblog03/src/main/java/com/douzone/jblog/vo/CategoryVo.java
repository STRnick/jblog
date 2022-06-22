package com.douzone.jblog.vo;

public class CategoryVo {
	private long no;
	private String categoryName;
	private String description;
	private String blogId;
	private long categoryCount;

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

	public long getCategoryCount() {
		return categoryCount;
	}

	public void setCategoryCount(long categoryCount) {
		this.categoryCount = categoryCount;
	}

	@Override
	public String toString() {
		return "CategoryVo [no=" + no + ", categoryName=" + categoryName + ", description=" + description + ", blogId="
				+ blogId + ", categoryCount=" + categoryCount + "]";
	}

}
