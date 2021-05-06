package com.alacriti.fundtransaction.model;

public class FundCardDetails {
	private int userId;
	private Long cardNo;
	private String expiry;
	private String accountHoldersName;

	public FundCardDetails() {
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public Long getCardNo() {
		return cardNo;
	}

	public void setCardNo(Long cardNo) {
		this.cardNo = cardNo;
	}

	public String getExpiry() {
		return expiry;
	}

	public void setExpiry(String expiry) {
		this.expiry = expiry;
	}

	public String getAccountHoldersName() {
		return accountHoldersName;
	}

	public void setAccountHoldersName(String accountHoldersName) {
		this.accountHoldersName = accountHoldersName;
	}

	@Override
	public String toString() {
		return "FundCardDetails [userId=" + userId + ", cardNo=" + cardNo + ", expiry=" + expiry
				+ ", accountHoldersName=" + accountHoldersName + "]";
	}

}
