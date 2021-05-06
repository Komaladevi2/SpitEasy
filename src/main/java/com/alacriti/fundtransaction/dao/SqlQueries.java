package com.alacriti.fundtransaction.dao;

public class SqlQueries {

	public static final String SAVE_USER = "INSERT INTO AL395_users_table(user_name, user_email_id, user_password, user_phone_number) VALUES (?,?,?,?)";

	public static final String UPDATE_USER = "UPDATE AL395_users_table SET user_name=?,user_email_id=?,user_password=?, user_phone_number=? WHERE user_id=?";

	public static final String GET_BY_NAME = "SELECT * FROM AL395_users_table WHERE user_name=?";

	public static final String VALIDATE_USER = "SELECt * FROM AL395_users_table WHERE user_email_id=? AND user_password=?";

	public static final String VALIDATE_FRIEND = "SELECT * FROM AL395_friends_table WHERE (user_name=? and friend_name=?) or (user_name=? and friend_name=?)";

	public static final String ADD_FRIEND = "INSERT INTO AL395_friends_table(user_name, friend_name)values(?,?)";

	public static final String GET_FRIENDS = "SELECT * FROM AL395_friends_table WHERE user_name=? or friend_name=?";

	public static final String GET_EXPENSE_ID = "SELECT expense_id FROM AL395_expense_table ORDER BY expense_id DESC LIMIT 1";

	public static final String ADD_EXPENSE_MEMBERS = "INSERT INTO AL395_expense_members(paid_by, paid_to, splitted_amount, expense_description, expense_amount, expense_id)values(?,?,?,?,?,?)";

	public static final String ADD_EXPENSE = "INSERT INTO AL395_expense_table(expense_description, expense_amount, user_name, splitted_amount)values(?,?,?,?)";

	public static final String GET_ALL_EXPENSES = "SELECT paid_by, paid_to, sum(splitted_amount)FROM AL395_expense_members WHERE (paid_by=? or paid_to=?) and status=? group by paid_by, paid_to";

	public static final String GET_USER_EXPENSES = "SELECT * from AL395_expense_table where expense_Id IN (SELECT expense_Id FROM AL395_expense_members WHERE paid_To=? or paid_By=?) ORDER BY expense_id DESC";

	public static final String GET_BY_EMAIL = "SELECT * FROM AL395_users_table WHERE user_email_id=?";

	public static final String GET_BY_ID = "SELECT * FROM AL395_users_table WHERE user_id=?";

	public static final String CHECK_NAME = "SELECT * FROM AL395_users_table WHERE user_name=?";

	public static final String ADD_CARD = "INSERT INTO AL395_card_details(user_id, card_no, expiry, account_holders_name)values(?,?,?,?)";

	public static final String GET_CARDS="SELECT * FROM AL395_card_details where user_id=?";

	public static final String UPDATE_BALANCE="UPDATE AL395_card_details SET balance=? where user_id=?";
	
	public static final String UPDATE_EXPENSE="UPDATE AL395_expense_members SET status=? where paid_by=? and paid_to=?";
	
	public static final String GET_BALANCE= "SELECT balance FROM AL395_card_details WHERE user_id =?";

	public static final String CREATE_TRANSACTION="INSERT INTO AL395_transactions_table(payee, beneficiary, amount, status)values(?,?,?,?)";

	public static final String GET_TRANSACTIONS="SELECT * FROM AL395_transactions_table WHERE payee=? or beneficiary=? ORDER BY transaction_id DESC";

	public static final String GET_ID_BY_NAME="SELECT user_id from AL395_users_table WHERE user_name=?";
	
	public static final String GET_RECENT_USER="SELECT user_id FROM AL395_users_table ORDER BY user_id DESC LIMIT 1";

	public static final String ADD_COOKIE = "INSERT INTO AL395_sessions_table(user_id, session_id, status)values(?, ?, ?)";
	
	public static final String UPDATE_COOKIE="UPDATE AL395_sessions_table SET session_id=? WHERE user_id=?";

	public static final String GET_USER_WITH_SESSION="select user_id from AL395_sessions_table where user_id=?";

	public static final String GET_USER_ID_BY_SESSION_ID="select user_id from AL395_sessions_table where session_id=?";
}
