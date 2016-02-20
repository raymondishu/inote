package com.note.manage.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.note.manage.pojo.Notebook;
import com.note.manage.pojo.Test;
import com.note.manage.service.TestService;

@Controller
@RequestMapping("mytest")
public class TestController {
	@Autowired
	private  TestService testService;
	
	@RequestMapping("test")
	@ResponseBody
	public List<Test> test(){
		System.out.println(testService.insertUser());
		//System.out.println(testService.getUser());
		//testService.updateUser();
		return testService.getTests();
	}
	
	@RequestMapping("test1")
	@ResponseBody
	public List<Notebook> test1(){
		//System.out.println(testService.insertUser());
		testService.insertNotebook();
		testService.updateNotebook();
		//System.out.println(testService.getUser());
		//testService.updateUser();
		return testService.getNoteBooks();
	}

}
