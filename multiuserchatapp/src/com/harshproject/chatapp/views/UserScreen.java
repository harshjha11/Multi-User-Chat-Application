package com.harshproject.chatapp.views;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import com.harshproject.chatapp.dao.UserDAO;
import com.harshproject.chatapp.dto.UserDTO;
import com.harshproject.chatapp.utils.UserInfo;

public class UserScreen extends JFrame{
	private JTextField useridtxt;
	private JPasswordField passwordField;

	
	public static void main(String[] args) {
		
					UserScreen window = new UserScreen();
					
	}
	UserDAO userDAO = new UserDAO();
	private void doLogin() {
		String userid = useridtxt.getText();
		char[] password = passwordField.getPassword();
		
		UserDTO userDTO = new UserDTO(userid,password);
		try {
			String message = "";
			if(userDAO.isLogin(userDTO)) {
				message = "Welcome " +userid;
				UserInfo.USER_NAME = userid;
				JOptionPane.showMessageDialog(this, message);
				setVisible(false);
				dispose();
				DashBoard dashBoard = new DashBoard(message);
				dashBoard.setVisible(true);
				
			}
			else {
				message="Invalid Userid or Password";
				JOptionPane.showMessageDialog(this, message);
			}
//			JOptionPane.showMessageDialog(this, message);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	private void register() {
		String userid = useridtxt.getText();
		char[] password = passwordField.getPassword();
		//UserDAO userDAO = new UserDAO();
		UserDTO userDTO = new UserDTO(userid,password);
		
		try{
			int result= userDAO.add(userDTO);
			if(result>0) {
				JOptionPane.showMessageDialog(this, "Registered Successfully");
				//System.out.println("Record added.....");
			}
			else {
				JOptionPane.showMessageDialog(this, "Register Failed");
			}
		}
		catch(ClassNotFoundException | SQLException ex) {
			System.out.println("DB issue.....");
			ex.printStackTrace();
		}
		catch(Exception ex) {
			System.out.println("Some generic exception raised....");
			ex.printStackTrace();//where is exception
		}
		System.out.println("userid " +userid+" Password "+password); // ClassName@HashCode(Hexadecimal)
	}

	/**
	 * Create the application.
	 */
	public UserScreen() {
		setResizable(false);
		setTitle("LOGIN");
		getContentPane().setBackground(new Color(255, 255, 255));
		
		getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("LOGIN");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 40));
		lblNewLabel.setBounds(275, 29, 135, 40);
		getContentPane().add(lblNewLabel);
		
		useridtxt = new JTextField();
		useridtxt.setBounds(362, 135, 261, 26);
		getContentPane().add(useridtxt);
		useridtxt.setColumns(10);
		
		JLabel useridlbl = new JLabel("Userid");
		useridlbl.setFont(new Font("Tahoma", Font.BOLD, 18));
		useridlbl.setBounds(217, 142, 135, 19);
		getContentPane().add(useridlbl);
		
		JLabel pwdidlbl = new JLabel("password");
		pwdidlbl.setFont(new Font("Tahoma", Font.BOLD, 18));
		pwdidlbl.setBounds(217, 200, 135, 19);
		getContentPane().add(pwdidlbl);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(362, 200, 261, 26);
		getContentPane().add(passwordField);
		
		JButton loginbt = new JButton("Login");
		loginbt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				doLogin();
			}
		});
		loginbt.setFont(new Font("Tahoma", Font.BOLD, 16));
		loginbt.setBounds(244, 297, 93, 32);
		getContentPane().add(loginbt);
		
		JButton registerbt = new JButton("Register");
		registerbt.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				register();
			}
		});
		registerbt.setFont(new Font("Tahoma", Font.BOLD, 16));
		registerbt.setBounds(384, 297, 110, 32);
		getContentPane().add(registerbt);
		setSize(784, 449);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setVisible(true);
		
	}
}
