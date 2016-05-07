package com.note.manage.mapper;

import java.util.List;

import com.note.manage.pojo.Active;

public interface ActiveMapper {
	List<Active> getAllActives();
	
	int addActive(Active active);

}
