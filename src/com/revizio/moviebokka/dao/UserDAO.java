package com.revizio.moviebokka.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.revizio.moviebokka.dto.Member;

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

	public Member isAthenticate(String id, String password) {
		System.out.println("in dao??");
		System.out.println(id+" "+password);
		String query = "SELECT * FROM member WHERE mem_email=? AND mem_pass=?";
		Member member = new Member();
		try {
			conn = instance.getConnection();
			preparedStatement = conn.prepareStatement(query);
			preparedStatement.setString(1, id);
			preparedStatement.setString(2, password);
			rs = preparedStatement.executeQuery();
			while(rs.next()) {
				member.setMem_id(rs.getInt("mem_id"));
				member.setMem_email(rs.getString("mem_email"));
				member.setMem_pass(rs.getString("mem_pass"));
				member.setMem_nick(rs.getString("mem_nick"));
			//	result = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return member;
	}
}

