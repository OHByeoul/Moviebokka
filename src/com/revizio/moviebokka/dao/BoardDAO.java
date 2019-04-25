package com.revizio.moviebokka.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
	private Statement stmt= null; 
	private PreparedStatement preparedStatement = null;
	private PreparedStatement pstmt= null;
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
	
	public int getTotalCnt(int type, String search, String keyword) throws SQLException {
		int tot = 0;
		String sql;
		
		if(search != null && keyword.length() > 1) {
			if(search.equals("all")) {
				sql = "select count(*) from board where brd_type = " + type + " and (brd_title like '%" + keyword + "%' or brd_content like '%" + keyword + "%')";
			} else {
				sql = "select count(*) from board where brd_type = " + type + " and " + search + " like '%" + keyword + "%'";
			}
		} else {
			sql = "select count(*) from board where brd_type = " + type;
		}
		
		try {
			conn = getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			if (rs.next()) tot = rs.getInt(1);
		} catch(Exception e) {	
			System.out.println(e.getMessage()); 
		} finally {
		    close();
		}
		
		return tot;
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
	 public int myBoardCount(int mem_id) {
	      String sql = "Select Count(*) FROM board WHERE mem_id = ?";
	      conn = instance.getConnection();
	      int count = 0;
	      try {
	         preparedStatement = conn.prepareStatement(sql);
	         preparedStatement.setInt(1, mem_id);
	         rs = preparedStatement.executeQuery();
	         
	         if(rs.next()) {
	            count = rs.getInt(1);
	         }

	      }catch (Exception e) {
	         e.printStackTrace();
	      }finally {
	         closeIdleConnection();
	      }
	      return count;
	   }

	   public List<Board> myBoardList(int mem_id, int startNum, int startRow, int endRow) {
	      String sql = "SELECT * FROM (SELECT rownum rn, a.* FROM (SELECT brd_id, brd_title, brd_regdate FROM board WHERE mem_id=? ORDER BY brd_regdate desc) a) WHERE rn BETWEEN ? AND ?";
	      conn = instance.getConnection();
	      List<Board> list = new ArrayList<Board>();
	      

	      try {
	         preparedStatement = conn.prepareStatement(sql);
	         preparedStatement.setInt(1, mem_id);
	         preparedStatement.setInt(2, startRow);
	         preparedStatement.setInt(3, endRow);
	         rs = preparedStatement.executeQuery();
	         
	         while(rs.next()) {
	            
	            Board board = new Board();
	            board.setMyListNum(startNum);
	            board.setBrd_id(rs.getInt(2));
	            board.setBrd_title(rs.getString(3));
	            board.setBrd_regdate(rs.getDate(4));
	            list.add(board);
	            startNum--;
	         }
	      }catch (Exception e) {
	         // TODO: handle exception
	         e.printStackTrace();
	      }finally {
	         closeIdleConnection();
	      }
	      return list;
	   }
	   
	   public int myReviewCount(int mem_id) {
	      String sql = "Select Count(*) FROM review WHERE mem_id = ?";
	      conn = instance.getConnection();
	      int count = 0;
	      try {
	         preparedStatement = conn.prepareStatement(sql);
	         preparedStatement.setInt(1, mem_id);
	         rs = preparedStatement.executeQuery();
	         
	         if(rs.next()) {
	            count = rs.getInt(1);
	         }

	      }catch (Exception e) {
	         e.printStackTrace();
	      }finally {
	         closeIdleConnection();
	      }
	      return count;
	   }

	   public List<Board> myReviewList(int mem_id, int startNum, int startRow, int endRow) {
	      String sql = "SELECT * FROM (SELECT rownum rn, a.* FROM (SELECT rev_id, rev_title, rev_regdate FROM review WHERE mem_id = ? ORDER BY rev_regdate desc) a) WHERE rn  BETWEEN ? AND ?";
	      conn = instance.getConnection();
	      List<Board> list = new ArrayList<Board>();
	      
	      try {
	         preparedStatement = conn.prepareStatement(sql);
	         preparedStatement.setInt(1, mem_id);
	         preparedStatement.setInt(2, startRow);
	         preparedStatement.setInt(3, endRow);
	         rs = preparedStatement.executeQuery();
	         
	         while(rs.next()) {
	            
	            Board board = new Board();
	            board.setMyListNum(startNum);
	            board.setBrd_id(rs.getInt(2));
	            board.setBrd_title(rs.getString(3));
	            board.setBrd_regdate(rs.getDate(4));
	            list.add(board);
	            startNum--;
	         }
	      }catch (Exception e) {
	         // TODO: handle exception
	         e.printStackTrace();
	      }finally {
	         closeIdleConnection();
	      }
	      return list;
	   }
	   
	   public List<Board> list(int startRow, int endRow, int type, String search, String keyword) throws SQLException {
			List<Board> list = new ArrayList<Board>();
			Connection conn = null;
			PreparedStatement pstmt= null;
			ResultSet rs = null;
			String sql;
			
			if(search != null && keyword.length() > 1) {
				if(search.equals("all")) {
					sql = "select * from (select rownum rn ,a.* from " + 
							" (select * from board where brd_type=? and (brd_title like ? or brd_content like ?) order by brd_ref desc,brd_step asc) a ) "+
							" where rn between ? and ?";
				} else {
					sql = "select * from (select rownum rn ,a.* from " +
							" (select * from board where brd_type=? and " + search + " like ? order by brd_ref desc,brd_step asc) a ) " +
							" where rn between ? and ?";
				}
			} else { 
				sql = "select * from (select rownum rn ,a.* from " + 
					" (select * from board where brd_type=? order by brd_ref desc,brd_step asc) a ) "+
					" where rn between ? and ?";
			}
			
			try {
				conn = getConnection();
				pstmt = conn.prepareStatement(sql);
				if(search != null) {
					if(search.equals("all")) {
						pstmt.setInt(1, type);
						pstmt.setString(2, "%" + keyword + "%");
						pstmt.setString(3, "%" + keyword + "%");
						pstmt.setInt(4, startRow);
						pstmt.setInt(5, endRow);
					} else {
						pstmt.setInt(1, type);
						pstmt.setString(2, "%" + keyword + "%");
						pstmt.setInt(3, startRow);
						pstmt.setInt(4, endRow);
					}
				} else {
					pstmt.setInt(1, type);
					pstmt.setInt(2, startRow);
					pstmt.setInt(3, endRow);
				}
				rs = pstmt.executeQuery();
				while (rs.next()) {
					Board board = new Board();
					board.setBrd_id(rs.getInt("brd_id"));
					board.setBrd_type(rs.getInt("brd_type"));
					board.setMem_nick(rs.getString("mem_nick"));
					board.setBrd_title(rs.getString("brd_title"));
					board.setBrd_cnt(rs.getInt("brd_cnt"));
					board.setBrd_ip(rs.getString("brd_ip"));
					board.setBrd_ref(rs.getInt("brd_ref"));
					board.setBrd_level(rs.getInt("brd_level"));
					board.setBrd_step(rs.getInt("brd_step"));
					board.setBrd_recom(rs.getInt("brd_recom"));
					board.setBrd_unrecom(rs.getInt("brd_unrecom"));
					board.setBrd_regdate(rs.getDate("brd_regdate"));
					list.add(board);
				}
			} catch(Exception e) {	System.out.println(e.getMessage()); 
			} finally {
			    close();
			}
			
			return list;
		}
	   
	   public Board select(int brd_id) throws SQLException {
			Connection conn = null;
			PreparedStatement pstmt= null;
			ResultSet rs = null;
			String sql = "select * from board where brd_id=?";
			Board board = new Board();
			try {
				conn = getConnection();
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, brd_id);
				rs = pstmt.executeQuery();
				if (rs.next()) {				
					board.setBrd_id(rs.getInt("brd_id"));
					board.setBrd_type(rs.getInt("brd_type"));
					board.setMem_id(rs.getInt("mem_id"));
					board.setMem_nick(rs.getString("mem_nick"));
					board.setBrd_title(rs.getString("brd_title"));
					board.setBrd_content(rs.getString("brd_content"));
					board.setBrd_cnt(rs.getInt("brd_cnt"));
					board.setBrd_ip(rs.getString("brd_ip"));
					board.setBrd_regdate(rs.getDate("brd_regdate"));
					board.setBrd_recom(rs.getInt("brd_recom"));
					board.setBrd_unrecom(rs.getInt("brd_unrecom"));
					board.setBrd_ref(rs.getInt("brd_ref"));
					board.setBrd_level(rs.getInt("brd_level"));
					board.setBrd_step(rs.getInt("brd_step"));
				}
			} catch(Exception e) {	System.out.println(e.getMessage()); 
			} finally {
			    close();
			}
			return board;
		}
	   
	   public int insert(Board board) throws SQLException {
			int brd_id = board.getBrd_id();		
			Connection conn = null;
			PreparedStatement pstmt= null;
			ResultSet rs = null;
			int result = 0;

			try {
				conn = getConnection();
				if(brd_id != 0) {
	                String minSql = "select nvl2(min(brd_step), min(brd_step), -1) minstep from board where brd_ref = ? and brd_step > ? and brd_level <= ?";
	                pstmt = conn.prepareStatement(minSql);
	                pstmt.setInt(1, board.getBrd_ref());
	                pstmt.setInt(2, board.getBrd_step());
	                pstmt.setInt(3, board.getBrd_level());
	                rs = pstmt.executeQuery();
	                rs.next();
	                int step = rs.getInt("minstep");
	                rs.close();
	                pstmt.close();
	                
	                if(step == -1) { //맨 밑으로
	                    String selectSql = "select max(brd_step) + 1 maxstep from board where brd_ref = ?";
	                    pstmt = conn.prepareStatement(selectSql);    
	                    pstmt.setInt(1, board.getBrd_ref());
	                    rs = pstmt.executeQuery();
	                    rs.next();
	                    step =  rs.getInt("maxstep");
	                    rs.close();
	                    board.setBrd_step(step);
	                } else {    //끼워넣기
	                    String updateSql = "update board set brd_step = brd_step + 1 where brd_ref = ? and brd_step >= ?";
	                    pstmt = conn.prepareStatement(updateSql);
	                    pstmt.setInt(1, board.getBrd_ref());
	                    pstmt.setInt(2, step);
	                    pstmt.executeUpdate();
	                    board.setBrd_step(step);
	                }
	                pstmt.close();
					board.setBrd_level(board.getBrd_level() + 1);
				}

	            String nextSql = "select BOARD_SEQ.NEXTVAL nextval from dual";
				pstmt = conn.prepareStatement(nextSql);
				rs = pstmt.executeQuery();
				rs.next();
				int nextval = rs.getInt("nextval");
				rs.close();
				pstmt.close();

	            String insertSql = "insert into board "
	                    + "(brd_id, brd_type, mem_id, mem_nick, brd_title, brd_content, brd_ref, brd_step, brd_level, brd_ip, brd_regdate) "
	                    + "values "
	                    + "(?,?,?,?,?,?,?,?,?,?,sysdate)";
				pstmt = conn.prepareStatement(insertSql);
				pstmt.setInt(1,  nextval);
				pstmt.setInt(2, board.getBrd_type());
				pstmt.setInt(3, board.getMem_id());
				pstmt.setString(4, board.getMem_nick());
				pstmt.setString(5, board.getBrd_title());
				pstmt.setString(6, board.getBrd_content());

				if(brd_id == 0) {
				    pstmt.setInt(7, nextval);
				} else {
				    pstmt.setInt(7, board.getBrd_ref());
				}
				pstmt.setInt(8,  board.getBrd_step());
				pstmt.setInt(9, board.getBrd_level());
				pstmt.setString(10, board.getBrd_ip());
				
				result = pstmt.executeUpdate(); 
	        } catch (Exception e) {
	            System.out.println(e.getMessage());
	            e.printStackTrace();
			} finally {
			    close();
			}
			/*
			String nextSql = "select BOARD_SEQ.NEXTVAL nextval from dual";
			String insertSql = "insert into board "
					+ "(brd_id, brd_type, mem_id, mem_nick, brd_title, brd_content, brd_ref, brd_step, brd_level, brd_ip, brd_regdate) "
					+ "values "
					+ "(?,?,?,?,?,?,?,?,?,?,sysdate)";
			String refSql = "update board set brd_step = brd_step + 1 where brd_ref = ? and brd_step > ?";

			try {			
				// 댓글 존재시 
				if (brd_id != 0) {
					pstmt = conn.prepareStatement(refSql);
					pstmt.setInt(1, board.getBrd_ref());
					pstmt.setInt(2, board.getBrd_step());
					pstmt.executeUpdate();
					pstmt.close();
					board.setBrd_step(board.getBrd_step()  + 1);
					board.setBrd_level(board.getBrd_level() + 1);
				}
				pstmt = conn.prepareStatement(nextSql);
				rs = pstmt.executeQuery();
				rs.next();
				int nextval = rs.getInt("nextval");
				rs.close();
				pstmt.close();

				pstmt = conn.prepareStatement(insertSql);
				pstmt.setInt(1,  nextval);
				pstmt.setInt(2, board.getBrd_type());
				pstmt.setInt(3, board.getMem_id());
				pstmt.setString(4, board.getMem_nick());
				pstmt.setString(5, board.getBrd_title());
				pstmt.setString(6, board.getBrd_content());

				if(brd_id == 0) {
				    pstmt.setInt(7, nextval);
				} else {
				    pstmt.setInt(7, board.getBrd_ref());
				}
				pstmt.setInt(8,  board.getBrd_step());
				pstmt.setInt(9, board.getBrd_level());
				pstmt.setString(10, board.getBrd_ip());
				
				result = pstmt.executeUpdate(); 
			} catch(Exception e) {
			    System.out.println(e.getMessage()); 
			} finally {
			    close();
			}
			*/
			return result;
		}

	   public int update(Board board, int mem_id) throws SQLException {
			Connection conn = null;	
			PreparedStatement pstmt= null; 
			int result = 0;			
			String sql="update board set brd_title=?,mem_nick=?,brd_content=?,brd_ip=? where brd_id=? and mem_id=?";
			try {
				conn = getConnection();
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, board.getBrd_title());
				pstmt.setString(2, board.getMem_nick());
				pstmt.setString(3, board.getBrd_content());
				pstmt.setString(4, board.getBrd_ip());
				pstmt.setInt(5, board.getBrd_id());
				pstmt.setInt(6, mem_id);
				
				result = pstmt.executeUpdate();
			} catch(Exception e) {
			    System.out.println(e.getMessage());
			    e.printStackTrace();
			} finally {
			    close();
			}
			return result;
		}
	   
	   public int delete(int brd_id, int mem_id) throws SQLException {
			Connection conn = null;	
			PreparedStatement pstmt= null; 
			int result = 0;		    
			String sql="delete from board where brd_id=? and mem_id=?";

			try {
			    conn = getConnection();
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, brd_id);
				pstmt.setInt(2, mem_id);
				result = pstmt.executeUpdate();
			} catch(Exception e) {
			    System.out.println(e.getMessage()); 
			    e.printStackTrace();
			} finally {
			    close();
			}
			return result;
		}
	   
	   public void updateCount(int brd_id) throws SQLException {
			Connection conn = null;
			PreparedStatement pstmt= null; 
			String sql="update board set brd_cnt = brd_cnt+1 where brd_id=?";
			try {
				conn = getConnection();
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, brd_id);
				pstmt.executeUpdate();
			} catch(Exception e) {	System.out.println(e.getMessage()); 
			} finally {
			    close();
			}
		}
				
	   
	   public void close() {
	        try {
	            if(rs != null) rs.close();
	            if(stmt != null) stmt.close(); 
	            if(pstmt != null) pstmt.close();
	            if (conn !=null) conn.close();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	   }

}
