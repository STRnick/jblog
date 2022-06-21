package com.douzone.jblog.vo;

public class PostVo {
	private long no;
	private String postTitle;
	private String contents;
	private long categoryNo;

	public long getNo() {
		return no;
	}

	public void setNo(long no) {
		this.no = no;
	}

	public String getPostTitle() {
		return postTitle;
	}

	public void setPostTitle(String postTitle) {
		this.postTitle = postTitle;
	}

	public String getContents() {
		return contents;
	}

	public void setContents(String contents) {
		this.contents = contents;
	}

	public long getCategoryNo() {
		return categoryNo;
	}

	public void setCategoryNo(long categoryNo) {
		this.categoryNo = categoryNo;
	}

	@Override
	public String toString() {
		return "PostVo [no=" + no + ", postTitle=" + postTitle + ", contents=" + contents + ", categoryNo=" + categoryNo
				+ "]";
	}

}
