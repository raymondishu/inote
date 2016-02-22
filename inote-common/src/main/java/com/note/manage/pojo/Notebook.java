package com.note.manage.pojo;

import java.io.Serializable;
import java.util.Date;

public class Notebook implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -8126702729233009130L;
	private String id;
	private String noteBookName;
	private Integer status;
	private Long belongto;
	private Date createTime;
	private Date updateTime;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNoteBookName() {
		return noteBookName;
	}

	public void setNoteBookName(String noteBookName) {
		this.noteBookName = noteBookName;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Long getBelongto() {
		return belongto;
	}

	public void setBelongto(Long belongto) {
		this.belongto = belongto;
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

	@Override
	public String toString() {
		return "Notebook [id=" + id + ", noteBookName=" + noteBookName
				+ ", status=" + status + ", belongto=" + belongto
				+ ", createTime=" + createTime + ", updateTime=" + updateTime
				+ "]";
	}

}
