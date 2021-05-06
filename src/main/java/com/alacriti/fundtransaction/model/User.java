package com.alacriti.fundtransaction.model;

public class User {
	private int userId;
	private String userName;
	private String userEmailId;
	private String userPassword;
	private Long userPhoneNumber;
	public User() {
		
	}
	public User(String userName,  String userEmailId, String userPassword, Long userPhoneNumber) {
		
		this.userName=userName;
		this.userEmailId=userEmailId;
		this.userPassword=userPassword;
		this.userPhoneNumber=userPhoneNumber;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserEmailId() {
		return userEmailId;
	}
	public void setUserEmailId(String userEmailId) {
		this.userEmailId = userEmailId;
	}
	public String getUserPassword() {
		return userPassword;
	}
	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}
	public Long getUserPhoneNumber() {
		return userPhoneNumber;
	}
	public void setUserPhoneNumber(Long userPhoneNumber) {
		this.userPhoneNumber = userPhoneNumber;
	}
	@Override
	public String toString() {
		return "User [userId=" + userId + ", userName=" + userName + ", userEmailId=" + userEmailId + ", userPassword="
				+ userPassword + ", userPhoneNumber=" + userPhoneNumber + "]";
	}
	
	
}
