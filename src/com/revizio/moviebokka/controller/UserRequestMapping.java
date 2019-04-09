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
				
			} else if(route.equals(Route.JOIN_ACTION.getRoute())) {				
				String id = request.getParameter("id");
				String password = request.getParameter("password");
				
				userService.userJoin(request,id,password);
			} else if(route.equals(Route.JOIN_AUTHENTICATE.getRoute())) {
				String id = request.getParameter("email");
				userService.updateUserEmailAuthenticate(id);
			} else if(route.equals(Route.CHECK_AUTHENTICATE.getRoute())) {
				String id = request.getParameter("id");
				String password = request.getParameter("password");
				
				System.out.println(id+" "+password);
				boolean result = userService.createSession(request,id,password);
				System.out.println(result);
				request.setAttribute("result", result);
			}
	}

}
