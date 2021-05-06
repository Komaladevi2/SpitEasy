package com.alacriti.fundtransaction.controller;

import java.sql.SQLException;

import javax.mail.MessagingException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alacriti.fundtransaction.model.Expense;
import com.alacriti.fundtransaction.model.User;
import com.alacriti.fundtransaction.response.ApiResponse;
import com.alacriti.fundtransaction.response.ResponseConstants;
import com.alacriti.fundtransaction.service.MailService;
import com.alacriti.fundtransaction.service.SessionManagement;
import com.alacriti.fundtransaction.service.UserService;

@RestController
@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
@RequestMapping(value = "/api")

public class UserController {

	private static final Logger logger = LoggerFactory.getLogger(UserController.class);

	@Autowired
	UserService userService;

	@Autowired
	MailService mailService;

	@Autowired
	JdbcTemplate jdbcTemplate;

	@Autowired
	SessionManagement sessionManagement;

	@PostMapping("/saveUser")
	public ApiResponse saveUser(@RequestBody User user) throws SQLException {
		logger.info("method ---> saveUser method in use");
		
		return userService.saveUser(user);
		
	}

	@GetMapping("/validateUser/{userEmail}/{password}")
	public ApiResponse validateUser(@PathVariable("userEmail") String userEmail,
			@PathVariable("password") String password, HttpServletRequest request, HttpServletResponse response) {
		logger.info("method ---> validateUser method in use ");
		return userService.validateUser(userEmail, password, response);
	}

	@PutMapping("/updateUser")
	public ApiResponse updateUser(@RequestBody User user, HttpServletRequest request, HttpServletResponse response) {
		logger.info("method ---> updateUser method in use");
		if (sessionManagement.validateSession(request, response) == null)
			return new ApiResponse(ResponseConstants.EXPIRE_CODE, ResponseConstants.EXPIRED);
		return userService.updateUser(user);
	}

	@GetMapping("/inviteFriend/{friendEmail}")
	public ApiResponse inviteFriend(
			@PathVariable("friendEmail") String friendEmail, HttpServletRequest request, HttpServletResponse response)
			throws MessagingException {
		logger.info("method ---> inviteFriend method in use");
		String sessionId=sessionManagement.validateSession(request, response);
		if (sessionId == null)
			return new ApiResponse(ResponseConstants.EXPIRE_CODE, ResponseConstants.EXPIRED);
		return  mailService.inviteFriend(sessionId, friendEmail);
		
	}

	@GetMapping("/findUser")
	public ApiResponse findUserById(HttpServletRequest request, HttpServletResponse response) {
		logger.info("method ---> findUserById method in use");
		String sessionId = sessionManagement.validateSession(request, response);
		if (sessionId == null) 
			return new ApiResponse(ResponseConstants.EXPIRE_CODE, ResponseConstants.EXPIRED);
		return userService.getUserById(sessionId); 
	}

	@GetMapping("addFriend/{friendEmail}")
	public ApiResponse addFriend(@PathVariable("friendEmail") String friendEmail, HttpServletRequest request,
			HttpServletResponse response) {
		logger.info("method ---> addFriend controller method in use");
		String sessionId = sessionManagement.validateSession(request, response);
		if (sessionId == null)
			return new ApiResponse(ResponseConstants.EXPIRE_CODE, ResponseConstants.EXPIRED);
		return userService.addFriend(sessionId, friendEmail);
		
	}

	@GetMapping("/getFriends")
	public ApiResponse getFriends(HttpServletRequest request, HttpServletResponse response) {
		logger.info("method ---> getFriends method in use");
		String sessionId = sessionManagement.validateSession(request, response);
		if (sessionId == null)
			return new ApiResponse(ResponseConstants.EXPIRE_CODE, ResponseConstants.EXPIRED);
		return userService.getFriends(sessionId);
	}

	@PostMapping("/addExpense")
	public ApiResponse createExpense(@RequestBody Expense expense, HttpServletRequest request,
			HttpServletResponse response) {
		logger.info("method ---> creteExpense method in use");
		if (sessionManagement.validateSession(request, response) == null)
			return new ApiResponse(ResponseConstants.EXPIRE_CODE, ResponseConstants.EXPIRED);
		return userService.createExpense(expense);
		
	}

	@GetMapping("/getAllExpenses")
	public ApiResponse getAllExpenses(HttpServletRequest request, HttpServletResponse response) {
		logger.info("method ---> getAllExpenses method in use");
		String sessionId = sessionManagement.validateSession(request, response);
		if (sessionId == null)
			return new ApiResponse(ResponseConstants.EXPIRE_CODE, ResponseConstants.EXPIRED);
		return userService.getAllExpenses(sessionId);
		

	}

	@GetMapping("/getUserExpenses")
	public ApiResponse getUserExpenses(HttpServletRequest request, HttpServletResponse response) {
		logger.info("method ---> getUserExpenses method in use");
		String sessionId = sessionManagement.validateSession(request, response);
		if (sessionId == null)
			return new ApiResponse(ResponseConstants.EXPIRE_CODE, ResponseConstants.EXPIRED);
		return userService.getUserExpenses(sessionId); 
	}

	@GetMapping("/logout")
	public void userLogout(HttpServletResponse response) {
		logger.info("method ---> logout method in use");
		
		Cookie cookie = new Cookie("sessionId", null);
		cookie.setMaxAge(0);
		cookie.setSecure(true);
		cookie.setHttpOnly(true);
		cookie.setPath("/");

		response.addCookie(cookie);
	}

}
