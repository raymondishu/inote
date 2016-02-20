package com.note.manage.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.note.manage.mapper.NotebookMapper;
import com.note.manage.mapper.TestMapper;
import com.note.manage.mapper.UserMapper;
import com.note.manage.pojo.Notebook;
import com.note.manage.pojo.Test;
import com.note.manage.pojo.User;


@Service
public class TestService {
	@Autowired
	private TestMapper testMapper;
	@Autowired
	private UserMapper userMapper;
	@Autowired
	private NotebookMapper notebookMapper;
	
	
	public List<Test> getTests(){
		return testMapper.queryMytest(1);
	}
	
	public long insertUser(){
		User user=new User();
		user.setPassword("123");
		user.setUserName("111");
		user.setPhone("1234");
		user.setEml("1122");
		user.setVIP(false);
		userMapper.addUser(user);
		System.out.println(user.toString());
		return 1l;
	}
	
	public User getUser(){
		User user=new User();
		user.setId(1l);
		return userMapper.getUser(user);
	}
	
	public void updateUser(){
		User user=new User();
		user.setId(1l);
		user.setPassword("2222");
		user.setPhone("333");
		user.setVIP(true);
		user.setEml("444");
		userMapper.updateUser(user);
	}
	
	public int insertNotebook(){
		Notebook notebook=new Notebook();
		notebook.setId("id123");
		notebook.setNoteBookName("book123");
		notebook.setStatus(0);
		notebook.setBelongto(1l);
		return notebookMapper.addNotebook(notebook);
	}
	
	public void updateNotebook(){
		Notebook notebook=new Notebook();
		notebook.setId("id123");
		notebook.setNoteBookName("book1234");
		notebook.setStatus(1);	
		notebookMapper.updateNotebook(notebook);
	}
	
	public List<Notebook> getNoteBooks(){
		return notebookMapper.getNotebooksByUserId(1l);
	}

}
