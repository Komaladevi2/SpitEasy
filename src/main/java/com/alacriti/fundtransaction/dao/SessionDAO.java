package com.alacriti.fundtransaction.dao;

public interface SessionDAO {
	
	public void createSession(int userId, String sessionId);

	public int getuserIdBySessionId(String sessionId);
	

}
