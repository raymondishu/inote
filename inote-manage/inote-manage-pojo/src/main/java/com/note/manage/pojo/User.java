package com.note.manage.pojo;

import java.util.Date;

public class User {
	private Long id;
	private String userName;
	private String password;
	private String phone;
	private String eml;
	private Boolean isVIP;
	private Date creatTime;
	private Date updateTime;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEml() {
		return eml;
	}

	public void setEml(String eml) {
		this.eml = eml;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Boolean isVIP() {
		return isVIP;
	}

	public void setVIP(Boolean isVIP) {
		this.isVIP = isVIP;
	}

	public Date getCreatTime() {
		return creatTime;
	}

	public void setCreatTime(Date creatTime) {
		this.creatTime = creatTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", userName=" + userName + ", password="
				+ password + ", phone=" + phone + ", eml=" + eml + ", isVIP="
				+ isVIP + ", creatTime=" + creatTime + ", updateTime="
				+ updateTime + "]";
	}

}
