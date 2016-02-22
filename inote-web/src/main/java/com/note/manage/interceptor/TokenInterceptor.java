package com.note.manage.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.note.manage.pojo.User;
import com.note.manage.service.TokenService;
import com.note.manage.utils.CookieUtils;

public class TokenInterceptor implements HandlerInterceptor{

	@Autowired
	private TokenService tokenService;
	
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		String cookieValue = CookieUtils.getCookieValue(request, "notetoken");
		if(StringUtils.isBlank(cookieValue)){
			response.sendRedirect("/index.jsp");
		}
		User checkUser = tokenService.checkUser(cookieValue);
		System.out.println(checkUser);
		return true;
	}

	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
		
	}

	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

}
