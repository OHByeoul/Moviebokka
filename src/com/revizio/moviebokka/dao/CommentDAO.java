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
import com.revizio.moviebokka.dto.ReviewComment;

public class CommentDAO {
	private static CommentDAO instance;
	private Connection conn = null;
	private PreparedStatement preparedStatement = null;
	private ResultSet rs = null;
	
	private CommentDAO() {
		
	}
	
	public static CommentDAO getInstance() {
		if (instance == null) {
			instance = new CommentDAO();
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
	
	public boolean createReviewComment(ReviewComment com) {
		String query = "INSERT INTO reviewcom VALUES (com_seq.nextval,?,?,?,?,?,?,?,?,?)";
		conn = instance.getConnection();
		int result = 0;
		try {
			preparedStatement = conn.prepareStatement(query);
			preparedStatement.setString(1, com.getCom_content());
			preparedStatement.setDate(2, com.getCom_regdate());
			preparedStatement.setString(3, com.getCom_group());
			preparedStatement.setString(4, com.getCom_depth());
			preparedStatement.setString(5, com.getCom_order());
			preparedStatement.setString(6, com.getCom_ip());
			preparedStatement.setInt(7, com.getMem_id());
			preparedStatement.setString(8, com.getMem_nick());
			preparedStatement.setInt(9, com.getRev_id());
			result = preparedStatement.executeUpdate();
			if(result > 0) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeIdleConnection();
		}
		return false;
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



	
}

