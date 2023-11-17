package com.realmaverick.db;

import java.sql.Connection;
import java.sql.DriverManager;

public class DbUtil {
	
	//Static fields required for creating connection.
	public static final String DB_DRIVER = "com.mysql.cj.jdbc.Driver";
	public static final String DB_URL = "jdbc:mysql://localhost:3306/dac_quotes";
	public static final String DB_USER = "dac";
	public static final String DB_PASSWD = "dac";

	//loading and registeration of driver classses.
	static {
		try {
			Class.forName(DB_DRIVER);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.exit(1);
		}
	}
	
	//static method to create connection.
	public static Connection getConnection() throws Exception{
		Connection con = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWD);
		return con;
	}

}
