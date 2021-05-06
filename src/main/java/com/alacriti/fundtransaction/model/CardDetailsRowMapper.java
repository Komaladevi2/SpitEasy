package com.alacriti.fundtransaction.model;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class CardDetailsRowMapper implements RowMapper<FundCardDetails> {

	public FundCardDetails mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		FundCardDetails card=new FundCardDetails();
		card.setUserId(rs.getInt(1));
		card.setCardNo(rs.getLong(2));
		card.setAccountHoldersName(rs.getString(7));
		card.setExpiry(rs.getString(3));
		
		return card;
	}

}