package com.note.manage.service.impl;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.note.manage.mapper.ActiveMapper;
import com.note.manage.pojo.Active;
import com.note.manage.service.ActiveService;
import com.note.manage.service.NoteService;
import com.note.manage.utils.constants.Constants;
@Service
public class ActiveServiceImpl implements ActiveService {

	@Autowired
	private ActiveMapper activeMapper;
	@Autowired
	private NoteService noteService;
	@Override
	public List<Active> getAllActives() {
		return activeMapper.getAllActives();
	}

	@Override
	public boolean addActive(Active active) throws IOException {
		String activeName="active";
		String createTime=String.valueOf(System.currentTimeMillis());
		String rowKey = activeName.trim() + Constants.ROWKEY_SEPARATOR+ createTime.trim();
		noteService.addNoteBook(active.getTitle(), activeName, createTime, 0);
		//noteService.addNoteBookToHbase(active.getTitle(), activeName, createTime, 0);
		active.setRowKey(rowKey);
		int addActive = activeMapper.addActive(active);
		if(addActive>0){
			return true;
		}
		return false;
	}

}
