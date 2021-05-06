package com.alacriti.fundtransaction.model;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class UserModelRowMapper implements RowMapper<User> {

	public User mapRow(ResultSet rs, int rowNum) throws SQLException {
//		User user=new User(rs.getString(2), rs.getString(3), rs.getString(4), rs.getLong(5));
		User user=new User();
		user.setUserId(rs.getInt(1));
		user.setUserName(rs.getString(2));
		user.setUserEmailId(rs.getString(3));
		user.setUserPassword(rs.getString(4));
		user.setUserPhoneNumber(rs.getLong(5));
		return user;
	}

}
