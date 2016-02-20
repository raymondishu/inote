package com.note.manage.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.note.manage.pojo.Notebook;

public interface NotebookMapper {
	public int addNotebook(Notebook notebook);

	public int updateNotebook(Notebook notebook);

	public List<Notebook> getNotebooksByUserId(@Param("belongto")Long userId);

}
