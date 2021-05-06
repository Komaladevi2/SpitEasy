package com.alacriti.fundtransaction.service;

import java.util.Arrays;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alacriti.fundtransaction.dao.SessionDAO;

@Service
public class SessionManagement {
	
	@Autowired
	SessionDAO sessionDAO;
	
	public String validateSession(HttpServletRequest request, HttpServletResponse response) {

		try {
			Cookie[] cookies = request.getCookies();
			String cookie = Arrays.stream(cookies).map(c -> c.getValue()).collect(Collectors.joining(", "));

			// extending expire time of the cookie as user requests with valid cookie
			Cookie updatedCookie = new Cookie("sessionId", cookie);
			updatedCookie.setMaxAge(30*60);// cookie will expire if user if inactive for 30 minutes
			updatedCookie.setSecure(true);
			updatedCookie.setHttpOnly(true);
			updatedCookie.setPath("/");

			response.addCookie(updatedCookie);

			return cookie;
		} catch (NullPointerException e) {
			
			return null;
		}
	}
	
	public void createSession(int userId, HttpServletResponse response) {

		UUID uuid = UUID.randomUUID();
		String sessionId = uuid.toString();

		sessionDAO.createSession(userId, sessionId);

		Cookie cookie = new Cookie("sessionId", sessionId);

		cookie.setMaxAge(30*60); // cookie is valid for 30 minutes
		cookie.setSecure(true);
		cookie.setHttpOnly(true);
		cookie.setPath("/");

		response.addCookie(cookie);

	}
	
	public void destroySession(HttpServletResponse response) {

		// removing cookie from browser after user logout
		Cookie cookie = new Cookie("sessionId", null);
		cookie.setMaxAge(0);
		cookie.setSecure(true);
		cookie.setHttpOnly(true);
		cookie.setPath("/");

		response.addCookie(cookie);
	}

}
