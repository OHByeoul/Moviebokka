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

import com.revizio.moviebokka.constant.Constants;
import com.revizio.moviebokka.dto.GetMovieInfoForm;
import com.revizio.moviebokka.dto.MovieInfo;
import com.revizio.moviebokka.dto.Review;

public class MovieDAO {
   private static MovieDAO instance;
   private Connection conn = null;
   private PreparedStatement preparedStatement = null;
   private ResultSet rs = null;

   public MovieDAO() {

   }

   public static MovieDAO getInstance() {
      if (instance == null) {
         instance = new MovieDAO();
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

   public int InsertMovieInfo(MovieInfo movieInfo) {
      String query = "INSERT INTO movie VALUES (?,?,?,?,?,?,?)";
      conn = instance.getConnection();
      int result = 0;
      try {
         preparedStatement = conn.prepareStatement(query);
         preparedStatement.setInt(1, movieInfo.getM_code());
         preparedStatement.setString(2, movieInfo.getM_title());
         preparedStatement.setString(3, movieInfo.getM_pub_date());
         preparedStatement.setString(4, movieInfo.getM_img());
         preparedStatement.setString(5, movieInfo.getM_story());
         preparedStatement.setString(6, movieInfo.getM_userRating());
         preparedStatement.setString(7, movieInfo.getM_cnt());
         result = preparedStatement.executeUpdate();

      } catch (SQLException e) {
         e.printStackTrace();
      } finally {
         closeIdleConnection();
      }
      return result;
   }

   public int InsertSubInfo(int movieCode, String key, String name) {
      conn = instance.getConnection();
      String query;
      int result = 0;
      if (key.equals(Constants.DIRECTOR)) {
         query = "INSERT INTO director VALUES (DIRECTOR_SEQ.NEXTVAL, ?,?)";
         try {
            preparedStatement = conn.prepareStatement(query);
            preparedStatement.setInt(1, movieCode);
            preparedStatement.setString(2, name);
            result = preparedStatement.executeUpdate();
         } catch (SQLException e) {
            e.printStackTrace();
         } finally {
            closeIdleConnection();
         }
      } else if (key.equals(Constants.ACTOR)) {
         query = "INSERT INTO actor VALUES (ACTOR_SEQ.NEXTVAL,?,?)";
         try {
            preparedStatement = conn.prepareStatement(query);
            preparedStatement.setInt(1, movieCode);
            preparedStatement.setString(2, name);
            result = preparedStatement.executeUpdate();
         } catch (SQLException e) {
            e.printStackTrace();
         } finally {
            closeIdleConnection();
         }
      } else if (key.equals(Constants.GENRE)) {
         query = "INSERT INTO genre VALUES (GENRE_SEQ.NEXTVAL,?,?)";
         try {
            preparedStatement = conn.prepareStatement(query);
            preparedStatement.setInt(1, movieCode);
            preparedStatement.setString(2, name);
            result = preparedStatement.executeUpdate();
         } catch (SQLException e) {
            e.printStackTrace();
         } finally {
            closeIdleConnection();
         }
      }
      return result;
   }

   public GetMovieInfoForm getMovieDetailInfo(int movieCode) {
      GetMovieInfoForm getMovieInfoForm = new GetMovieInfoForm();
      String query = "SELECT * FROM movie WHERE m_code=?";
         conn = instance.getConnection();
      try {
         preparedStatement = conn.prepareStatement(query);
         preparedStatement.setInt(1, movieCode);
         rs = preparedStatement.executeQuery();
         while (rs.next()) {
            getMovieInfoForm.setM_code(rs.getInt("m_code"));
            getMovieInfoForm.setM_title(rs.getString("m_title"));
            getMovieInfoForm.setM_img(rs.getString("m_img"));
            getMovieInfoForm.setM_user_rating(rs.getFloat("m_user_rating"));
            getMovieInfoForm.setM_story(rs.getString("m_story"));
            getMovieInfoForm.setM_pub_date(rs.getString("m_pub_date"));
         }
         //todo : setGenre
         getMovieInfoForm.setActor(getActorInfo(movieCode));
         getMovieInfoForm.setDirector(getDirectorInfo(movieCode));
         getMovieInfoForm.setGenre(getGenreInfo(movieCode));
      } catch (SQLException e) {
         e.printStackTrace();
      } finally {
         closeIdleConnection();
      }
      return getMovieInfoForm;
   }

   private List<String> getGenreInfo(int movieCode) {
      List<String> genres = new ArrayList<String>();
      String query = "SELECT g_name FROM genre WHERE m_code=?";
      conn = instance.getConnection();
      try {
         preparedStatement = conn.prepareStatement(query);
         preparedStatement.setInt(1, movieCode);
         rs = preparedStatement.executeQuery();

         while (rs.next()) {
            String name = rs.getString("g_name");
            genres.add(name);
         }
      } catch (SQLException e) {
         e.printStackTrace();
      } finally {
         closeIdleConnection();
      }
      return genres;
   }

   private List getDirectorInfo(int movieCode) {
      List<String> directors = new ArrayList<String>();
      String query = "SELECT d_name FROM director WHERE m_code=?";
      conn = instance.getConnection();
      try {
         preparedStatement = conn.prepareStatement(query);
         preparedStatement.setInt(1, movieCode);
         rs = preparedStatement.executeQuery();

         while (rs.next()) {
            String name = rs.getString("d_name");
            directors.add(name);
         }
      } catch (SQLException e) {
         e.printStackTrace();
      } finally {
         closeIdleConnection();
      }
      return directors;
   }

   private List getActorInfo(int movieCode) {
      String query = "SELECT a_name FROM actor WHERE m_code=?";
      List<String> actors = new ArrayList<String>();
      if(conn == null) {
         conn = instance.getConnection();
      }
      try {
         preparedStatement = conn.prepareStatement(query);
         preparedStatement.setInt(1, movieCode);
         rs = preparedStatement.executeQuery();

         while (rs.next()) {
            String name = rs.getString("a_name");
            actors.add(name);
         }
      } catch (SQLException e) {
         e.printStackTrace();
      } finally {
         closeIdleConnection();
      }
      return actors;
   }

   public boolean isExist(int movieCode) {
      String query = "SELECT * FROM movie WHERE m_code=?";
      ResultSet rs;
      boolean result = false;
      conn = instance.getConnection();
      try {
         preparedStatement = conn.prepareStatement(query);
         preparedStatement.setInt(1, movieCode);
         rs = preparedStatement.executeQuery();

         if (rs.next()) {
            result = true;
         }
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

   public List<GetMovieInfoForm> getMovieDetailInfoList() {
      List<GetMovieInfoForm> movieInfoForms = new ArrayList<>();
      GetMovieInfoForm getMovieInfoForm = new GetMovieInfoForm();
      String query = "SELECT m_code FROM movie WHERE ROWNUM >= 1 AND ROWNUM <= 6 ORDER BY m_user_rating DESC";
      conn = instance.getConnection();
      List<Integer> codes = new ArrayList<>();
      try {
         preparedStatement = conn.prepareStatement(query);
         rs = preparedStatement.executeQuery();
         while(rs.next()) {
            int movieCode = rs.getInt("m_code");
            codes.add(movieCode);
         }
         for(Integer code : codes) {
            movieInfoForms.add(getMovieDetailInfo(code));
         }
      } catch (SQLException e) {
         e.printStackTrace();
      } finally {
         closeIdleConnection();
      }
      return movieInfoForms;
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
         if(queryResult>=1) {
        	 result = true;
         }
      } catch (SQLException e) {
         e.printStackTrace();
      } finally {
         closeIdleConnection();
      }
	return result;
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

	public boolean deleteReview(int revId) {
		String query = "DELETE FROM review WHERE rev_id=?";		
		conn = instance.getConnection();
		int result = 0;
		try {
			preparedStatement = conn.prepareStatement(query);
			preparedStatement.setInt(1, revId);
			result = preparedStatement.executeUpdate();
			if(result >0) {
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

	public List<GetMovieInfoForm> getSearchedMovieList(String search) {
		List<GetMovieInfoForm> movieInfoForms = new ArrayList<>();
		GetMovieInfoForm getMovieInfoForm = new GetMovieInfoForm();
		String query = "SELECT m_code FROM movie WHERE m_title LIKE '%' || ? || '%'";
		conn = instance.getConnection();
		List<Integer> codes = new ArrayList<>();
		try {
			preparedStatement = conn.prepareStatement(query);
			preparedStatement.setString(1, search);
			rs = preparedStatement.executeQuery();
			while (rs.next()) {
				int movieCode = rs.getInt("m_code");
				codes.add(movieCode);
			}
			for (Integer code : codes) {
				movieInfoForms.add(getMovieDetailInfo(code));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeIdleConnection();
		}
		return movieInfoForms;
	}
}