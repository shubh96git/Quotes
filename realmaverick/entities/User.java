package com.realmaverick.entities;

public class User {
	
	//fileds of POJO
	private int userId;
	private String fName;
	private String lName;
	private String email;
	private String passwd;
	private String mobile;
	
	//ctor
	public User() {
	}

	public User(int userId, String fName, String lName, String email, String passwd, String mobile) {
		super();
		this.userId = userId;
		this.fName = fName;
		this.lName = lName;
		this.email = email;
		this.passwd = passwd;
		this.mobile = mobile;
	}
	
	//getters and setters
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getfName() {
		return fName;
	}
	public void setfName(String fName) {
		this.fName = fName;
	}
	public String getlName() {
		return lName;
	}
	public void setlName(String lName) {
		this.lName = lName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPasswd() {
		return passwd;
	}
	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	
	//tostring...
	@Override
	public String toString() {
		return "User [userId=" + userId + ", fName=" + fName + ", lName=" + lName + ", email=" + email + ", passwd="
				+ passwd + ", mobile=" + mobile + "]";
	}
	
}
