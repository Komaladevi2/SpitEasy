package com.alacriti.fundtransaction.model;

import java.util.Date;
import java.util.List;

public class Group {
	private int groupId;
	private String groupName;
	private String createdBy;
	public List<String> groupMembers;
	private Date createOn;

	public Group() {
	}
	
	
	public int getGroupId() {
		return groupId;
	}


	public void setGroupId(int groupId) {
		this.groupId = groupId;
	}


	public String getGroupName() {
		return groupName;
	}


	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}


	public String getCreatedBy() {
		return createdBy;
	}


	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}


	public Date getCreateOn() {
		return createOn;
	}


	public void setCreateOn(Date createOn) {
		this.createOn = createOn;
	}

	

	public List<String> getGroupMembers() {
		return groupMembers;
	}


	public void setGroupMembers(List<String> groupMembers) {
		this.groupMembers = groupMembers;
	}


	@Override
	public String toString() {
		return "Group [groupId=" + groupId + ", groupName=" + groupName + ", createdBy=" + createdBy + ", groupMembers="
				+ groupMembers + ", createOn=" + createOn + "]";
	}


	
	
}
