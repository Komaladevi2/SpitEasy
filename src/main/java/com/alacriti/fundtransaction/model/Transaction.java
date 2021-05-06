package com.alacriti.fundtransaction.model;

public class Transaction {
	
	private int transactionId;
	private String payee;
	private String beneficiary;
	private float amount;
	private String status;
	private String createdOn;
	
	public Transaction() {
	}

	public int getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(int transactionId) {
		this.transactionId = transactionId;
	}

	public String getPayee() {
		return payee;
	}

	public void setPayee(String payee) {
		this.payee = payee;
	}

	public String getBeneficiary() {
		return beneficiary;
	}

	public void setBeneficiary(String beneficiary) {
		this.beneficiary = beneficiary;
	}

	

	public String getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(String createdOn) {
		this.createdOn = createdOn;
	}

	public float getAmount() {
		return amount;
	}

	public void setAmount(float amount) {
		this.amount = amount;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Transaction [transactionId=" + transactionId + ", payee=" + payee + ", beneficiary=" + beneficiary
				+ ", amount=" + amount + ", status=" + status + ", createdOn=" + createdOn + "]";
	}
	
	
	
}
