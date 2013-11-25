package socialnetwork;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SocialNetwork {
	private Connection connection;
	private PreparedStatement addUser;
	private String query;	
	private boolean isLoggedIn = false;
	private String response;
	
	private String userName;
	private String password;
	private String firstName;
	private String lastName;
	private String email;
	private String responseButton;
	
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	
	public String getResponseButton() {
		return responseButton;
	}

	public void setResponseButton(String responseButton) {
		this.responseButton = responseButton;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public String accessDatabase(){
		try{
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
		}catch (ClassNotFoundException e) {
			System.out.println("No such Driver");
			e.printStackTrace();
		}
		try{
			this.connection = DriverManager.getConnection("jdbc:odbc:SocialNetwork");
		}catch (SQLException e) {
			System.out.println("No such Database");
			e.printStackTrace();
		}
		switch(getResponseButton()){
		case "Register": addToDataBase();
		return response;
		case "Log In": findUser(getUserName(), getPassword());
		if(isLoggedIn)
			return "SUCCESS_LOG_IN";
		else
			return "FAILURE";
		default:;
		return "FAILURE";
		}
	}

	public void findUser(String userName, String userPassword){
		try{
			query ="SELECT  * FROM users WHERE username = '" + userName + "' AND password = '"
					+ userPassword + "'";
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(query);
			while(resultSet.next()){
				String username = resultSet.getString(1);
				String password = resultSet.getString(2);
				if(((!username.equals(null)) || userName.equals(username)) && ((!password.equals(null))
						|| userPassword.equals(password))){
					isLoggedIn = true;
				}else{
					isLoggedIn = false;
				}
			}
			connection.close();
		}catch (SQLException e) {
			System.out.println("Error executing");
			e.printStackTrace();
		}
	}
	
	public void addToDataBase(){
		try{
				addUser = connection.prepareStatement("INSERT INTO users(username, password, firstname,"
						+ "lastname, email) VALUES(?, ?, ?, ?, ?)");
				addUser.setString(1, getUserName());
				addUser.setString(2, getPassword());
				addUser.setString(3, getFirstName());
				addUser.setString(4, getLastName());
				addUser.setString(5, getEmail());
				addUser.executeUpdate();
				connection.close();
				response = "SUCCESS_REGISTER";
		}catch (SQLException e) {
			System.out.println("Error executing");
			e.printStackTrace();
		}
	}

}
