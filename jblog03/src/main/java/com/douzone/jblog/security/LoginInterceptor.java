package com.douzone.jblog.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;

import com.douzone.jblog.service.UserService;
import com.douzone.jblog.vo.UserVo;

public class LoginInterceptor implements HandlerInterceptor {

	@Autowired
	private UserService userService;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		System.out.println("LoginInterceptor 진입확인");
		
		String id = request.getParameter("id");
		String password = request.getParameter("password");
		
		UserVo authUser = userService.getUser(id, password);
		
		if(authUser == null) {
			System.out.println("LoginInterceptor 1번");
			request.setAttribute("id", id);
			request.setAttribute("result", "fail");
			request.getRequestDispatcher("/WEB-INF/views/user/login.jsp").forward(request, response);
			return false;
		}
		
		/* session 처리 */
		HttpSession session = request.getSession(true);
		session.setAttribute("authUser", authUser);
		System.out.println(authUser);
		
		response.sendRedirect(request.getContextPath());		
		
		return false;
	}
	
}
