package com.harshproject.chatapp.dao;

import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.harshproject.chatapp.dto.UserDTO;
import com.harshproject.chatapp.utils.Encryption;

//USER CRUD
public class UserDAO {
	
	public boolean isLogin(UserDTO userDTO) throws SQLException, ClassNotFoundException, NoSuchAlgorithmException {
		Connection con=null;
		PreparedStatement pstmt = null;
		ResultSet rs=null;
		final String SQL= "select userid from users where userid=? and password=?";
		try {
			con = CommonDAO.createConnection();
			pstmt = con.prepareStatement(SQL);
			pstmt.setString(1, userDTO.getUserid() );
			String encryptedPwd = Encryption.passwordEncrypt(new String(userDTO.getPassword()));
			pstmt.setString(2,encryptedPwd);
			rs = pstmt.executeQuery();
			return rs.next();
			//			if(rs.next()) {
//				return true;
//			}
//			else {
//				return false;
//			}
			}
		finally {
			if(rs!=null) {
				rs.close();
			}
			if(pstmt!=null) {
				pstmt.close();
			}
			if(con!=null) {
				con.close();
			}
		}
	}
	
	public int add(UserDTO userDTO) throws ClassNotFoundException, SQLException,Exception {
		System.out.println("Rec "+userDTO.getUserid()+" "+userDTO.getPassword());
		Connection connection = null;
		Statement stmt= null;//query
		try {//guarded region
		connection = CommonDAO.createConnection();//step-1 connection created
		//step-2 we do a query
		stmt = connection.createStatement();
		//INSERT INTO USERS(USERID, PASSWORD) VALUES('Ram','RAM123');
		int record = stmt.executeUpdate("Insert into users(userid,password) values('"+userDTO.getUserid()+"','"+Encryption.passwordEncrypt(new String(userDTO.getPassword()))+"')");//insert,delete,update
		return record;
		}
		finally {//always execute
			if(stmt!=null) {
				stmt.close();
			}
			if(connection!=null) {
				connection.close();
			}
		
		
		}
		
		
	}
}
