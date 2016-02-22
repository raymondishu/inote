package com.note.manage.service.impl;

import java.io.IOException;

import org.apache.log4j.Logger;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.note.manage.pojo.User;
import com.note.manage.service.TokenService;
import com.note.manage.utils.RedisTools;

public class TokenServiceImpl implements TokenService {
	ObjectMapper mapper = new ObjectMapper();  
	private static Logger logger = Logger.getLogger(TokenServiceImpl.class);  

	@Override
	public User checkUser(String token) {
		String string = RedisTools.get(token);
		try {
			return mapper.readValue(string, User.class);
		} catch (IOException e) {
			logger.error("reader user error", e);
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean saveUser(User user) {
		// TODO Auto-generated method stub
		return false;
	}

}
