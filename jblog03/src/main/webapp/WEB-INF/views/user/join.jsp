<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>JBlog</title>
<Link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/jblog.css">
</head>
<body>
	<div class="center-content">
		<h1 class="logo">JBlog</h1>
		<ul class="menu">
		<c:choose>
		<c:when test="${empty authUser }">
			<li><a href="${pageContext.request.contextPath }/user/login">로그인</a></li>
			<li><a href="${pageContext.request.contextPath }/user/join">회원가입</a></li>
		</c:when>
		<c:otherwise>
			<li><a href="${pageContext.request.contextPath }/user/logout">로그아웃</a></li>
			<li><a href="${pageContext.request.contextPath }/blog/main">내블로그</a></li>
		</c:otherwise>			
		</c:choose>
		</ul>
		<form:form class="join-form" 
					modelAttribute="userVo"
					id="join-form"
					name="joinForm"
					method="post" action='${pageContext.request.contextPath }/user/join'>
			<label class="block-label" for="name">이름</label>
			<form:input id="name" name="name" type="text" path="name" value="" />
			<p style="text-align:left; padding:0; color: red">
				<spring:hasBindErrors name="userVo">
					<c:if test='${errors.hasFieldErrors("name") }'>
						<spring:message code='${errors.getFieldError("name").codes[0] }'/>
					</c:if>
				</spring:hasBindErrors>
			</p>
			
			<label class="block-label" for="blog-id">아이디</label>
			<form:input id="blog-id" name="id" type="text" path="id" />
			<p style="text-align:left; padding:0; color: red">
				<form:errors path="id" />
			</p>
			
			<input id="btn-checkemail" type="button" value="id 중복체크">
			<img id="img-checkemail" style="display: none;" src="${pageContext.request.contextPath}/assets/images/check.png">
			<label class="block-label" for="password">패스워드</label>
			<form:password id="password" name="password" path="password" />
			<p style="text-align:left; padding:0; color: red">
				<form:errors path="password" />
			</p>

			<fieldset>
				<legend>약관동의</legend>
				<input id="agree-prov" type="checkbox" name="agreeProv" value="y">
				<label class="l-float">서비스 약관에 동의합니다.</label>
			</fieldset>

			<input type="submit" value="가입하기">
		</form:form>
	</div>
</body>
</html>
