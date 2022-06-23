package com.douzone.jblog.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.douzone.jblog.vo.PostVo;

@Repository
public class PostRepository {
	
	@Autowired
	private SqlSession sqlSession;
	
	public PostVo view(Long no) {
		PostVo vo = new PostVo();
		vo.setNo(no);
		return sqlSession.selectOne("post.view", vo);
	}
	
	public boolean write(PostVo vo) {
		return sqlSession.update("post.write", vo) == 1;
	}

	public List<PostVo> getPostList(Long categoryNo, String id){
		Map<String,Object> map = new HashMap<>();
		map.put("categoryNo", categoryNo);
		map.put("id", id);
		
		return sqlSession.selectList("post.getpostlist", map);
	}

	public PostVo findPostContents(String id, Long categoryNo, Long postNo) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		map.put("categoryNo", categoryNo);
		map.put("postNo", postNo);
		
		
		return sqlSession.selectOne("post.findPostContents", map);
	}
}
