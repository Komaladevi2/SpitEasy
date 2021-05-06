package com.alacriti.fundtransaction.model;

public class Friend {
	private String userName;
	private String friendName;
	private String created_on;
	
	public Friend() {
		// TODO Auto-generated constructor stub
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getFriendName() {
		return friendName;
	}

	public void setFriendName(String friendName) {
		this.friendName = friendName;
	}

	public String getCreated_on() {
		return created_on;
	}

	public void setCreated_on(String created_on) {
		this.created_on = created_on;
	}

	@Override
	public String toString() {
		return "Friend [userName=" + userName + ", friendName=" + friendName + ", created_on=" + created_on + "]";
	}

	

	
}
