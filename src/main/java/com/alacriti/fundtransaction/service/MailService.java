package com.alacriti.fundtransaction.service;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alacriti.fundtransaction.dao.SessionDAO;
import com.alacriti.fundtransaction.dao.UserDAO;
import com.alacriti.fundtransaction.dao.UserDAOImpl;
import com.alacriti.fundtransaction.model.User;
import com.alacriti.fundtransaction.response.ApiResponse;
import com.alacriti.fundtransaction.response.ResponseConstants;

@Component
public class MailService {

	@Autowired 
	SessionDAO sessionDAO;
	
	@Autowired
	UserDAO userDAO;
	
	public ApiResponse inviteFriend(String sessionId, String friendEmail) throws MessagingException {

		int id = sessionDAO.getuserIdBySessionId(sessionId);
		User user = userDAO.getUserById(id);

		final String username = "komaladevikosuri1999@gmail.com";
		final String password = "komalanages";

		Properties props = new Properties();
		props.put("mail.smtp.auth", true);
		props.put("mail.smtp.starttls.enable", true);
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");

		Session session = Session.getInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		});

		try {
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("komaladevikosuri1999@gmail.com"));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(friendEmail));
			message.setSubject("Invitation to Split Easy Application");
			message.setContent(user.getUserName()+ "<h1> Invites You To SplitEasy<h1>"
					+ "<h4>An Application To Relieve Your Stress</h4>"
					+ "<h4>One Place To Share Your Expenses and Settle Up</h4>"
					+ " <button>Visit SplitEasy</button>", "text/html");
			
			Transport.send(message);

			return new ApiResponse(ResponseConstants.SUCCESS_CODE, ResponseConstants.SUCCESS);
		} catch (MessagingException e) {
			e.printStackTrace();
			return new ApiResponse(ResponseConstants.ERROR_CODE, ResponseConstants.ERROR);
		}
		
	}

}
