package com.revizio.moviebokka.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.revizio.moviebokka.dao.UserDAO;

public class UserService {
	private UserDAO userDAO;
	private HttpSession session;
	
	public UserService() {
		userDAO = UserDAO.getInstance();
	}


	public boolean createSession(HttpServletRequest request,String id, String password) {
		if(isAthenticate(id, password)) {
			session = request.getSession();
			session.setAttribute("name", "sessionnnn");
			String se = (String) session.getAttribute("name");
			System.out.println("sessionId "+se);
			System.out.println("createSession");
			return true;
		} else {
			System.out.println("session 망함");
			return false;
		}
	}
	
	private boolean isAthenticate(String id, String password) {
		boolean result =  userDAO.isAthenticate(id,password);
		System.out.println("계졍 권한 체크");
		System.out.println(result);
		return result;
	}
	
	//TODO : 알아서 하셍 !!
}
