package com.alacriti.fundtransaction.dao;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.alacriti.fundtransaction.model.Expense;
import com.alacriti.fundtransaction.model.ExpenseModel;
import com.alacriti.fundtransaction.model.ExpenseRowMapper;
import com.alacriti.fundtransaction.model.Friend;
import com.alacriti.fundtransaction.model.User;
import com.alacriti.fundtransaction.model.UserModelRowMapper;
import com.alacriti.fundtransaction.service.SessionManagement;

@SuppressWarnings({ "deprecation", "rawtypes", "unchecked" })
@Repository
public class UserDAOImpl implements UserDAO {

	private static final Logger logger = LoggerFactory.getLogger(UserDAOImpl.class);

	@Autowired
	JdbcTemplate jdbcTemplate;

	@Autowired
	SessionManagement sessionManagement;

	public String saveUser(User user) throws SQLException {

		logger.info("method ---> saveUser DAOImpl in use");
		try {
			jdbcTemplate.update(SqlQueries.SAVE_USER, user.getUserName(), user.getUserEmailId(), user.getUserPassword(),
					user.getUserPhoneNumber());
			return "Registered Successfully";
		} catch (Exception e) {
			throw e;
		}
	}

	public String updateUser(User user) {

		try {
			jdbcTemplate.update(SqlQueries.UPDATE_USER, user.getUserName(), user.getUserEmailId(),
					user.getUserPassword(), user.getUserPhoneNumber(), user.getUserId());
			return "Record updated successfully";
		} catch (Exception e) {
			throw e;
		}
	}

	public User getUserByName(String username) {
		try {
			User user = jdbcTemplate.queryForObject(SqlQueries.GET_BY_NAME, new Object[] { username },
					new UserModelRowMapper());

			return user;
		} catch (Exception e) {
			throw e;
		}
	}

	public User validateUser(String userEmailId, String userPassword, HttpServletResponse response) {
		try {
			User user = jdbcTemplate.queryForObject(SqlQueries.VALIDATE_USER,
					new Object[] { userEmailId, userPassword }, new UserModelRowMapper());

			sessionManagement.createSession(user.getUserId(), response);

			return user;
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public String addFriend(int userid, String friendEmailId) {
		logger.info("method ---> addFriend UserDAOImpl method in use");
		User user = UserDAOImpl.this.getUserById(userid);
		String userName = user.getUserName();

		User user2 = UserDAOImpl.this.getUserByEmailId(friendEmailId);
		String friendName = user2.getUserName();

		if (friendName != null) {
			try {

				jdbcTemplate.queryForObject(SqlQueries.VALIDATE_FRIEND,
						new Object[] { userName, friendName, friendName, userName },
						new BeanPropertyRowMapper(Friend.class));
				return "Already Friends";
			} catch (Exception e) {
				jdbcTemplate.update(SqlQueries.ADD_FRIEND, userName, friendName);
				return "Friend Added Successfully";
			}
		}
		return "Friend Doesn't Existed";
	}

	@Override
	public List<Friend> getFriends(int userid) {
		User user = UserDAOImpl.this.getUserById(userid);
		try {
			List<Friend> friendsList = jdbcTemplate.query(SqlQueries.GET_FRIENDS,
					new Object[] { user.getUserName(), user.getUserName() }, new BeanPropertyRowMapper(Friend.class));
			return friendsList;
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public String createExpense(Expense expense) {

		List<String> friendsList = expense.getPaidTo();
		float splittedAmount = (expense.getExpenseAmount()) / ((friendsList.size() + 1));
		try {
			UserDAOImpl.this.saveExpense(expense, splittedAmount);
			int expenseId = jdbcTemplate.queryForObject(SqlQueries.GET_EXPENSE_ID, int.class);

			for (int i = 0; i < friendsList.size(); i++) {
				jdbcTemplate.update(SqlQueries.ADD_EXPENSE_MEMBERS, expense.getPaidBy(), friendsList.get(i),
						splittedAmount, expense.getExpenseDescription(), expense.getExpenseAmount(), expenseId);

			}
			return "Expense Added";
		} catch (Exception e) {
			throw e;
		}

	}

	private void saveExpense(Expense expense, float splittedAmount) {
		User user = UserDAOImpl.this.getUserByName(expense.getPaidBy());

		jdbcTemplate.update(SqlQueries.ADD_EXPENSE, expense.getExpenseDescription(), expense.getExpenseAmount(),
				user.getUserName(), splittedAmount);

	}

	@Override
	public List<ExpenseModel> getAllExpenses(String userName) {

		List<ExpenseModel> expenseList = jdbcTemplate.query(SqlQueries.GET_ALL_EXPENSES,
				new Object[] { userName, userName, "unpaid" }, new ExpenseRowMapper());
		return expenseList;
	}

	@Override
	public List<Expense> getUserExpenses(String userName) {
		try {

			List<Expense> userExpensesList = jdbcTemplate.query(SqlQueries.GET_USER_EXPENSES,
					new Object[] { userName, userName }, new BeanPropertyRowMapper<>(Expense.class));
			return userExpensesList;
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public User getUserByEmailId(String email) {

		try {
			User user=jdbcTemplate.queryForObject(SqlQueries.GET_BY_EMAIL, new Object[] { email }, new UserModelRowMapper());
			return user;
		} catch (Exception e) {
			throw e;
		}
	}

	public User getUserById(int id) {
		try {
			User user = jdbcTemplate.queryForObject(SqlQueries.GET_BY_ID, new Object[] { id },
					new UserModelRowMapper());
			return user;
		} catch (Exception e) {
			throw e;
		}
	}

	public int checkUserName(String username) {
		try {
			jdbcTemplate.queryForObject(SqlQueries.CHECK_NAME, new Object[] { username }, new UserModelRowMapper());
			return 1;
		} catch (Exception e) {
			return 0;
		}
	}

}
