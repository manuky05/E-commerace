package com.mantuIT.springDataJpaFirstApp.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class User {
	@Id
	private int userid;
	private String userNname;
	private String useraddress;
	private int userage;
	public int getUserid() {
		return userid;
	}
	public void setUserid(int userid) {
		this.userid = userid;
	}
	public String getUserNname() {
		return userNname;
	}
	public void setUserNname(String userNname) {
		this.userNname = userNname;
	}
	public String getUseraddress() {
		return useraddress;
	}
	public void setUseraddress(String useraddress) {
		this.useraddress = useraddress;
	}
	public int getUserage() {
		return userage;
	}
	public void setUserage(int userage) {
		this.userage = userage;
	}
	@Override
	public String toString() {
		return "User [userid=" + userid + ", userNname=" + userNname + ", useraddress=" + useraddress + ", userage="
				+ userage + "]";
	}
	
	
	

}
