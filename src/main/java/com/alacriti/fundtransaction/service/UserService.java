package com.alacriti.fundtransaction.service;

import java.sql.SQLException;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.alacriti.fundtransaction.dao.SessionDAO;
import com.alacriti.fundtransaction.dao.UserDAO;
import com.alacriti.fundtransaction.model.Expense;
import com.alacriti.fundtransaction.model.User;
import com.alacriti.fundtransaction.response.ApiResponse;
import com.alacriti.fundtransaction.response.ResponseConstants;

@Service
public class UserService {

	private static final Logger logger = LoggerFactory.getLogger(UserService.class);

	@Autowired
	UserDAO userDAO;

	@Autowired
	SessionDAO sessionDAO;

	public ApiResponse saveUser(User user) throws SQLException {
		logger.info("method ---> saveUser method in use");

		if (userDAO.checkUserName(user.getUserName()) == 1) {
			return new ApiResponse(ResponseConstants.ERROR_CODE, "User Name Existed");
		} else {
			try {
				if (userDAO.getUserByEmailId(user.getUserEmailId()) != null)
					;
				return new ApiResponse(ResponseConstants.ERROR_CODE, "Emaild Id Existed");
			} catch (Exception e) {
				return this.handleSaveUser(user);
			}
		}
	}

	public ApiResponse handleSaveUser(User user) throws SQLException {
		try {
			return new ApiResponse(ResponseConstants.SUCCESS_CODE, ResponseConstants.SUCCESS, userDAO.saveUser(user));
		} catch (Exception e) {
			return new ApiResponse(ResponseConstants.ERROR_CODE, ResponseConstants.ERROR);
		}
	}

	public ApiResponse validateUser(String userEmail, String password, HttpServletResponse response) {
		try {
			return new ApiResponse(ResponseConstants.SUCCESS_CODE, ResponseConstants.SUCCESS,
					userDAO.validateUser(userEmail, password, response));
		} catch (EmptyResultDataAccessException e) {
			logger.error("Exception Occured----->User details not found" + e.getMessage());
			return new ApiResponse(ResponseConstants.UNAUTHORIZED_CODE, ResponseConstants.SUCCESS);
		} catch (Exception e) {
			return new ApiResponse(ResponseConstants.ERROR_CODE, ResponseConstants.ERROR);
		}

	}

	public int checkUserName(String username) {
		return userDAO.checkUserName(username);
	}

	public ApiResponse updateUser(User user) {
		try {
			return new ApiResponse(ResponseConstants.SUCCESS_CODE, ResponseConstants.SUCCESS, userDAO.updateUser(user));
		} catch (Exception e) {
			return new ApiResponse(ResponseConstants.ERROR_CODE, ResponseConstants.ERROR);
		}
	}

	public ApiResponse addFriend(String sessionId, String friendid) {
		int userId = sessionDAO.getuserIdBySessionId(sessionId);
		try {
			return new ApiResponse(ResponseConstants.SUCCESS_CODE, ResponseConstants.SUCCESS,
					userDAO.addFriend(userId, friendid));
		} catch (Exception e) {
			return new ApiResponse(ResponseConstants.ERROR_CODE, ResponseConstants.ERROR);
		}

	}

	public ApiResponse getFriends(String sessionId) {
		int userId = sessionDAO.getuserIdBySessionId(sessionId);
		try {
			return new ApiResponse(ResponseConstants.SUCCESS_CODE, ResponseConstants.SUCCESS,
					userDAO.getFriends(userId));
		} catch (EmptyResultDataAccessException e) {
			logger.error("Exception Occured----->No Friends found" + e.getMessage());
			return new ApiResponse(ResponseConstants.NOT_FOUND_CODE, ResponseConstants.SUCCESS);
		} catch (Exception e) {
			return new ApiResponse(ResponseConstants.ERROR_CODE, ResponseConstants.ERROR);
		}

	}

	public ApiResponse getUserFriends(int userid) {
		try {
			return new ApiResponse(ResponseConstants.SUCCESS_CODE, ResponseConstants.SUCCESS,
					userDAO.getFriends(userid));
		} catch (EmptyResultDataAccessException e) {
			logger.error("Exception Occured----->No Friends found" + e.getMessage());
			return new ApiResponse(ResponseConstants.NOT_FOUND_CODE, ResponseConstants.SUCCESS);
		} catch (Exception e) {
			return new ApiResponse(ResponseConstants.ERROR_CODE, ResponseConstants.ERROR);
		}
	}

	public ApiResponse createExpense(Expense expense) {
		try {
			return new ApiResponse(ResponseConstants.SUCCESS_CODE, ResponseConstants.SUCCESS,
					userDAO.createExpense(expense));
		} catch (Exception e) {
			return new ApiResponse(ResponseConstants.ERROR_CODE, ResponseConstants.ERROR);
		}

	}

	public ApiResponse getAllExpenses(String sessionId) {
		int userId = sessionDAO.getuserIdBySessionId(sessionId);
		User user = (User) userDAO.getUserById(userId);
		try {
			return new ApiResponse(ResponseConstants.SUCCESS_CODE, ResponseConstants.SUCCESS,
					userDAO.getAllExpenses(user.getUserName()));
		} catch (EmptyResultDataAccessException e) {
			logger.error("Exception Occured----->No Expenses found" + e.getMessage());
			return new ApiResponse(ResponseConstants.NOT_FOUND_CODE, ResponseConstants.SUCCESS);
		} catch (Exception e) {
			return new ApiResponse(ResponseConstants.ERROR_CODE, ResponseConstants.ERROR);
		}
	}

	public ApiResponse getUserExpenses(String sessionId) {
		int userId = sessionDAO.getuserIdBySessionId(sessionId);
		User user = (User) userDAO.getUserById(userId);
		try {
			return new ApiResponse(ResponseConstants.SUCCESS_CODE, ResponseConstants.SUCCESS,
					userDAO.getUserExpenses(user.getUserName()));
		}catch (EmptyResultDataAccessException e) {
			logger.error("Exception Occured----->User Expenses Not Found" + e.getMessage());
			return new ApiResponse(ResponseConstants.NOT_FOUND_CODE, ResponseConstants.SUCCESS);
		} 
		catch (Exception e) {
			return new ApiResponse(ResponseConstants.ERROR_CODE, ResponseConstants.ERROR);
		}
	}

	public ApiResponse getUserById(String sessionId) {
		int userId = sessionDAO.getuserIdBySessionId(sessionId);
		try {
			return new ApiResponse(ResponseConstants.SUCCESS_CODE, ResponseConstants.SUCCESS,
					userDAO.getUserById(userId));
		}catch (EmptyResultDataAccessException e) {
			logger.error("Exception Occured----->No User Found" + e.getMessage());
			return new ApiResponse(ResponseConstants.NOT_FOUND_CODE, ResponseConstants.SUCCESS);
		} 
		catch (Exception e) {
			return new ApiResponse(ResponseConstants.ERROR_CODE, ResponseConstants.ERROR);
		}
	}

}
