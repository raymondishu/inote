package com.note.manage.service;

import java.io.IOException;
import java.util.List;

import com.note.manage.pojo.Active;

public interface ActiveService {
	List<Active> getAllActives();

	boolean addActive(Active active) throws IOException;

}
