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

	public boolean isAuthenticateCheck(String id, String password) {
		String query = "SELECT * FROM member WHERE mem_email=? AND mem_pass = ?";
		conn = instance.getConnection();
		boolean result = false;
		
		try {
			preparedStatement = conn.prepareStatement(query);
			preparedStatement.setString(1, id);
			preparedStatement.setString(2, password);
			rs = preparedStatement.executeQuery();
			
			while(rs.next()) {
				result = true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
	}

	//회원 삭제 시 비밀번호 입력받아 비교
	public boolean deletePasswordCheck(String email, String password) {
		// TODO Auto-generated method stub
		String sql = "SELECT mem_pass FROM member WHERE mem_email=?" ;
		conn = instance.getConnection();
		boolean result = false;
		
		try {
			preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setString(1, email);
			rs = preparedStatement.executeQuery();
			
			while(rs.next()) {
				String dbPass = rs.getString(1);
				
				if(dbPass.equals(password)) {
					result = true;
				}
				
			}
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			closeIdleConnection();
		}
		return result;
	}

	//비밀번호 같으면 삭제 수행
	public boolean deleteUser(String email) {
		// TODO Auto-generated method stub
		String sql = "DELETE member WHERE mem_email=?";
		conn = instance.getConnection();
		boolean result = false;
		
		try {
			preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setString(1, email);
			
			if(preparedStatement.executeUpdate() > 0) {
				result = true;
			};
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			closeIdleConnection();
		}
		return result;
	}

	public boolean editUser(String email, String password, String nickname) {
		// TODO Auto-generated method stub
		String sql = "UPDATE member SET mem_pass=?, mem_nick=? WHERE mem_email=?";
		conn = instance.getConnection();
		boolean result = false;
		try {
			preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setString(1, password);
			preparedStatement.setString(2, nickname);
			preparedStatement.setString(3, email);
			
			if(preparedStatement.executeUpdate() > 0) {
				result = true;
			}
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			closeIdleConnection();
		}
		return result;
	}


	
	public Member loginUserCheck(String email, String password) {
		// TODO Auto-generated method stub
		String sql = "SELECT * FROM member WHERE mem_email=? AND mem_pass=?";
		conn = instance.getConnection();
		Member member = new Member();
		
		try {
			preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setString(1, email);
			preparedStatement.setString(2, password);
			rs = preparedStatement.executeQuery();
			
			if(rs.next()) {
				member.setMem_id(rs.getInt(1));
				member.setMem_nick(rs.getString(2));
				member.setMem_email(rs.getString(3));
				member.setMem_pass(rs.getString(4));
				member.setMem_regdate(rs.getDate(7));
			}
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			closeIdleConnection();
		}
		
		return member;
	}

	public Member loadUserInfo(String email) {
		// TODO Auto-generated method stub
		String sql = "SELECT * FROM member WHERE mem_email=?";
		conn = instance.getConnection();
		Member member = new Member();
		
		try {
			preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setString(1, email);
			rs = preparedStatement.executeQuery();
			if(rs.next()) {
				member.setMem_id(rs.getInt(1));
				member.setMem_nick(rs.getString(2));
				member.setMem_email(rs.getString(3));
				member.setMem_pass(rs.getString(4));
				member.setMem_auth(rs.getInt(5));
				member.setMem_pic(rs.getString(6));
				member.setMem_regdate(rs.getDate(7));
				member.setMem_ip(rs.getString(8));
			}
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			closeIdleConnection();
		}
		return member;
	}

	public boolean updateUserPicture(String email, String finalPath) {
		// TODO Auto-generated method stub
		String sql = "UPDATE member SET mem_pic=? WHERE mem_email=?";
		conn = instance.getConnection();
		boolean result = false;
		try {
			preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setString(1, finalPath);
			preparedStatement.setString(2, email);
			
			if(preparedStatement.executeUpdate() > 0) {
				result = true;
			}
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			closeIdleConnection();
		}
		return result;
	}

	public boolean deletePicture(String email) {
		// TODO Auto-generated method stub
		String sql = "UPDATE member SET mem_pic='../static/images/user/default.jpg' WHERE mem_email=?";
		conn = instance.getConnection();
		boolean result = false;
		
		try {
			preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setString(1, email);
				
			if(preparedStatement.executeUpdate() > 0){
				result = true;
			}
			
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			closeIdleConnection();
		}
		
		return result;
	}

	public boolean userJoin(Member member) {
		// TODO Auto-generated method stub
		String sql = "INSERT INTO member VALUES(mem_seq.nextval, ?, ?, ?, ?, '../static/images/user/default.jpg', SYSDATE, ?)";
		conn = instance.getConnection();
		boolean result = false;
		try {
			preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setString(1, member.getMem_nick());
			preparedStatement.setString(2, member.getMem_email());
			preparedStatement.setString(3, member.getMem_pass());
			preparedStatement.setInt(4, member.getMem_auth());
			preparedStatement.setString(5, member.getMem_ip());
			
			if(preparedStatement.executeUpdate() > 0) {
				result = true;
			}
		}catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeIdleConnection();
		}
		return result;
	}

	public String getUserEmail(String email) {
		// TODO Auto-generated method stub
		String sql = "SELECT mem_email FROM member WHERE mem_email=?";
		conn = instance.getConnection();
		
		try {
			preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setString(1, email);
			rs = preparedStatement.executeQuery();
			
			while(rs.next()) {
				return rs.getString("mem_email");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeIdleConnection();
		}
		return null;
	}

	public boolean updateUserEmailAuthenticate(String email) {
		// TODO Auto-generated method stub
		String sql = "UPDATE member SET mem_auth = 1 WHERE mem_email=?";
		conn = instance.getConnection();
		boolean result = false;
		
		try {
			preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setString(1, email);
			
			if(preparedStatement.executeUpdate() > 0) {
				result = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeIdleConnection();
		}
		
		return result;
	}

	public boolean searchExistingEmail(String email) {
		// TODO Auto-generated method stub
		String sql = "SELECT mem_email FROM member WHERE mem_email=?";
		boolean result = false;	//있으면 true, 없 false
		conn = instance.getConnection();
		
		try {
			preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setString(1, email);
			rs = preparedStatement.executeQuery();
			
			if(rs.next()) {
				result = true;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeIdleConnection();
		}
		

		return result;
	}

	public boolean searchExistingNickname(String nickname) {
		// TODO Auto-generated method stub
		String sql = "SELECT mem_nick FROM member WHERE mem_nick=?";
		boolean result = false;	//있으면 true, 없 false
		conn = instance.getConnection();
		
		try {
			preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setString(1, nickname);
			rs = preparedStatement.executeQuery();
			
			if(rs.next()) {
				result = true;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeIdleConnection();
		}

		return result;
	}

	public void updateTempPassowrd(String email, String hashPassword) {
		// TODO Auto-generated method stub
		String sql = "UPDATE member SET mem_pass=? WHERE mem_email=?";
		conn = instance.getConnection();
		
		try {
			preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setString(1, hashPassword);
			preparedStatement.setString(2, email);
			preparedStatement.executeUpdate();
		}catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeIdleConnection();
		}
	}

	

	

}
