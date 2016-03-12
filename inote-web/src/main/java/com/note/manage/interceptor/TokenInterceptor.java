package com.note.manage.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.note.manage.pojo.User;
import com.note.manage.service.TokenService;
import com.note.manage.threadlocal.UserThreadLocal;
import com.note.manage.utils.CookieUtils;

public class TokenInterceptor implements HandlerInterceptor{

	private static Logger logger=LoggerFactory.getLogger(TokenInterceptor.class);
	@Value("#{ipConfig['userToken']}")
	private String userTocken;
	
	@Autowired
	private TokenService tokenService;
	
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		String cookieValue = CookieUtils.getCookieValue(request, userTocken);
		
		if(StringUtils.isBlank(cookieValue)){
			UserThreadLocal.clear();
			response.sendRedirect("/index.jsp");
			logger.info("未登录跳转首页");
			return false;
		}
		User checkUser = tokenService.checkUser(cookieValue);
		if(null==checkUser){
			UserThreadLocal.clear();
			response.sendRedirect("/index.jsp");
			CookieUtils.deleteCookie(request, response, userTocken);
			logger.info("登录过期跳转首页");
			return false;
		}
		UserThreadLocal.set(checkUser);
		logger.info(checkUser.getUserName()+" use "+request.getServletPath());		
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
