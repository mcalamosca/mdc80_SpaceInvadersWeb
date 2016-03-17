package edu.pitt.is1017.spaceinvaders;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;
/**
 * 
 * @author Micah Calamosca
 *
 */
public class User {
	private int userID;
	private String lastName;
	private String firstName;
	private String email;
	private String password;
	private boolean loggedIn = false;

	public User(int userID){
		DbUtilities db = new DbUtilities();

		String sql = "SELECT * FROM `alieninvasion`.`users` WHERE `userID` = \"" + userID + "\"";

		ResultSet rs = db.getResultSet(sql);

		try {
			if(rs.next()){
				try {
					this.userID = rs.getInt(userID);
					this.lastName = rs.getString("lastName");
					this.firstName = rs.getString("firstName");
					this.email = rs.getString("email");
					this.password = rs.getString("password");
					this.loggedIn = true;
					
					/*
					System.out.println(this.userID);
					System.out.println(this.lastName);
					System.out.println(this.firstName);
					System.out.println(this.email);
					System.out.println(this.password);
					*/
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		db.closeConnection();
	}

	public User(String email, String password){
		DbUtilities db = new DbUtilities();

		String sql = "SELECT * ";
		sql += "FROM `alieninvasion`.`users` WHERE `email` = \"" + email + "\" ";
		sql += "and `password` = md5(\"" + password + "\")";

		ResultSet rs = db.getResultSet(sql);

		//needs to set appropriate values from result set
		try {
			if(rs.next()){
				this.userID = rs.getInt("userID");
				this.lastName = rs.getString("lastName");
				this.firstName = rs.getString("firstName");
				this.email = rs.getString("email");
				this.password = rs.getString("password");
				this.loggedIn = true;
				
				JOptionPane.showMessageDialog(null, "Login successful!");
				/*
				System.out.println(this.userID);
				System.out.println(this.lastName);
				System.out.println(this.firstName);
				System.out.println(this.email);
				System.out.println(this.password);
				*/				
			}
			else
			{
				JOptionPane.showMessageDialog(null, "Username or Password incorrect!");
			}
		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		db.closeConnection();
	}

	public User(String lastName, String firstName, String email, String password){
		this.lastName = lastName;
		this.firstName = firstName;
		this.email = email;
		this.password = password;

		DbUtilities db = new DbUtilities();

		String sql = "INSERT INTO `alieninvasion`.`users`";
		sql += "(`lastName`,`firstName`,`email`,`password`) ";
		sql += "VALUES (\""+lastName+"\",\"" + firstName + "\",\"" + email + "\",md5(\"" + password + "\"));";

		if(db.executeQuery(sql))
			JOptionPane.showMessageDialog(null, "Registration Successful!");
		db.closeConnection();
	}

	public void saveUserInfo(){
		DbUtilities db = new DbUtilities();

		String sql = "UPDATE users ";
		
		sql += "SET `lastName` = \"" + getLastName() + "\", ";
		sql += "SET `firstName` = \"" + getFirstName() + "\", ";
		sql += "SET `email` = \"" + getEmail() + "\", ";
		sql += "SET `password` = \"" + getPassword() + "\", ";
		sql += "WHERE `userID` = \"" + getUserID() + "\";";

		if(db.executeQuery(sql))
			JOptionPane.showMessageDialog(null, "User info updated.");
		db.closeConnection();
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isLoggedIn() {
		return loggedIn;
	}

	public void setLoggedIn(boolean loggedIn) {
		this.loggedIn = loggedIn;
	}

	public int getUserID() {
		return userID;
	}


}
