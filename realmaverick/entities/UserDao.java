package com.realmaverick.entities;

//my project classes
import com.realmaverick.db.DbUtil;

//bootstrap classes
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDao {

//Pre-Action preparation
//==============================================================================================================================
	
	// As connections should automatically generated....
	// main() created object of service ---> service created object of dao ---> in
	// dao we create connections.
	Connection con;

	// static fields for preparing single statements for each type of method action
	PreparedStatement checkUserStmt;
	PreparedStatement insertUserStmt;
	PreparedStatement changePasswdStmt;
	PreparedStatement editprofileStmt;

	// Do all initialisation task for above field in ctor..
	public UserDao() throws Exception {

		// getting connection : creating connection using the static method of DbUtil
		// and taking object int Reference of Connection
		con = DbUtil.getConnection();

		// adding sql-query into statements..
		// 1.signIn Query :
		String checkUserSql = "SELECT id, first_name, last_name, email, passwd, mobile  FROM users WHERE email = ? and passwd = ?";
		checkUserStmt = con.prepareStatement(checkUserSql);

		// 2.signUp Query :
		String insertUserSql = "INSERT INTO users (first_name, last_name, email, passwd, mobile) VALUES(?, ?, ?, ?, ?)";
		insertUserStmt = con.prepareStatement(insertUserSql);

		// 3.editprofileSql Query :
		// update users set first_name = 'new' where id=1;
		String editprofileSql = "update users set ? = ? where id=?";
		editprofileStmt = con.prepareStatement(editprofileSql);

		// 4.changePasswd Query :
		// String changePasswdSql = "UPDATE users SET passwd = ? WHERE email = ? and
		// passwd = ?";
		String changePasswdSql = "update users set passwd = ? where email = ? and passwd = ?";
		changePasswdStmt = con.prepareStatement(changePasswdSql);

	}

//Action : Methods for dealing with DB
//==============================================================================================================================

// SignIn() ....LOGINPAGE
//-----------------------------------------------------------------------------------------------------------------

	public User daoSignIn(String email, String password) throws Exception {

		checkUserStmt.setString(1, email);
		checkUserStmt.setString(2, password);

		ResultSet rs = checkUserStmt.executeQuery();

		if (rs.next()) { // As only one row will returned...email is unique constraint

			// getting a values of rows..to put into object of POJO..
			int userId = rs.getInt("id");
			String firstName = rs.getString("first_name");
			String lastName = rs.getString("last_name");
			email = rs.getString("email");
			String passwd = rs.getString("passwd");
			String mobile = rs.getString("mobile");

			// creating an obj of POGO-USER and returning back to service
			User u = new User(userId, firstName, lastName, email, passwd, mobile);
			return u;
		}
		return null;

	}

// SignUp...LOGINPAGE
//-----------------------------------------------------------------------------------------------------------------

	public int daoRegister(User u) throws Exception {
		// insertion not happned..
		int cnt = 0;

		// setting the values for ? in sql query..
		insertUserStmt.setString(1, u.getfName());
		insertUserStmt.setString(2, u.getlName());
		insertUserStmt.setString(3, u.getEmail());
		insertUserStmt.setString(4, u.getPasswd());
		insertUserStmt.setString(5, u.getMobile());

		// executing statement...
		cnt = insertUserStmt.executeUpdate();

		// returning count to service for validation purpose..
		return cnt;
	}

	
//==============================================================================================================================
	
// 8.change passwd
//------------------------------------------------------------------------------------------------------------------

	public int daochangePasswd(String newPasswd, String email, String passwd) {

		int cnt = 0;
		try {
			// setting changePasswdStmt
			changePasswdStmt.setString(1, newPasswd);
			changePasswdStmt.setString(2, email);
			changePasswdStmt.setString(3, passwd);

			// executing changePasswdStmt
			cnt = changePasswdStmt.executeUpdate();

			return cnt;

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return cnt;
	}

// 9.edit profile
//------------------------------------------------------------------------------------------------------------------------
	public int daoEditProfile(String fieldName, String fieldValue, int userId) throws Exception {

		int cnt = 0;

		// setting editprofileStmt
		editprofileStmt.setString(1, fieldName);
		editprofileStmt.setString(2, fieldValue);
		editprofileStmt.setInt(3, userId);

		// executing editprofileStmt
		cnt = editprofileStmt.executeUpdate();

		return cnt;
	}

}
