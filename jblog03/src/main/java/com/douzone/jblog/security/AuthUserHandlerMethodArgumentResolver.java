package com.douzone.jblog.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebArgumentResolver;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import com.douzone.jblog.vo.UserVo;

public class AuthUserHandlerMethodArgumentResolver implements HandlerMethodArgumentResolver {

	@Override
	public boolean supportsParameter(MethodParameter parameter) {
		AuthUser authUser = parameter.getParameterAnnotation(AuthUser.class);
		
		// @AuthUser가 안붙어 있으면
		if(authUser == null) {
			System.out.println("AuthUserHandlerMethodArgumentResolver 1번");
			return false;
		}
		
		// Parameter Type이 UserVo가 아니면 
		if(!parameter.getParameterType().equals(UserVo.class)) {
			System.out.println("AuthUserHandlerMethodArgumentResolver 2번");
			return false;
		}
		
		return true;
	}

	@Override
	public Object resolveArgument(
			MethodParameter parameter,
			ModelAndViewContainer mavContainer,
			NativeWebRequest webRequest, 
			WebDataBinderFactory binderFactory) throws Exception {
		if(!supportsParameter(parameter)) {
			return WebArgumentResolver.UNRESOLVED;
		}
		
		HttpServletRequest request = (HttpServletRequest) webRequest.getNativeRequest();
		HttpSession session = request.getSession();
		
		if(session == null) {
			return null;
		}
		
		return session.getAttribute("authUser");
	}
}
