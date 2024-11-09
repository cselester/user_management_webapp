package com.user.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.user.model.user;

public class UserDAO {
	private String jdbcURL="jdbc:mysql://localhost:3306/userDB";
	private String jdbcUserName="root";
	private String jdbcPassword="Mansi@2022";
	
	private static final String INSERT_USERS_SQL="INSERT INTO users"+"(name,email,country,passwd) VALUES"+"(?,?,?,?)";
	private static final String SELECT_USER_BY_ID="SELECT * FROM users where id=?";
	private static final String SELECT_ALL_USERS="SELECT * from users";
	private static final String DELETE_USERS_SQL="delete * FROM users where id=?";
	private static final String UPDATE_USERS_SQL="UPDATE user set name=?,email=?,country=?,passwd=? where id=?";
	
	public UserDAO() {
		
	}
	
	public Connection getConnection() {
		Connection connection= null;
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection=DriverManager.getConnection(jdbcURL, jdbcUserName,jdbcPassword);
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return connection;
	}
	
	public void insertUser(user user) {
		UserDAO dao= new UserDAO();
		try(Connection connection=dao.getConnection()){
			PreparedStatement preparedstatement = connection.prepareStatement(INSERT_USERS_SQL);
			preparedstatement.setString(1,user.getName());
			preparedstatement.setString(2,user.getEmail());
			preparedstatement.setString(3,user.getCountry());
			preparedstatement.setString(4,user.getPassword());
			
			preparedstatement.executeUpdate();
		} 
		catch (SQLException e) {
		
			e.printStackTrace();
		}
	}
	
	
	public user selectUser(int id) {
		user user=new user();
		UserDAO dao= new UserDAO();
		try(Connection connection=dao.getConnection()){
			PreparedStatement preparedstatement = connection.prepareStatement(SELECT_USER_BY_ID);
			preparedstatement.setInt(1,id);
			
			ResultSet resultSet=preparedstatement.executeQuery();
			user.setName(resultSet.getString("name"));
			user.setEmail(resultSet.getString("email"));
			user.setCountry(resultSet.getString("country"));
			user.setPassword(resultSet.getString("password"));

			
		} 
		catch (SQLException e) {
		
			e.printStackTrace();
		}
		return user;
	}
	
	public List<user> selectAllUsers(){
		List<user> users=new ArrayList <user>();
		UserDAO dao= new UserDAO();
		try(Connection connection=dao.getConnection()){
			PreparedStatement preparedstatement = connection.prepareStatement(SELECT_ALL_USERS);
			ResultSet resultSet=preparedstatement.executeQuery();
			
			
			while(resultSet.next()) {
				
				String uname=resultSet.getString("uname");
				String email=resultSet.getString("email");
				String country=resultSet.getString("country");
				String password=resultSet.getString("passwd");

				users.add(new user(uname,email,country,password));
			}
		} 
		catch (SQLException e) {
			
			e.printStackTrace();
		}
		return users;
	}
	
	public boolean deleteUser(int id) {
		boolean status=false;
		UserDAO dao=new UserDAO();
		try(Connection connection=dao.getConnection()){
			PreparedStatement preparedstatement = connection.prepareStatement(DELETE_USERS_SQL);
			preparedstatement.setInt(1,id);
			
			status=preparedstatement.execute();
			}
		catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		return status;
	}
	
	
	public boolean updateUser(user user) {
		boolean status=false;
		UserDAO dao=new UserDAO();
		try(Connection connection=dao.getConnection()){
			PreparedStatement preparedstatement = connection.prepareStatement(UPDATE_USERS_SQL);
			preparedstatement.setString(1,user.getName());
			preparedstatement.setString(2,user.getEmail());
			preparedstatement.setString(3,user.getCountry());
			preparedstatement.setString(4,user.getPassword());
			
			
			status=preparedstatement.executeUpdate()>0;
	} 
		catch (SQLException e) {
	
		e.printStackTrace();
	}
		return status;
	}
	
	public static void main(String args[]) {
		UserDAO dao= new UserDAO();
		if(dao.getConnection()!=null)
		{
			System.out.print("Successfully connected to database");
		}
		else {
			System.out.print("Problem");
		}
		
		user user = new user("test","test@abc.com","India","abc@123");
		dao.insertUser(user);
		
	}
	
}
