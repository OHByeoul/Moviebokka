package com.revizio.moviebokka.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revizio.moviebokka.constant.Constants;

/**
 * Servlet implementation class UserController
 */
@WebServlet("/user/*")
public class UserController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private UserRequestMapping userRequestMapping;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserController() {
        super();
        userRequestMapping = new UserRequestMapping();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String contextPath = request.getContextPath();
		String getUri = request.getRequestURI().substring(contextPath.length());
		String [] route = getUri.split("/");
		
		String getView = "";
		if(route.length<Constants.THIRD) {
			userRequestMapping.dispatcherRoute(route[Constants.FIRST],request,response);
			getView = Route.getViewResolver(route[Constants.FIRST]);
		} else {
			userRequestMapping.dispatcherRoute(route[Constants.SECOND],request,response);
			getView = Route.getViewResolver(route[Constants.SECOND]);
		}

		if(!getView.equals(Route.AJAX_FORMMAT)) {			
			request.getRequestDispatcher(getView).forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	/**
	 * @see HttpServlet#doPut(HttpServletRequest, HttpServletResponse)
	 */
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doDelete(HttpServletRequest, HttpServletResponse)
	 */
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
