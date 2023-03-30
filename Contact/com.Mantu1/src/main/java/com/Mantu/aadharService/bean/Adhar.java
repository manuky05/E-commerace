package com.Mantu.aadharService.bean;

public class Adhar {
	
	private Integer aadharnumber;
     private int id;
	
	private String firstName;
	
	private String lastName;
	
	private String email;
	
	private String password;
	private String about;
	private String roles;
	public Integer getAadharnumber() {
		return aadharnumber;
	}
	public void setAadharnumber(Integer aadharnumber) {
		this.aadharnumber = aadharnumber;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
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
	public String getAbout() {
		return about;
	}
	public void setAbout(String about) {
		this.about = about;
	}
	public String getRoles() {
		return roles;
	}
	public void setRoles(String roles) {
		this.roles = roles;
	}
	@Override
	public String toString() {
		return "Adhar [aadharnumber=" + aadharnumber + ", id=" + id + ", firstName=" + firstName + ", lastName="
				+ lastName + ", email=" + email + ", password=" + password + ", about=" + about + ", roles=" + roles
				+ "]";
	}
	public Adhar(Integer aadharnumber, int id, String firstName, String lastName, String email, String password,
			String about, String roles) {
		super();
		this.aadharnumber = aadharnumber;
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.about = about;
		this.roles = roles;
	}
	public Adhar() {
		super();
		// TODO Auto-generated constructor stub
	}
	

}
