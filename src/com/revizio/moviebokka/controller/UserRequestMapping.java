package com.revizio.moviebokka.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revizio.moviebokka.service.UserService;

public class UserRequestMapping implements RequestDispatcher{
	private UserService userService;
	
	public UserRequestMapping() {
		userService = new UserService();
	}
	@Override
	public void dispatcherRoute(String route, HttpServletRequest request, HttpServletResponse response) {
			if(route.equals(Route.JOIN.getRoute())) {
				
			} else if(route.equals(Route.LOGIN.getRoute())) {
				
			}
	}

}
