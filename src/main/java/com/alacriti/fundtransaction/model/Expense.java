package com.alacriti.fundtransaction.model;

import java.util.List;

public class Expense {
	private int expenseId;
	private String userName;
	private String expenseDescription;
	private float expenseAmount;
	private float splittedAmount;
	private String status;
	private String paidBy;
	private List<String> paidTo;
	private String createdOn;

	public Expense() {
	}

	public int getExpenseId() {
		return expenseId;
	}

	public void setExpenseId(int expenseId) {
		this.expenseId = expenseId;
	}

	public String getExpenseDescription() {
		return expenseDescription;
	}

	public void setExpenseDescription(String expenseDescription) {
		this.expenseDescription = expenseDescription;
	}

	public float getExpenseAmount() {
		return expenseAmount;
	}

	public void setExpenseAmount(float expenseAmount) {
		this.expenseAmount = expenseAmount;
	}

	public float getSplittedAmount() {
		return splittedAmount;
	}

	public void setSplittedAmount(float splittedAmount) {
		this.splittedAmount = splittedAmount;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getPaidBy() {
		return paidBy;
	}

	public void setPaidBy(String paidBy) {
		this.paidBy = paidBy;
	}

	public List<String> getPaidTo() {
		return paidTo;
	}

	public void setPaidTo(List<String> paidTo) {
		this.paidTo = paidTo;
	}

	public String getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(String createdOn) {
		this.createdOn = createdOn;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	@Override
	public String toString() {
		return "Expense [expenseId=" + expenseId + ", userName=" + userName + ", expenseDescription="
				+ expenseDescription + ", expenseAmount=" + expenseAmount + ", splittedAmount=" + splittedAmount
				+ ", status=" + status + ", paidBy=" + paidBy + ", paidTo=" + paidTo + ", createdOn=" + createdOn + "]";
	}

}
