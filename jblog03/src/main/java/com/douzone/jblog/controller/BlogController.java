package com.douzone.jblog.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.douzone.jblog.security.Auth;
import com.douzone.jblog.security.AuthUser;
import com.douzone.jblog.service.BlogService;
import com.douzone.jblog.service.CategoryService;
import com.douzone.jblog.service.FileUploadService;
import com.douzone.jblog.service.PostService;
import com.douzone.jblog.vo.BlogVo;
import com.douzone.jblog.vo.CategoryVo;
import com.douzone.jblog.vo.PostVo;
import com.douzone.jblog.vo.UserVo;

@Controller
@RequestMapping("/{id:(?!assets).*}")
public class BlogController {
	
	@Autowired
	private BlogService blogService;
	
	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private PostService postService;
	
	@Autowired
	private FileUploadService fileUploadService;
	
	@Autowired
	private ServletContext context;

	@RequestMapping({"", "/{pathNo1}", "/{pathNo1}/{pathNo2}"})
	public String index(
		@PathVariable("id") String id,
		@PathVariable("pathNo1") Optional<Long> pathNo1,
		@PathVariable("pathNo2") Optional<Long> pathNo2, Model model) {
		
		Long categoryNo = 0L;
		Long postNo = 0L;
		
		if(pathNo2.isPresent()) {
			categoryNo = pathNo1.get();
			postNo = pathNo2.get();
		} else if(pathNo1.isPresent()) {
			categoryNo = pathNo1.get();
		}
		
		BlogVo blogvo = blogService.Select(id);
		List<CategoryVo> catlist = categoryService.findList(id);
		List<PostVo> postlist = postService.getPostList(categoryNo);
	    PostVo postVo = postService.view(postNo);
		
		model.addAttribute("blogvo", blogvo);
		model.addAttribute("catlist", catlist);
		model.addAttribute("postlist", postlist);
		model.addAttribute("postVo", postVo);
		
		System.out.println(model);
				
		return "blog/main";
	}
	
	@Auth
	@RequestMapping("/admin/basic")
	public String adminBasic(
			@PathVariable("id") String id, 
			@AuthUser UserVo authUser, Model model) {
		
		if(!authUser.getId().equals(id)) {
			return "redirect:/";
		}
		
		BlogVo blogvo = blogService.Select(id);
		model.addAttribute("blogvo", blogvo);
		
		return "blog/admin/basic";
	}
	
	@Auth
	@RequestMapping("/admin/category")
	public String adminCategory(
			@PathVariable("id") String id,
			Model model) {
		List<CategoryVo> catlist = categoryService.findList(id);
		model.addAttribute("catlist", catlist);
		
		return "blog/admin/category";
   }
	
	@Auth
	@RequestMapping(value="/admin/update", method=RequestMethod.POST)
	public String mainUpdate(
			@RequestParam(value="file") MultipartFile multipartFile,
			@PathVariable("id") String id,
			BlogVo vo,
			@AuthUser UserVo uservo) {
		String url = fileUploadService.restore(multipartFile);	
		vo.setLogo(url);
		vo.setId(uservo.getId());
		blogService.update(vo);
		context.setAttribute("blogvo", vo);
		
		return "redirect:/" + id + "/admin/basic";
	}
	
	@Auth
	@RequestMapping(value="/category/add", method=RequestMethod.POST)
	   public String addCategory(@PathVariable("id") String id, String name, String desc) {
		categoryService.insertCategory(name, desc, id);
		
	      return "redirect:/"+ id + "/admin/category";
	   }
	
	@Auth
	@RequestMapping(value="/category/delete/{no}", method=RequestMethod.GET)
	public String category(
			@PathVariable(value = "no") Long categoryNo, 
			@AuthUser UserVo uservo) {
		categoryService.delete(categoryNo);
		return "redirect:/"+ uservo.getId() + "/admin/category";
	}
	
	@Auth
	@RequestMapping("/admin/write")
	   public String adminWrite(
			   @PathVariable("id") String id, 
			   Model model) {
		
		List<CategoryVo> list = categoryService.findList(id);
		model.addAttribute("list", list);
		
	      return "blog/admin/write";
	   }
	
	@Auth
	@RequestMapping(value="/admin/write",method=RequestMethod.POST)
	public String write(@AuthUser UserVo uservo, PostVo postvo, CategoryVo categoryvo) {
		postService.write(postvo);
		
		return "redirect:/"+ uservo.getId();
	}
}
