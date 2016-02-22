package com.note.manage.service;

import com.note.manage.pojo.User;

public interface TokenService {
	User checkUser(String token);

	boolean saveUser(User user);

}
