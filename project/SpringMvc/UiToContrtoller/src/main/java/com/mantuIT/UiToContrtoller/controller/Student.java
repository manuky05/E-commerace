package com.mantuIT.UiToContrtoller.controller;

public class Student {
	private String fname;
	private String lname;
	private String collegename;
    private String email;
    private String password;
	public String getFname() {
		return fname;
	}
	public void setFname(String fname) {
		this.fname = fname;
	}
	public String getLname() {
		return lname;
	}
	public void setLname(String lname) {
		this.lname = lname;
	}
	public String getCollegename() {
		return collegename;
	}
	public void setCollegename(String collegename) {
		this.collegename = collegename;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@Override
	public String toString() {
		return "Student [fname=" + fname + ", lname=" + lname + ", collegename=" + collegename + ", email=" + email
				+ ", password=" + password + "]";
	}
    
	
	

}
