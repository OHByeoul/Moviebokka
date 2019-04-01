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

import com.revizio.moviebokka.dto.GetMovieInfoForm;
import com.revizio.moviebokka.dto.MovieInfo;

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
		String query = "INSERT INTO movieInfo VALUES (?,?,?,?,?)";
		conn = instance.getConnection();
		int result = 0;
		try {
			preparedStatement = conn.prepareStatement(query);
			preparedStatement.setInt(1, movieInfo.getM_code());
			preparedStatement.setString(2, movieInfo.getM_title());
			preparedStatement.setString(3, movieInfo.getM_img());
			preparedStatement.setString(4, movieInfo.getM_userRating());
			preparedStatement.setString(5, movieInfo.getM_story());
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
		if (key.equals("director")) {
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
		} else if (key.equals("actor")) {
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
		}
		return result;
	}

	public GetMovieInfoForm getMovieDetailInfo(int movieCode) {
		GetMovieInfoForm getMovieInfoForm = new GetMovieInfoForm();
		String query = "SELECT * FROM movieInfo WHERE m_code=?";
		conn = instance.getConnection();
		try {
			preparedStatement = conn.prepareStatement(query);
			preparedStatement.setInt(1, movieCode);
			rs = preparedStatement.executeQuery();
			while (rs.next()) {
				getMovieInfoForm.setM_code(rs.getInt("m_code"));
				getMovieInfoForm.setM_title(rs.getString("m_title"));
				getMovieInfoForm.setM_img(rs.getString("m_img"));
				getMovieInfoForm.setM_userRating(rs.getFloat("m_userRating"));
				getMovieInfoForm.setM_story(rs.getString("m_story"));
			}
			//todo : setGenre
			getMovieInfoForm.setActor(getActorInfo(movieCode));
			getMovieInfoForm.setDirector(getDirectorInfo(movieCode));
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeIdleConnection();
		}
		return getMovieInfoForm;
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
		conn = instance.getConnection();
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
		String query = "SELECT * FROM movieInfo WHERE m_code=?";
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
}
