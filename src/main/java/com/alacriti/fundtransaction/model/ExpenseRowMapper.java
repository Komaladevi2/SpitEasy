package com.alacriti.fundtransaction.model;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class ExpenseRowMapper implements RowMapper<ExpenseModel> {

	public ExpenseModel mapRow(ResultSet rs, int rowNum) throws SQLException {
		ExpenseModel expense=new ExpenseModel();
		
		expense.setPaidBy(rs.getString(1));
		expense.setPaidTo(rs.getString(2));
		expense.setSplittedAmount(rs.getFloat(3));
		
		return expense;
	}
}
