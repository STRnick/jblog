package com.douzone.jblog.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.douzone.jblog.vo.BlogVo;
import com.douzone.jblog.vo.UserVo;

@Repository
public class BlogRepository {
	@Autowired
	private SqlSession sqlSession;
	
//	default 값
	public boolean insert(UserVo vo) {
		return sqlSession.insert("blog.insert", vo) == 1;
	}

	public List<BlogVo> findCategory(String id) {
		Map<String, Object> map = new HashMap<>();
		System.out.println(id);
		map.put("id", id);

		List<BlogVo> list = sqlSession.selectList("blog.findCategory", map);
		return list;
	}
	
	public boolean update(BlogVo vo) {
		return sqlSession.insert("blog.update", vo) == 1;
	}
	
	public BlogVo select(String id) {
		return sqlSession.selectOne("blog.select", id);
	}
}