package com.douzone.jblog.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

	public Map<String, Object> getCategoryList(String id) {
		Map<String, Object> map = new HashMap<>();
		List<BlogVo> list = null;
		list = blogRepository.findCategory(id);
		map.put("list", list);

		return map;
	}

	public Boolean update(BlogVo vo) {
		return blogRepository.update(vo);
	}
}
