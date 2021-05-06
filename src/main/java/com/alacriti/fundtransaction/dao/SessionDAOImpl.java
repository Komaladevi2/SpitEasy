package com.alacriti.fundtransaction.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@SuppressWarnings("deprecation")
@Repository
public class SessionDAOImpl implements SessionDAO {

	@Autowired
	JdbcTemplate jdbcTemplate;

	
	@Override
	public void createSession(int userId, String sessionId) {

		try {
			/*
			 * checking for user who logged in past and having expired session in sessions
			 * table
			 */
			jdbcTemplate.queryForObject(SqlQueries.GET_USER_WITH_SESSION, new Object[] { userId }, int.class);

			/*
			 * updating user with new session id which overrides the above expired session
			 * id in sessions table
			 */
			jdbcTemplate.update(SqlQueries.UPDATE_COOKIE, sessionId, userId);
		} catch (Exception e) {
			// creating session id for the first time user login
			jdbcTemplate.update(SqlQueries.ADD_COOKIE, userId, sessionId, true);
		}

	}

	@Override
	public int getuserIdBySessionId(String sessionId) {
		return jdbcTemplate.queryForObject(SqlQueries.GET_USER_ID_BY_SESSION_ID, new Object[] {sessionId}, int.class);
	}

}
