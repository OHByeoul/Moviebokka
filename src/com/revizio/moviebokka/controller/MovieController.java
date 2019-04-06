package com.revizio.moviebokka.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.revizio.moviebokka.constant.Constants;


/**
 * Servlet implementation class MovieController
 */
@WebServlet(urlPatterns = {"/movie/*","/"})
public class MovieController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MovieRequestMapping movieRequestMapping;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MovieController() {
        super();
        movieRequestMapping = new MovieRequestMapping();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		requestMapper(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("doPost");
		requestMapper(request,response);
	}

	/**
	 * @see HttpServlet#doPut(HttpServletRequest, HttpServletResponse)
	 */
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		requestMapper(request,response);
	}

	/**
	 * @see HttpServlet#doDelete(HttpServletRequest, HttpServletResponse)
	 */
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		requestMapper(request,response);
	}

	private void requestMapper(HttpServletRequest request, HttpServletResponse response) {
		String contextPath = request.getContextPath();
		String getUri = request.getRequestURI().substring(contextPath.length());
		String [] route = getUri.split("/"); 
		
		String getView = "";
			
		if(route.length<Constants.THIRD) {
			if(route.length<=Constants.FIRST) {
				movieRequestMapping.dispatcherRoute(route[Constants.ZERO],request,response);
				getView = Route.getViewResolver(route[Constants.ZERO]);
			} else {
				movieRequestMapping.dispatcherRoute(route[Constants.FIRST],request,response);
				getView = Route.getViewResolver(route[Constants.FIRST]);
			}
		} else {
			movieRequestMapping.dispatcherRoute(route[Constants.SECOND],request,response);
			getView = Route.getViewResolver(route[Constants.SECOND]);
		}

		if(!getView.equals(Route.AJAX_FORMMAT)) {			
			try {
				request.getRequestDispatcher(getView).forward(request, response);
			} catch (ServletException | IOException e) {
				e.printStackTrace();
			}
		}
	}

}
