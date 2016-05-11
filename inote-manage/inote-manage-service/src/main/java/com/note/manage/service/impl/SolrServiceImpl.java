package com.note.manage.service.impl;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.CloudSolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.client.solrj.response.UpdateResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.stereotype.Service;

import com.note.manage.dpojo.Note;
import com.note.manage.service.SolrService;
@Service
public class SolrServiceImpl implements SolrService {
	// zookeeper地址
		private static String zkHostString = "192.168.56.201:2181,192.168.56.202:2181,192.168.56.203:2181";
		// collection名称
		private static String defaultCollection = "mycore";
		// 客户端连接超时时间
		private static int zkClientTimeout = 3000;
		// zookeeper连接超时时间
		private static int zkConnectTimeout = 3000;

		// cloudSolrServer实际
		private static CloudSolrServer cloudSolrServer;

		// 测试方法之前构造 CloudSolrServer
		static {
			cloudSolrServer = new CloudSolrServer(zkHostString);
			cloudSolrServer.setDefaultCollection(defaultCollection);
			cloudSolrServer.setZkClientTimeout(zkClientTimeout);
			cloudSolrServer.setZkConnectTimeout(zkConnectTimeout);
			cloudSolrServer.connect();
		}

		/* (non-Javadoc)
		 * @see com.note.manage.service.impl.SolrService#createIndexToSolrCloud(java.lang.String, java.lang.String, java.lang.String)
		 */
		@Override
		public void createIndexToSolrCloud(String noteRowKey, String noteName,String content) throws SolrServerException,
				IOException {
			//init();
			SolrInputDocument document = new SolrInputDocument();
			document.addField("id", noteRowKey);
			document.addField("title", noteName);
			document.addField("content", content);
			cloudSolrServer.add(document);
			cloudSolrServer.commit();
		}

		/* (non-Javadoc)
		 * @see com.note.manage.service.impl.SolrService#searchIndexFromSolrCloud(java.lang.String)
		 */
		@Override
		public List<Note> searchIndexFromSolrCloud(String keyWords) throws Exception {
			//init();
			List<Note> list=new ArrayList<Note>();
			SolrQuery query = new SolrQuery();
			query.setQuery("title:"+keyWords+" OR content:"+keyWords);
			try {
				QueryResponse response = cloudSolrServer.query(query);
				SolrDocumentList docs = response.getResults();

				System.out.println("文档个数：" + docs.getNumFound());
				System.out.println("查询时间：" + response.getQTime());

				for (SolrDocument doc : docs) {
					String title = (String) doc.getFieldValue("title");
					String id = (String) doc.getFieldValue("id");
					String content=(String) doc.getFieldValue("content");
					/*System.out.println("id: " + id);
					System.out.println("title: " + title);
					System.out.println();*/
					Note note=new Note();
					note.setRowKey(id);
					note.setName(title);
					note.setContent(content);
					list.add(note);
				}
				return list;
			} catch (SolrServerException e) {
				e.printStackTrace();
			} catch (Exception e) {
				System.out.println("Unknowned Exception!!!!");
				e.printStackTrace();
			}
			return null;
		}

		/* (non-Javadoc)
		 * @see com.note.manage.service.impl.SolrService#deleteIndexFromSolrCloud(java.lang.String)
		 */
		@Override
		public void deleteIndexFromSolrCloud(String noteRowKey) throws SolrServerException, IOException {
			//init();
			// 根据id删除
			UpdateResponse response = cloudSolrServer.deleteById(noteRowKey);
			// 根据多个id删除
			// cloudSolrServer.deleteById(ids);
			// 自动查询条件删除
			// cloudSolrServer.deleteByQuery("product_keywords:教程");
			// 提交
			cloudSolrServer.commit();
		}
}
