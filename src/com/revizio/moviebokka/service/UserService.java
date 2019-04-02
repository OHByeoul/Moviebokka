package com.revizio.moviebokka.service;

import com.revizio.moviebokka.dao.UserDAO;

public class UserService {
	private UserDAO userDAO;
	
	public UserService() {
		userDAO = UserDAO.getInstance();
	}
	
	//TODO : 알아서 하셍 !!
}
