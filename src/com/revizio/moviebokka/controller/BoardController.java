package com.revizio.moviebokka.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revizio.moviebokka.constant.Constants;

/**
 * Servlet implementation class BoardController
 */
@WebServlet("/board/*")
public class BoardController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private BoardRequestMapping boardRequestMapping;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardController() {
        super();
        boardRequestMapping = new BoardRequestMapping();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String contextPath = request.getContextPath(); // Moviebokka
		String getUri = request.getRequestURI().substring(contextPath.length()); // /board/createBoard   uri : Moviebokka/..
		String [] route = getUri.split("/"); 
		
		String getView = "";
		if(route.length<Constants.THIRD) {
			try {
				boardRequestMapping.dispatcherRoute(route[Constants.FIRST],request,response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			getView = Route.getViewResolver(route[Constants.FIRST]);
		} else {
			try {
				boardRequestMapping.dispatcherRoute(route[Constants.SECOND],request,response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
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
