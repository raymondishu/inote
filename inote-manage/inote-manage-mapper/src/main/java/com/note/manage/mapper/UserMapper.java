package com.note.manage.mapper;

import com.note.manage.pojo.User;

public interface UserMapper {
	
	public int addUser(User user);
	
	public User getUser(User user);
	
	public int updateUser(User user);

}
