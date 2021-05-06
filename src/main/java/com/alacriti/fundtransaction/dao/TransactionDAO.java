package com.alacriti.fundtransaction.dao;


import java.util.List;

import com.alacriti.fundtransaction.model.FundCardDetails;
import com.alacriti.fundtransaction.model.Transaction;

public interface TransactionDAO {

	String addCard(FundCardDetails card);

	FundCardDetails getCards(int userId);
	
	void debitMoney(String  paidTo, float amount);

	void creditMoney(String paidBy, float splittedAmount);

	void updateExpense(String paidBy, String paidTo);

	void createTransaction(String paidBy, String paidTo, float splittedAmount);

	List<Transaction> getTransactions(String userName);
	
}
