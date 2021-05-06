package com.alacriti.fundtransaction.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alacriti.fundtransaction.model.ExpenseModel;
import com.alacriti.fundtransaction.model.FundCardDetails;
import com.alacriti.fundtransaction.response.ApiResponse;
import com.alacriti.fundtransaction.response.ResponseConstants;
import com.alacriti.fundtransaction.service.SessionManagement;
import com.alacriti.fundtransaction.service.TransactionService;

@RestController
@RequestMapping(value = "/api", produces = "application/json")
@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
public class TransactionController {

	private static final Logger logger = LoggerFactory.getLogger(UserController.class);

	@Autowired
	TransactionService transactionService;

	@Autowired
	SessionManagement sessionManagement;

	@PostMapping("/addCard")
	public ApiResponse addCard(@RequestBody FundCardDetails card, HttpServletRequest request,
			HttpServletResponse response) {
		logger.info("method ---> addCard method in use");
		if (sessionManagement.validateSession(request, response)==null)
			return new ApiResponse(ResponseConstants.EXPIRE_CODE, ResponseConstants.EXPIRED);
		return  transactionService.addCard(card);
		
	}

	@GetMapping("/getCards")
	public ApiResponse getCards(HttpServletRequest request, HttpServletResponse response) {
		logger.info("method ---> getCards method in use");
		String sessionId=sessionManagement.validateSession(request, response);
		if(sessionId==null)
			return new ApiResponse(ResponseConstants.EXPIRE_CODE, ResponseConstants.EXPIRED);
		return transactionService.getCards(sessionId);
	}

	@PostMapping("/proceedPayment")
	public ApiResponse proceedPayment(@RequestBody ExpenseModel expense, HttpServletRequest request, HttpServletResponse response) {
		String sessionId=sessionManagement.validateSession(request, response);
		if(sessionId==null)
			return new ApiResponse(ResponseConstants.EXPIRE_CODE, ResponseConstants.EXPIRED);
	
		return 	transactionService.proceedPayment(expense);
	}

	@GetMapping("/getTransactions")
	public ApiResponse getTransactions(HttpServletRequest request, HttpServletResponse response) {
		logger.info("method ---> getTransactions controller method in use");
		String sessionId=sessionManagement.validateSession(request, response);
		if(sessionId==null)
			return new ApiResponse(ResponseConstants.EXPIRE_CODE, ResponseConstants.EXPIRED);
		return transactionService.getTransactions(sessionId);
		
	}
}
