package com.douzone.jblog.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.douzone.jblog.repository.PostRepository;
import com.douzone.jblog.vo.PostVo;

@Service
public class PostService {
	
	@Autowired
	private PostRepository postRepository;
	
	public PostVo view(Long no){
		return postRepository.view(no);
	}
	
	public boolean write(PostVo vo) {
		return postRepository.write(vo);
	}

	public List<PostVo> getPostList(Long categoryNo){
		return postRepository.getPostList(categoryNo);
	}

}
