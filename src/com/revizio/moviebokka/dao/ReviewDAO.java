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

import com.revizio.moviebokka.controller.ReviewRequestMapping;
import com.revizio.moviebokka.dto.GetMovieInfoForm;
import com.revizio.moviebokka.dto.Review;
import com.revizio.moviebokka.dto.UserRecommand;
import com.sun.org.apache.xpath.internal.operations.Bool;

import sun.dc.pr.PRError;

public class ReviewDAO {
	private static ReviewDAO instance;
	private Connection conn = null;
	private PreparedStatement preparedStatement = null;
	private ResultSet rs = null;

	private ReviewDAO() {

	}

	public static ReviewDAO getInstance() {
		if (instance == null) {
			instance = new ReviewDAO();
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

	public boolean createReview(Review review) {
		String query = "INSERT INTO review VALUES (rev_seq.nextval,?,?,?,?,?,?,?,?,?,?)";
		boolean result = false;
		conn = instance.getConnection();
		try {
			preparedStatement = conn.prepareStatement(query);
			preparedStatement.setString(1, review.getRev_title());
			preparedStatement.setString(2, review.getRev_content());
			preparedStatement.setInt(3, review.getRev_recommand());
			preparedStatement.setInt(4, review.getRev_unrecommand());
			preparedStatement.setDate(5, review.getRev_regdate());
			preparedStatement.setString(6, review.getRev_ip());
			preparedStatement.setInt(7, review.getRev_view());
			preparedStatement.setInt(8, review.getM_code());
			preparedStatement.setInt(9, review.getMem_id());
			preparedStatement.setString(10, review.getMem_nick());
			int queryResult = preparedStatement.executeUpdate();
			if (queryResult >= 1) {
				result = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeIdleConnection();
		}
		return result;
	}

	public int getReviewId() {
		String query = "SELECT MAX(rev_Id) FROM review";
		int revId = 0;
		conn = instance.getConnection();
		try {
			preparedStatement = conn.prepareStatement(query);
			rs = preparedStatement.executeQuery();
			while (rs.next()) {
				revId = rs.getInt(1);

			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeIdleConnection();
		}
		return revId;
	}

	public Review getReviewDetailInfo(int revId) {
		String query = "SELECT * FROM review WHERE rev_id=?";
		Review review = new Review();
		conn = instance.getConnection();
		try {
			preparedStatement = conn.prepareStatement(query);
			preparedStatement.setInt(1, revId);
			rs = preparedStatement.executeQuery();
			while (rs.next()) {
				review.setRev_id(rs.getInt("rev_id"));
				review.setRev_title(rs.getString("rev_title"));
				review.setRev_content(rs.getString("rev_content"));
				review.setRev_recommand(rs.getInt("rev_recommand"));
				review.setRev_unrecommand(rs.getInt("rev_unrecommand"));
				review.setRev_regdate(rs.getDate("rev_regdate"));
				review.setRev_ip(rs.getString("rev_ip"));
				review.setRev_view(rs.getInt("rev_view"));
				review.setM_code(rs.getInt("m_code"));
				review.setMem_id(rs.getInt("mem_id"));
				review.setMem_nick(rs.getString("mem_nick"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeIdleConnection();
		}
		return review;
	}

	public boolean deleteReview(int revId) {
		String query = "DELETE FROM review WHERE rev_id=?";
		conn = instance.getConnection();
		int result = 0;
		try {
			preparedStatement = conn.prepareStatement(query);
			preparedStatement.setInt(1, revId);
			result = preparedStatement.executeUpdate();
			if (result > 0) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeIdleConnection();
		}
		return false;
	}

	public boolean updateSelectedReview(int revId, String title, String content) {
		String query = "UPDATE review SET rev_title=?, rev_content=? WHERE rev_id=?";
		int result = 0;
		conn = instance.getConnection();

		try {
			preparedStatement = conn.prepareStatement(query);
			preparedStatement.setString(1, title);
			preparedStatement.setString(2, content);
			preparedStatement.setInt(3, revId);
			result = preparedStatement.executeUpdate();
			if (result > 0) {
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

	public List<Review> getReviewList(int movieCode) {
		String query = "SELECT * FROM review WHERE m_code=?";
		List reviews = new ArrayList<>();
		conn = instance.getConnection();
		try {
			preparedStatement = conn.prepareStatement(query);
			preparedStatement.setInt(1, movieCode);
			rs = preparedStatement.executeQuery();
			while (rs.next()) {
				Review review = new Review();
				review.setRev_id(rs.getInt("rev_id"));
				review.setRev_title(rs.getString("rev_title"));
				review.setMem_nick(rs.getString("mem_nick"));
				review.setRev_regdate(rs.getDate("rev_regdate"));
				reviews.add(review);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeIdleConnection();
		}
		return reviews;
	}
	
	public List<Review> getSearchedReviewList(String search, int start, int end) {
		List<Review> reviews = new ArrayList<>();
		 String query =	"SELECT S2.rnum, S2.rev_id, S2.rev_title, S2.mem_nick, S2.rev_regdate "+
						"FROM (SELECT ROWNUM as rnum, S1.rev_id, S1.rev_title, S1.mem_nick, S1.rev_regdate "+
								"FROM (SELECT rev_id, rev_title, mem_nick, rev_regdate "+
										"FROM review "+ 
		                                "WHERE rev_title LIKE '%' || ? || '%' OR rev_content LIKE '%' || ? || '%' "+
										"ORDER BY rev_id ASC) S1 "+ 
								"WHERE ROWNUM <= ?) S2 "+ 
					"WHERE rnum >= ?";
		
		conn = instance.getConnection();

		try {
			preparedStatement = conn.prepareStatement(query);
			preparedStatement.setString(1, search);
			preparedStatement.setString(2, search);
			preparedStatement.setInt(3, end);
			preparedStatement.setInt(4, start);
			rs = preparedStatement.executeQuery();
			while (rs.next()) {
				Review review = new Review();
				review.setRev_id(rs.getInt("rev_id"));
				review.setRev_title(rs.getString("rev_title"));
				review.setRev_regdate(rs.getDate("rev_regdate"));
				review.setMem_nick(rs.getString("mem_nick"));
				reviews.add(review);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeIdleConnection();
		}
		return reviews;
	}

	public boolean updateViewCnt(int revId) {
		String query = "UPDATE review SET rev_view = rev_view+1 WHERE rev_id = ?";
		conn = instance.getConnection();
		int result = 0;
		try {
			preparedStatement = conn.prepareStatement(query);
			preparedStatement.setInt(1, revId);
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

	public boolean updateRecommand(int id, boolean isReccomand) {
		String query = "UPDATE review SET rev_recommand = rev_recommand+? WHERE rev_id=?";
		conn = instance.getConnection();
		int result = 0;
		try {
			preparedStatement = conn.prepareStatement(query);
			if(isReccomand) {
				preparedStatement.setInt(1, 1);
			} else {
				preparedStatement.setInt(1, -1);
			}
			preparedStatement.setInt(2, id);
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

	public boolean updateUnrecommand(int id, boolean isReccomand) {
		String query = "UPDATE review SET rev_unrecommand = rev_unrecommand+? WHERE rev_id=?";
		conn = instance.getConnection();
		int result = 0;
		try {
			preparedStatement = conn.prepareStatement(query);
			if(isReccomand) {
				preparedStatement.setInt(1, 1);
			} else {
				preparedStatement.setInt(1, -1);
			}
			preparedStatement.setInt(2, id);
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

	public boolean checkRecomExist(int id, String memEmail) {
		String query = "SELECT rev_id, mem_email FROM userRecommand WHERE rev_id=? AND mem_email=?";
		conn = instance.getConnection();
		try {
			preparedStatement = conn.prepareStatement(query);
			preparedStatement.setInt(1, id);
			preparedStatement.setString(2, memEmail);
			rs = preparedStatement.executeQuery();
			while (rs.next()) {
				return true;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeIdleConnection();
		}
		return false;
	}

	public boolean createUserRecommand(int id, String memEmail, int recom, int unrecom) {
		String query = "INSERT INTO userRecommand VALUES (?,?,?,?)";
		boolean result = false;
		conn = instance.getConnection();
		try {
			preparedStatement = conn.prepareStatement(query);
			preparedStatement.setInt(1, id);
			preparedStatement.setString(2, memEmail);
			preparedStatement.setInt(3, recom);
			preparedStatement.setInt(4, unrecom);
		
			int queryResult = preparedStatement.executeUpdate();
			if (queryResult >= 1) {
				result = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeIdleConnection();
		}
		return result;
	}

	public boolean updateUserRecommand(int id, String memEmail, int recom, int unrecom) {
		String query = "UPDATE userRecommand SET recom_status = ?, unrecom_status=? WHERE rev_id=? AND mem_email=?";
		conn = instance.getConnection();
		int result = 0;
		try {
			preparedStatement = conn.prepareStatement(query);
			preparedStatement.setInt(1, recom);
			preparedStatement.setInt(2, unrecom);
			preparedStatement.setInt(3, id);
			preparedStatement.setString(4, memEmail);
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

	public boolean deleteUserRecommand(int id, String memEmail) {
		String query = "DELETE userRecommand WHERE rev_id=? AND mem_email=?";
		conn = instance.getConnection();
		int result = 0;
		try {
			preparedStatement = conn.prepareStatement(query);
			preparedStatement.setInt(1, id);
			preparedStatement.setString(2, memEmail);
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

	public UserRecommand getReivewRecomInfo(int id, String memEmail) {
		System.out.println(id+" "+memEmail);
		String query = "SELECT recom_status, unrecom_status FROM userrecommand WHERE rev_id=? AND mem_email=?";
		UserRecommand userRecomman = new UserRecommand();
		conn = instance.getConnection();
		try {
			preparedStatement = conn.prepareStatement(query);
			preparedStatement.setInt(1, id);
			preparedStatement.setString(2, memEmail);
			rs = preparedStatement.executeQuery();
			while (rs.next()) {
				userRecomman.setRecom_status(rs.getInt("recom_status"));
				userRecomman.setUnrecom_status(rs.getInt("unrecom_status"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeIdleConnection();
		}
		return userRecomman;
		
	}

}
