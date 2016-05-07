package com.note.manage.pojo;

import java.io.Serializable;
import java.util.Date;

public class Active implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -1519189865156169836L;
	private Long id;
	private String title;
	private String detail;
	private String rowKey;
	private String deadlineStr;
	private Date deadline;
	private Date createTime;
	private Date updateTime;
	public String getDeadlineStr() {
		return deadlineStr;
	}
	public void setDeadlineStr(String deadlineStr) {
		this.deadlineStr = deadlineStr;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDetail() {
		return detail;
	}
	public void setDetail(String detail) {
		this.detail = detail;
	}
	public String getRowKey() {
		return rowKey;
	}
	public void setRowKey(String rowKey) {
		this.rowKey = rowKey;
	}
	public Date getDeadline() {
		return deadline;
	}
	public void setDeadline(Date deadline) {
		this.deadline = deadline;
	}
	@Override
	public String toString() {
		return "Active [id=" + id + ", title=" + title + ", detail=" + detail
				+ ", rowKey=" + rowKey + ", deadline=" + deadline
				+ ", createTime=" + createTime + ", updateTime=" + updateTime
				+ "]";
	}
	

}
