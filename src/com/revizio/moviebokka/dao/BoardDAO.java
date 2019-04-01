package com.revizio.moviebokka.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.revizio.moviebokka.dto.Board;


public class BoardDAO {
	private Connection conn;
	List<Board> boards;
	
	public BoardDAO() throws SQLException {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			String user = "scott";
			String password = "tiger";
			conn = DriverManager.getConnection(url, user, password);
			boards = new ArrayList<Board>();
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public List getAllBoard() {
		boards.clear();
		String sql = "SELECT * FROM board";
		try {
			PreparedStatement prst = conn.prepareStatement(sql);
			ResultSet rs = prst.executeQuery();
			while(rs.next()) {
				int id = rs.getInt(1);
				String title = rs.getString(2);
				String content = rs.getString(3);
				addBoard(id,title,content);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return boards;
	}

	private void addBoard(int id, String title, String content) {
		Board board = new Board(id, title, content);
		boards.add(board);
	}

	public List<Board> getBoard(String startNum, String endNum) {
		boards.clear();
		String query = "SELECT S2.rnum, S2.b_id, S2.title, S2.b_content " + 
						"FROM (SELECT ROWNUM as rnum, S1.b_id, S1.title, S1.b_content " + 
								"FROM (SELECT b_id, title, b_content " + 
										"FROM board " + 
										"ORDER BY b_id ASC) S1 " + 
								"WHERE ROWNUM <= ?) S2 " + 
						"WHERE ROWNUM >= ?";
		try {
			PreparedStatement preparedStatement = conn.prepareStatement(query);
			preparedStatement.setInt(1, Integer.parseInt(endNum));
			preparedStatement.setInt(2, Integer.parseInt(startNum));
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()) {
				int rownum = rs.getInt("rnum");
				int id = rs.getInt("b_id");
				String title = rs.getString("title");
				String content = rs.getString("b_content");
				boards.add(new Board(id, title, content));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return boards;
	}

	public int getTotalDataCnt() {
		String query = "SELECT COUNT(*) FROM board";
		try {
			PreparedStatement preparedStatement;
			preparedStatement = conn.prepareStatement(query);
			ResultSet rs = preparedStatement.executeQuery();
			int cnt = 0;
			while(rs.next()) {
				cnt = rs.getInt(1);
			}
			return cnt;
		} catch (SQLException e) {
			e.printStackTrace();
		}		
		return 0;
	}

	public List getPaingPage(int startNum, int endNum) {
		boards.clear();
		String query = "SELECT S2.rnum, S2.b_id, S2.title, S2.b_content " + 
				"FROM (SELECT ROWNUM as rnum, S1.b_id, S1.title, S1.b_content " + 
						"FROM (SELECT b_id, title, b_content " + 
								"FROM board " + 
								"ORDER BY b_id ASC) S1 " + 
						"WHERE ROWNUM <= ?) S2 " + 
				"WHERE rnum >= ?";
		PreparedStatement preparedStatement;
		try {
			preparedStatement = conn.prepareStatement(query);
			preparedStatement.setInt(2, startNum);
			preparedStatement.setInt(1, endNum);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()) {
				int id = rs.getInt("b_id");
				String title = rs.getString("title");
				String content = rs.getString("b_content");
				System.out.println(id+" "+title+" "+content);
				boards.add(new Board(id, title, content));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return boards; 
	}

	public Board getDetailBoardById(String id) {
		String query = "SELECT b_id, title, b_content FROM board WHERE b_id = ?";
		
		try {
			PreparedStatement preparedStatement = conn.prepareStatement(query);
			preparedStatement.setString(1, id);
			ResultSet rs = preparedStatement.executeQuery();
			
			while(rs.next()) {
				int bId = rs.getInt("b_id");
				String title = rs.getString("title");
				String content = rs.getString("b_content");
				return new Board(bId, title, content);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null ;
		
	}
}
