package com.revizio.moviebokka.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class UserDAO {
	private static UserDAO instance;
	private Connection conn = null;
	private PreparedStatement preparedStatement = null;
	private ResultSet rs = null;
	
	public UserDAO() {
		
	}
	
	public static UserDAO getInstance() {
		if (instance == null) {
			instance = new UserDAO();
		}
		return instance;
	}

	public Connection getConnection() {
		try {
			Context context = new InitialContext();
			DataSource dataSource = (DataSource) context.lookup("java:comp/env/jdbc/OracleDB");
			conn = dataSource.getConnection();
		} catch (NamingException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}

	public boolean isAthenticate(String id, String password) {
		boolean result = false;
		String query = "SELECT * FROM member WHERE mem_email=? AND mem_pass=?";
		try {
			conn = instance.getConnection();
			preparedStatement = conn.prepareStatement(query);
			preparedStatement.setString(1, id);
			preparedStatement.setString(2, password);
			rs = preparedStatement.executeQuery();
			while(rs.next()) {
				result = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
}

