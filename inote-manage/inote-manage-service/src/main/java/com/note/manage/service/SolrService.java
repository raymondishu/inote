package com.note.manage.service;

import java.io.IOException;
import java.util.List;

import org.apache.solr.client.solrj.SolrServerException;

import com.note.manage.dpojo.Note;

public interface SolrService {

	public abstract void createIndexToSolrCloud(String noteRowKey,
			String noteName, String content) throws SolrServerException,
			IOException;

	public abstract List<Note> searchIndexFromSolrCloud(String keyWords)
			throws Exception;

	public abstract void deleteIndexFromSolrCloud(String noteRowKey)
			throws SolrServerException, IOException;

}