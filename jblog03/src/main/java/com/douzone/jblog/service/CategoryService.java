package com.douzone.jblog.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.douzone.jblog.repository.CategoryRepository;
import com.douzone.jblog.vo.CategoryVo;
import com.douzone.jblog.vo.UserVo;

@Service
public class CategoryService {

	@Autowired
	private CategoryRepository categoryRepository;

	public CategoryVo insert(UserVo vo) {
		return categoryRepository.insert(vo);
	}

	public CategoryVo insertCategory(String name, String desc, String id) {
		return categoryRepository.insertCategory(name, desc, id);
	}

	public boolean delete(Long no) {
		return categoryRepository.delete(no);
	}

	public List<CategoryVo> findList(String id) {
		return categoryRepository.findList(id);
	}
	
	public CategoryVo getCategoryNo(String categoryName, String blogId) {
		return categoryRepository.findNobyName(categoryName, blogId);
	}
}
