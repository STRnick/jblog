package com.douzone.jblog.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.douzone.jblog.vo.CategoryVo;
import com.douzone.jblog.vo.UserVo;

@Repository
public class CategoryRepository {

	@Autowired
	private SqlSession sqlSession;

//	default 값
	public CategoryVo insert(UserVo vo) {
		return sqlSession.selectOne("category.insert", vo);
	}

	public CategoryVo insertCategory(String name, String desc, String id) {
		CategoryVo vo = new CategoryVo();
		vo.setCategoryName(name);
		vo.setDescription(desc);
		vo.setBlogId(id);
		return sqlSession.selectOne("category.insertCategory", vo);
	}

	public boolean delete(Long no) {
		return sqlSession.delete("category.delete", no) == 1;
	}

	public List<CategoryVo> findList(String id) {
		List<CategoryVo> list = sqlSession.selectList("category.findList", id);
		return list;
	}

	public CategoryVo findNobyName(String categoryName, String blogId) {
		Map<String,Object> map = new HashMap<>();
		map.put("categoryName", categoryName);
		map.put("blogId", blogId);
		
		return sqlSession.selectOne("category.selectNo", map);
	}
}
