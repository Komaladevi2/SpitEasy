package com.alacriti.fundtransaction.model;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class GroupModelRowMapper implements RowMapper<Group> {

	public Group mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		Group group=new Group();
		
		group.setGroupId(1);
		group.setGroupName(rs.getString(2));
		group.setCreatedBy(rs.getString(3));
		group.setCreateOn(rs.getDate(4));
		return group;
	}

}