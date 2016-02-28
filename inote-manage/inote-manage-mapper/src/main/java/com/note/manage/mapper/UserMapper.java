package com.note.manage.mapper;

import org.apache.ibatis.annotations.Param;

import com.note.manage.pojo.User;

public interface UserMapper {
	
	public int addUser(User user);
	
	public User getUser(User user);
	
	public int updateUser(User user);
	
	public User loginUser(@Param("userName")String userName,@Param("password")String password);

}
