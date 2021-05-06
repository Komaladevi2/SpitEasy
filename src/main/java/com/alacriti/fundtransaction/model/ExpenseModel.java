package com.alacriti.fundtransaction.model;

public class ExpenseModel {
	
	private String paidBy;
	private String paidTo;
	private float splittedAmount;
	
	public ExpenseModel() {
	}

	public String getPaidBy() {
		return paidBy;
	}

	public void setPaidBy(String paidBy) {
		this.paidBy = paidBy;
	}

	public String getPaidTo() {
		return paidTo;
	}

	public void setPaidTo(String paidTo) {
		this.paidTo = paidTo;
	}

	public float getSplittedAmount() {
		return splittedAmount;
	}

	public void setSplittedAmount(float splittedAmount) {
		this.splittedAmount = splittedAmount;
	}

	@Override
	public String toString() {
		return "ExpenseModel [paidBy=" + paidBy + ", paidTo=" + paidTo + ", splittedAmount=" + splittedAmount + "]";
	}
}
