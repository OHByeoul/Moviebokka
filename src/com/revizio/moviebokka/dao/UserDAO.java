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
import com.revizio.moviebokka.dto.Member1;

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
//temp : member -> member1
	public Member1 isAthenticate(String id, String password) {
		System.out.println("in dao??");
		System.out.println(id+" "+password);
		String query = "SELECT * FROM member1 WHERE mem_email=? AND mem_pass=?";
		Member1 member = new Member1();
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
				//member.setMem_nick(rs.getString("mem_nick"));
			//	result = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return member;
	}
	
	public int userJoin(Member1 member) {
		String query = "INSERT INTO member1 VALUES (mem_seq.nextval,?,?,?,?)";
		conn = instance.getConnection();
		int result = 0;
		try {
			preparedStatement = conn.prepareStatement(query);
			preparedStatement.setString(1, member.getMem_email());
			preparedStatement.setString(2, member.getMem_pass());
			preparedStatement.setString(3, member.getMem_hash_pass());
			preparedStatement.setInt(4, member.getMem_auth());
			result = preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeIdleConnection();
		}
		return result;
	}
	
	private void closeIdleConnection() {
	      try {
	         if (rs != null) {
	            rs.close();
	         }
	         if (preparedStatement != null) {
	            preparedStatement.close();
	         }
	         if (conn != null) {
	            conn.close();
	         }
	      } catch (SQLException e) {
	         e.printStackTrace();
	      }
	   }

	public boolean getIsEmailConfirmed(String id) {
		String query = "SELECT mem_auth FROM member1 WHERE mem_email = ?";
		conn = instance.getConnection();
		try {
			preparedStatement = conn.prepareStatement(query);
			preparedStatement.setString(1, id);
			rs = preparedStatement.executeQuery();
			while(rs.next()) {
				return rs.getBoolean(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeIdleConnection();
		}
		return false;
	}

	public String getSelectedUserEmail(String id) {
		String query = "SELECT mem_email FROM member1 WHERE mem_email = ?";
		conn = instance.getConnection();
		String mail = "";
		try {
			preparedStatement = conn.prepareStatement(query);
			preparedStatement.setString(1, id);
			rs = preparedStatement.executeQuery();
			while(rs.next()) {
				mail = rs.getString("mem_email");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeIdleConnection();
		}
		return mail;
	}
}

