package com.note.manage.service.impl;

import java.io.IOException;
import java.util.UUID;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.note.manage.mapper.UserMapper;
import com.note.manage.pojo.User;
import com.note.manage.service.TokenService;
import com.note.manage.utils.RedisTools;

public class TokenServiceImpl implements TokenService {
	private static final ObjectMapper MAPPER = new ObjectMapper();
	
	ObjectMapper mapper = new ObjectMapper();
	private static Logger logger = Logger.getLogger(TokenServiceImpl.class);

	@Autowired
	private UserMapper userMapper;

	@Override
	public User checkUser(String token) {
		String string = RedisTools.get(token);
		try {
			if (StringUtils.isBlank(string)) {
				return null;
			} else {
				RedisTools.expire(token, 30*60);
				return mapper.readValue(string, User.class);
			}
		} catch (IOException e) {
			logger.error("reader user error", e);
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean saveUser(User user) {
		int addUser = userMapper.addUser(user);
		if (addUser > 0) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public User LoginUser(String userName, String password) {
		User loginUser = userMapper.loginUser(userName, password);
		if(loginUser!=null){
			try {
				String randomUUID = UUID.randomUUID().toString();
				RedisTools.set(randomUUID, MAPPER.writeValueAsString(loginUser),30*60);
				loginUser.setUserName(randomUUID);
				return loginUser;
			} catch (JsonProcessingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				logger.error("login fail",e);
				return null;
			}
		}else{
			logger.error("login fail");
			return null;
		}
	}

}
