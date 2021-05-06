package com.alacriti.fundtransaction.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alacriti.fundtransaction.dao.SessionDAO;
import com.alacriti.fundtransaction.dao.TransactionDAO;
import com.alacriti.fundtransaction.dao.UserDAO;
import com.alacriti.fundtransaction.model.ExpenseModel;
import com.alacriti.fundtransaction.model.FundCardDetails;
import com.alacriti.fundtransaction.model.User;
import com.alacriti.fundtransaction.response.ApiResponse;
import com.alacriti.fundtransaction.response.ResponseConstants;

@Service
public class TransactionService {

	@Autowired
	SessionDAO sessionDAO;

	@Autowired
	UserDAO userDAO;

	private static final Logger logger = LoggerFactory.getLogger(TransactionService.class);

	@Autowired
	TransactionDAO transactionDAO;

	public ApiResponse addCard(FundCardDetails card) {
		logger.info("method  --->  addCard method in use");
		try {
			return new ApiResponse(ResponseConstants.SUCCESS_CODE, ResponseConstants.SUCCESS,
					transactionDAO.addCard(card));
		} catch (Exception e) {
			return new ApiResponse(ResponseConstants.ERROR_CODE, ResponseConstants.ERROR);
		}
	}

	public ApiResponse getCards(String sessionId) {
		logger.info("method ---> getCards method in use");
		int userId = sessionDAO.getuserIdBySessionId(sessionId);
		try {
			return new ApiResponse(ResponseConstants.SUCCESS_CODE, ResponseConstants.SUCCESS,
					transactionDAO.getCards(userId));
		} catch (EmptyResultDataAccessException e) {
			logger.error("Exception Occured----->No Fund Card Details found" + e.getMessage());
			return new ApiResponse(ResponseConstants.NOT_FOUND_CODE, ResponseConstants.SUCCESS);
		} catch (Exception e) {
			return new ApiResponse(ResponseConstants.ERROR_CODE, ResponseConstants.ERROR);
		}
	}

	@Transactional
	public ApiResponse proceedPayment(ExpenseModel expense) {
		logger.info("method ---> proceedPayment service method in use");

		try {
			transactionDAO.debitMoney(expense.getPaidTo(), expense.getSplittedAmount());

			transactionDAO.creditMoney(expense.getPaidBy(), expense.getSplittedAmount());

			transactionDAO.updateExpense(expense.getPaidBy(), expense.getPaidTo());

			transactionDAO.createTransaction(expense.getPaidBy(), expense.getPaidTo(), expense.getSplittedAmount());
			return new ApiResponse(ResponseConstants.SUCCESS_CODE, ResponseConstants.SUCCESS);
		} catch (Exception e) {
			return new ApiResponse(ResponseConstants.ERROR_CODE, ResponseConstants.ERROR);
		}
	}

	public ApiResponse getTransactions(String sessionId) {
		logger.info("method ---> getTransactions service method in use");
		int userId = sessionDAO.getuserIdBySessionId(sessionId);
		User user = userDAO.getUserById(userId);
		try {
			return new ApiResponse(ResponseConstants.SUCCESS_CODE, ResponseConstants.SUCCESS,
					transactionDAO.getTransactions(user.getUserName()));
		} catch (EmptyResultDataAccessException e) {
			logger.error("Exception Occured----->No Transactions found" + e.getMessage());
			return new ApiResponse(ResponseConstants.NOT_FOUND_CODE, ResponseConstants.SUCCESS);
		} catch (Exception e) {
			logger.error("Unable to fetch Transactions: " + e);
			throw e;
		}

	}
}
