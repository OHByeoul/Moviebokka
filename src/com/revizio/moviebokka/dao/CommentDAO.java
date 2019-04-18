package com.revizio.moviebokka.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

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
		String query = "INSERT INTO reviewcom VALUES (com_seq.nextval,?,?,?,?,?,?,?,?,?,?,?)";
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
			preparedStatement.setString(7, com.getCom_data_box());
			preparedStatement.setString(8, com.getCom_data_parent());
			preparedStatement.setInt(9, com.getMem_id());
			preparedStatement.setString(10, com.getMem_nick());
			preparedStatement.setInt(11, com.getRev_id());
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

	public List<ReviewComment> getReviewCommentById(String revId) {
		String query = "SELECT * FROM reviewcom WHERE rev_id=? ORDER BY com_group ASC, com_order ASC";
		List<ReviewComment> reviewComments = new ArrayList<>();
		try {
			conn = instance.getConnection();
			preparedStatement = conn.prepareStatement(query);
			preparedStatement.setString(1, revId);
			rs = preparedStatement.executeQuery();
			while(rs.next()) {
				ReviewComment reviewComment = new ReviewComment();
				reviewComment.setCom_content(rs.getString("com_content"));
				reviewComment.setCom_regdate(rs.getDate("com_regdate"));
				reviewComment.setCom_group(rs.getString("com_group"));
				reviewComment.setCom_depth(rs.getString("com_depth"));
				reviewComment.setCom_order(rs.getString("com_order"));
				reviewComment.setCom_ip(rs.getString("com_ip"));
				reviewComment.setMem_id(rs.getInt("mem_id"));
				reviewComment.setMem_nick(rs.getString("mem_nick"));
				reviewComment.setCom_data_box(rs.getString("com_data_box"));
				reviewComment.setCom_data_parent(rs.getString("com_data_parent"));
				reviewComment.setRev_id(rs.getInt("rev_id"));
				reviewComments.add(reviewComment);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeIdleConnection();
		}
		return reviewComments;
	}

	public int getMaxGroupId() {
		String query = "SELECT MAX(com_group) FROM reviewcom";
		int result = 0;
		conn = instance.getConnection();
		
		try {
			preparedStatement = conn.prepareStatement(query);
			rs = preparedStatement.executeQuery();
			while(rs.next()) {
				result = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeIdleConnection();
		}
		
		return result;
	}



	
}

