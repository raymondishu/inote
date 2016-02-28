/**
 * 
 */
package com.note.web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.note.manage.pojo.User;
import com.note.manage.service.TokenService;
import com.note.manage.threadlocal.UserThreadLocal;
import com.note.manage.utils.CookieUtils;
import com.note.manage.utils.constants.Constants;

@Controller
@RequestMapping("/login")
public class LoginController {
	private static Logger logger = LoggerFactory.getLogger(LoginController.class);
	//@Resource
	//private LoginService loginService;
	@Value("#{ipConfig['userToken']}")
	private String userTocken;

	@Autowired
	private TokenService tokenService;
	
	private static final ObjectMapper MAPPER = new ObjectMapper();
	
	@RequestMapping("/showloginpage")
	public String login(HttpServletRequest request)throws Exception{
		
		return "login/login";
	}
	@RequestMapping("/showloginpage2")
	public String login2(HttpServletRequest request)throws Exception{
		
		return "login/login2";
	}
	@RequestMapping("/showloginpage3")
	public String login3(HttpServletRequest request)throws Exception{
		
		return "login/login3";
	}
	
	@RequestMapping("/loginnow")
	public String loginin(HttpServletRequest request,HttpServletResponse response,String loginName,String password){
		
		try {
			if (loginName==null||"".equals(loginName)||password==null||"".equals(password)) {
				return "error/404";
			}
			//request.getSession().setAttribute(Constants.USER_INFO, loginName.trim());
			User loginUser = tokenService.LoginUser(loginName, password);
			if(loginUser!=null){
				UserThreadLocal.set(loginUser);
				CookieUtils.setCookie(request, response, userTocken,loginUser.getUserName());
				logger.info(loginName+" login scucess!");
			}else{
				logger.info(loginName+" login fail!");
				UserThreadLocal.clear();
				return "login/login2";
			}
		} catch (Exception e) {
			UserThreadLocal.clear();
			logger.error("登陆失败：loginName:"+loginName+";",e);
			e.printStackTrace();
			return "login/login2";
		}
		
		return "note/inotecenter";
	}

	
}
