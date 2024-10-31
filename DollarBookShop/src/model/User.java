package model;

import java.sql.Date;

public class User {
	
	private String userID;
	private String email;
	private String username;
	private String password;
	private Date date;
	private String role;
	
	public User(String userID, String email, String username, String password, Date date, String role) {
		super();
		this.userID = userID;
		this.email = email;
		this.username = username;
		this.password = password;
		this.date = date;
		this.role = role;
	}


	public String getUserID() {
		return userID;
	}

	public void setUserID(String userID) {
		this.userID = userID;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}


	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
}
