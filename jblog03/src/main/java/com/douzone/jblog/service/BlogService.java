package com.douzone.jblog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.douzone.jblog.repository.BlogRepository;
import com.douzone.jblog.vo.BlogVo;
import com.douzone.jblog.vo.UserVo;

@Service
public class BlogService {

	@Autowired
	private BlogRepository blogRepository;

	public Boolean insert(UserVo vo) {
		return blogRepository.insert(vo);
	}

	public BlogVo Select(String id) {
		return blogRepository.select(id);
	}

	public BlogVo getBlogList(String id) {
		BlogVo BlogVo = blogRepository.select(id);
		return BlogVo;
	}

	public Boolean update(BlogVo vo) {
		return blogRepository.update(vo);
	}
}
