package com.harshproject.chatapp.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import static com.harshproject.chatapp.utils.configReader.getValue;
//THROW EARLY AND CATCH LATER
public interface CommonDAO {
	public static Connection createConnection() throws ClassNotFoundException, SQLException {
		//STEP-1 load a Driver 
		Class.forName(getValue("DRIVER"));
		//STEP-2 making a connection
		final String CONNECTION_STRING = getValue("CONNECTION_URL");
		final String USER_ID = getValue("USERID");
		final String PASSWORD = getValue("PASSWORD");
		Connection con = DriverManager.getConnection(CONNECTION_STRING,USER_ID,PASSWORD);
		if(con != null) {
			System.out.println("Connection created....");
			//con.close();
		}
		return con;
	}
	
}
 