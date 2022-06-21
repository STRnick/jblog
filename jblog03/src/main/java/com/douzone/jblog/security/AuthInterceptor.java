package com.douzone.jblog.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import com.douzone.jblog.vo.UserVo;

public class AuthInterceptor implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		
		// 1. Hander 종류 확인
		if(handler instanceof HandlerMethod == false) {
			// DefaultservletHandler가 처리하는 정적 자원
			return true;
		}
		
		// 2. casting
		HandlerMethod handlerMethod = (HandlerMethod) handler;
		
		// 3. Handler Method의 @Auth 받아보기
		System.out.println("AuthInterceptor 3번");
		Auth auth = handlerMethod.getMethodAnnotation(Auth.class);
		
		// 4. Handler Method에 @Auth가 없으면 Type에 붙어 있는지 확인
		if(auth == null) {
			/* 과제 */
			System.out.println("AuthInterceptor 4번");
			auth = handlerMethod.getBeanType().getAnnotation(Auth.class);
		}
		
		// 5. Type과 Method 모두에 @Auth가 안붙어 있는 경우
		if(auth == null) {
			return true;
		}
		
		//6. @Auth가 적용이 되어있기 때문에 인증(Authentication) 여부 확인
		HttpSession session = request.getSession();
		if(session==null) {
			System.out.println("AuthInterceptor 6-1번");
			response.sendRedirect(request.getContextPath()+"/user/login");
			return false;
		}	
		UserVo authUser = (UserVo)session.getAttribute("authUser");
		if(authUser==null) {
			System.out.println("AuthInterceptor 6-2번");
			response.sendRedirect(request.getContextPath()+"/user/login");
			return false;
		}
		
//		//7. 권한(Authorization)체크를 위해서 @Auth의 role 가져오기("USER","ADMIN")
//		String role = auth.role();
//	
//		//8. @Auth의 role이 "USER"인 경우, authUser의 role은 상관이 없다
//		if("USER".equals(role)) {
//			System.out.println("AuthInterceptor 8번");
//			return true;
//		}

		return true;
	}
	
}
