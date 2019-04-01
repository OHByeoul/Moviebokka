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
	private GetMovieInfoForm getMovieInfoForm;  
	private Connection conn = null;
	
	public MovieDAO() {
		conn = getConnection();
		getMovieInfoForm = new GetMovieInfoForm();
	}
	
	public Connection getConnection() {
		try {
			Context context = new InitialContext();
			DataSource dataSource = (DataSource) context.lookup("java:comp/env/jdbc/OracleDB");
			conn = dataSource.getConnection();
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return conn;
	}
	
	public int InsertMovieInfo(MovieInfo movieInfo) {
		String query = "INSERT INTO movieInfo VALUES (?,?,?,?,?)";
		int result = 0;
		try {
			PreparedStatement preparedStatement = conn.prepareStatement(query);
			preparedStatement.setInt(1, movieInfo.getM_code());
			preparedStatement.setString(2, movieInfo.getM_title());
			preparedStatement.setString(3, movieInfo.getM_img());
			preparedStatement.setString(4, movieInfo.getM_userRating());
			preparedStatement.setString(5, movieInfo.getM_story());
			result = preparedStatement.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
		
		
	}

	public int InsertSubInfo(int movieCode, String key, String name) {
		String query;
		int result = 0;
		if(key.equals("director")) {
			query = "INSERT INTO director VALUES (DIRECTOR_SEQ.NEXTVAL, ?,?)";
			PreparedStatement preparedStatement;
			try {
				preparedStatement = conn.prepareStatement(query);
				preparedStatement.setInt(1, movieCode);
				preparedStatement.setString(2, name);
				result = preparedStatement.executeUpdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if(key.equals("actor")) {
			query = "INSERT INTO actor VALUES (ACTOR_SEQ.NEXTVAL,?,?)";
			PreparedStatement preparedStatement;
			try {
				preparedStatement = conn.prepareStatement(query);
				preparedStatement.setInt(1, movieCode);
				preparedStatement.setString(2, name);
				result = preparedStatement.executeUpdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return result;
	}

	public GetMovieInfoForm getMovieDetailInfo(int movieCode) {
		String query = "SELECT * FROM movieInfo WHERE m_code=?";
		try {
			PreparedStatement preparedStatement = conn.prepareStatement(query);
			preparedStatement.setInt(1, movieCode);
			ResultSet rs = preparedStatement.executeQuery();
			
			while(rs.next()) {
				getMovieInfoForm.setM_code(rs.getInt("m_code"));
				getMovieInfoForm.setM_title(rs.getString("m_title"));
				getMovieInfoForm.setM_img(rs.getString("m_img"));
				getMovieInfoForm.setM_userRating(rs.getFloat("m_userRating"));
				getMovieInfoForm.setM_story(rs.getString("m_story"));
			}
			getMovieInfoForm.setActor(getActorInfo(movieCode));
			getMovieInfoForm.setDirector(getDirectorInfo(movieCode));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return getMovieInfoForm;
	}

	private List getDirectorInfo(int movieCode) {
		String query = "SELECT d_name FROM director WHERE m_code=?";
		List<String> directors = new ArrayList<String>();
		try {
			PreparedStatement preparedStatement = conn.prepareStatement(query);
			preparedStatement.setInt(1, movieCode);
			ResultSet rs = preparedStatement.executeQuery();
			
			while(rs.next()) {
				String name = rs.getString("d_name");
				directors.add(name);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return directors;
	}

	private List getActorInfo(int movieCode) {
		String query = "SELECT a_name FROM actor WHERE m_code=?";
		List<String> actors = new ArrayList<String>();
		try {
			PreparedStatement preparedStatement = conn.prepareStatement(query);
			preparedStatement.setInt(1, movieCode);
			ResultSet rs = preparedStatement.executeQuery();
			
			while(rs.next()) {
				String name = rs.getString("a_name");
				actors.add(name);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return actors;
	}

	public boolean isExist(int movieCode) {
		String query = "SELECT * FROM movieInfo WHERE m_code=?";
		ResultSet rs;
		boolean result = false;
		try {
			PreparedStatement preparedStatement = conn.prepareStatement(query);
			preparedStatement.setInt(1, movieCode);
			rs = preparedStatement.executeQuery();

			if(rs.next()) {
				result = true;
			} 
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
}
