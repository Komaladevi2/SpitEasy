package com.alacriti.fundtransaction.dao;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.alacriti.fundtransaction.model.CardDetailsRowMapper;
import com.alacriti.fundtransaction.model.FundCardDetails;
import com.alacriti.fundtransaction.model.Transaction;

@SuppressWarnings({"deprecation", "rawtypes", "unchecked"})
@Repository
public class TransactionDAOImpl implements TransactionDAO {

	private static final Logger logger = LoggerFactory.getLogger(TransactionDAOImpl.class);

	@Autowired
	JdbcTemplate jdbcTemplate;

	@Override
	public String addCard(FundCardDetails card) {
		logger.info("method ---> addCard method in use");
		try {
			jdbcTemplate.update(SqlQueries.ADD_CARD, card.getUserId(), card.getCardNo(), card.getExpiry(),
					card.getAccountHoldersName());
			return "Card Added Successfully";
		} catch (Exception e) {
			throw e;
		}
	}

	
	@Override
	public FundCardDetails getCards(int userId) {
		logger.info("method ---> getCards method in use");
		try {
			FundCardDetails card = jdbcTemplate.queryForObject(SqlQueries.GET_CARDS, new Object[] { userId },
					new CardDetailsRowMapper());
			return card;
		} catch (Exception e) {
			throw e;
		}

	}

	@Override
	public void debitMoney(String paidTo, float amount) {
		logger.info("method ---> debitMoney in use");
		int userId = TransactionDAOImpl.this.getUserIdByUsername(paidTo);
		float initialBal = TransactionDAOImpl.this.getInitialBalance(userId);
		try {
			jdbcTemplate.update(SqlQueries.UPDATE_BALANCE, (initialBal-amount), userId);
			logger.info("Money debited from :" + paidTo);
		} catch (Exception e) {
			throw e;
		}

	}

	@Override
	public void creditMoney(String paidBy, float amount) {
		logger.info("method --->creditMoney in use");
		int userId = TransactionDAOImpl.this.getUserIdByUsername(paidBy);
		System.out.println(userId);
		float initialBal = TransactionDAOImpl.this.getInitialBalance(userId);
		try {
			jdbcTemplate.update(SqlQueries.UPDATE_BALANCE, initialBal + amount, userId);
			logger.info("Money credited to :" + paidBy);
		} catch (Exception e) {
			throw e;
		}

	}

	@Override
	public void updateExpense(String paidBy, String paidTo) {
		logger.info("method ---> updateExpense method in use");

		try {
			jdbcTemplate.update(SqlQueries.UPDATE_EXPENSE, "paid", paidBy, paidTo);
			logger.info("Expense Updated ");
		} catch (Exception e) {
			logger.info("Expense Updation Failed ");
		}
	}

	public int getUserIdByUsername(String userName) {
		logger.info("method ---> getUserIdByUsername method in use");
		try {
			return jdbcTemplate.queryForObject(SqlQueries.GET_ID_BY_NAME, new Object[] { userName }, int.class);
		} catch (Exception e) {
			logger.info("User Id not Found for user :" + userName);
			throw e;
		}
	}

	public float getInitialBalance(int userId) {
		logger.info("method ---> getInitialBalance method in use");
		try {
			return jdbcTemplate.queryForObject(SqlQueries.GET_BALANCE, new Object[] { userId }, float.class);
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public void createTransaction(String paidBy, String paidTo, float splittedAmount) {
		logger.info("method ---> createTransaction method in use");
		try {
			jdbcTemplate.update(SqlQueries.CREATE_TRANSACTION, paidTo, paidBy, splittedAmount, "success");
			logger.info("Transaction Success");
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public List<Transaction> getTransactions(String userName) {
		logger.info("method ---> getTransactions method in use");
		try {
	
		List<Transaction> transactionsList = jdbcTemplate.query(SqlQueries.GET_TRANSACTIONS,
				new Object[] { userName, userName }, new BeanPropertyRowMapper(Transaction.class));
		return transactionsList;
		}
		catch(Exception e) {
			throw e;
		}
	}

}
