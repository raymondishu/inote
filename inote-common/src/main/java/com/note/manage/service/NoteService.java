package com.note.manage.service;

import java.io.IOException;
import java.util.List;

import org.apache.solr.client.solrj.SolrServerException;

import com.note.manage.dpojo.Note;
import com.note.manage.dpojo.NoteBook;


public interface NoteService {
	public List<NoteBook> getAllNoteBook(String userIdAndName) throws IOException;
	boolean addNoteBook(String noteBookName, String userName, String createTime,
			int status) throws IOException;
	public List<Note> getNoteListByNotebook(String rowkey) throws IOException;
	boolean deleteNoteBook(String noteBookName, String userName, String createTime,
			int i);
	boolean updateNoteBook(String newNoteBookName, String oldNoteBookName,
			String userName, String createTime, int status);
	boolean addNote(String noteRowKey,String noteName,String createTime,String status,String noteBookRowkey);

	boolean deleteNote(String noteRowKey, String string, String string2,
			String oldNoteName, String noteBookRowkey);

	boolean updateNote(String noteRowKey, String noteName, String string,
			String content, String string2, String oldNoteName,
			String noteBookRowkey);
	boolean moveAndDeleteNote(String noteRowKey, String oldNoteBookRowkey,
			String newNoteBookRowkey,String noteName);
	Note getNoteByRowKey(String noteRowkey) throws Exception;
	boolean shareNote(String rowKey,String username,String activeRowKey) throws IOException, SolrServerException;
	//List<Article> search(String key, int page) throws InterruptedException,
	 //ParseException, IOException, InvalidTokenOffsetsException;
	boolean starOtherNote(String noteRowKey, String starBtRowKey);
	//public boolean activeMyNote(String noteRowKey,
	//		String activityBtRowKey);
	boolean addNoteBookToHbase(String title, String activeName,
			String createTime, int i);
	List<Note> searchActiveNotesByKeyWords(String keyWords) throws Exception;
}
