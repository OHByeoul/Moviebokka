package com.revizio.moviebokka.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.revizio.moviebokka.constant.Constant;


/**
 * Servlet implementation class MovieController
 */
@WebServlet("/movie/*")
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
		String contextPath = request.getContextPath();
		String getUri = request.getRequestURI().substring(contextPath.length());
		String [] route = getUri.split("/"); 
		System.out.println(route[Constant.FIRST]+" "+route[Constant.SECOND]);
		
		String getView = "";
		if(route.length<Constant.THIRD) {
			movieRequestMapping.dispatcherRoute(route[Constant.FIRST],request,response);
			getView = Route.getViewResolver(route[Constant.FIRST]);
		} else {
			movieRequestMapping.dispatcherRoute(route[Constant.SECOND],request,response);
			System.out.println("route : "+Route.getDispathcerRoute(route[Constant.SECOND]));
			System.out.println("view : "+Route.getViewResolver(route[Constant.SECOND]));		
			getView = Route.getViewResolver(route[Constant.SECOND]);
		}

		if(!getView.equals(Route.JSON_FORMMAT)) {			
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
