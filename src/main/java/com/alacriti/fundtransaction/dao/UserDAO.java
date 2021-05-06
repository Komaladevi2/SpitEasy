package com.alacriti.fundtransaction.dao;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.alacriti.fundtransaction.model.Expense;
import com.alacriti.fundtransaction.model.ExpenseModel;
import com.alacriti.fundtransaction.model.Friend;
import com.alacriti.fundtransaction.model.User;

public interface UserDAO {

	public String saveUser(User user) throws SQLException;

	public User validateUser(String username, String password, HttpServletResponse response);

	public int checkUserName(String username);

	public String updateUser(User user);

	public String addFriend(int userid, String friendEmail) ;

	public List<Friend> getFriends(int userid);

	String createExpense(Expense expense);

	List<ExpenseModel> getAllExpenses(String userName);

	public List<Expense> getUserExpenses(String userName);

	public User getUserById(int userId);

	User getUserByEmailId(String email);

}
