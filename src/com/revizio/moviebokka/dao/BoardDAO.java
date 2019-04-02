package com.revizio.moviebokka.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.revizio.moviebokka.dto.Board;


public class BoardDAO {
	private static BoardDAO instance;
	private Connection conn = null;
	private PreparedStatement preparedStatement = null;
	private ResultSet rs = null;

	List<Board> boards;
	
	public BoardDAO() {
		boards = new ArrayList<>();
	}
	
	public static BoardDAO getInstance() {
		if (instance == null) {
			instance = new BoardDAO();
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
	public List getAllBoard() {
		boards.clear();
		String sql = "SELECT * FROM board";
		conn = instance.getConnection();
		try {
			preparedStatement = conn.prepareStatement(sql);
			rs = preparedStatement.executeQuery();
			while(rs.next()) {
				int id = rs.getInt(1);
				String title = rs.getString(2);
				String content = rs.getString(3);
				addBoard(id,title,content);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeIdleConnection();
		}
		return boards;
	}

	private void addBoard(int id, String title, String content) {
		Board board = new Board(id, title, content);
		boards.add(board);
	}

	public List<Board> getBoard(String startNum, String endNum) {
		conn = instance.getConnection();
		boards.clear();
		String query = "SELECT S2.rnum, S2.b_id, S2.title, S2.b_content " + 
						"FROM (SELECT ROWNUM as rnum, S1.b_id, S1.title, S1.b_content " + 
								"FROM (SELECT b_id, title, b_content " + 
										"FROM board " + 
										"ORDER BY b_id ASC) S1 " + 
								"WHERE ROWNUM <= ?) S2 " + 
						"WHERE ROWNUM >= ?";
		try {
			preparedStatement = conn.prepareStatement(query);
			preparedStatement.setInt(1, Integer.parseInt(endNum));
			preparedStatement.setInt(2, Integer.parseInt(startNum));
			rs = preparedStatement.executeQuery();
			while(rs.next()) {
				int rownum = rs.getInt("rnum");
				int id = rs.getInt("b_id");
				String title = rs.getString("title");
				String content = rs.getString("b_content");
				boards.add(new Board(id, title, content));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeIdleConnection();
		}
		
		return boards;
	}

	public int getTotalDataCnt() {
		String query = "SELECT COUNT(*) FROM board";
		conn = instance.getConnection();
		try {
			preparedStatement = conn.prepareStatement(query);
			rs = preparedStatement.executeQuery();
			int cnt = 0;
			while(rs.next()) {
				cnt = rs.getInt(1);
			}
			return cnt;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeIdleConnection();
		}	
		return 0;
	}

	public List getPaingPage(int startNum, int endNum) {
		conn = instance.getConnection();
		boards.clear();
		String query = "SELECT S2.rnum, S2.b_id, S2.title, S2.b_content " + 
				"FROM (SELECT ROWNUM as rnum, S1.b_id, S1.title, S1.b_content " + 
						"FROM (SELECT b_id, title, b_content " + 
								"FROM board " + 
								"ORDER BY b_id ASC) S1 " + 
						"WHERE ROWNUM <= ?) S2 " + 
				"WHERE rnum >= ?";
		try {
			preparedStatement = conn.prepareStatement(query);
			preparedStatement.setInt(2, startNum);
			preparedStatement.setInt(1, endNum);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()) {
				int id = rs.getInt("b_id");
				String title = rs.getString("title");
				String content = rs.getString("b_content");
				//System.out.println(id+" "+title+" "+content);
				boards.add(new Board(id, title, content));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeIdleConnection();
		}
		return boards; 
	}

	public Board getDetailBoardById(String id) {
		String query = "SELECT b_id, title, b_content FROM board WHERE b_id = ?";
		conn = instance.getConnection();
		try {
			preparedStatement = conn.prepareStatement(query);
			preparedStatement.setString(1, id);
			rs = preparedStatement.executeQuery();
			
			while(rs.next()) {
				int bId = rs.getInt("b_id");
				String title = rs.getString("title");
				String content = rs.getString("b_content");
				return new Board(bId, title, content);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeIdleConnection();
		}
		return null ;
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
