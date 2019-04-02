package com.revizio.moviebokka.controller;

public enum Route {
	//Movie
	SEARCH_MOVIENAME("searchMovieName","searchMoviePage.jsp"),
	CREATE_REVIEW("createReview","reviewCreateForm.jsp"),
	GET_MOVIE_INFOES("getMovieInfoes","json"),
	GET_MOVIE_INFO("getMovieInfo","movieDetailInfoPage.jsp"),
	
	//Board
	CREATE_BOARD("createBoard","boardCreateForm.jsp"),
	GET_FREEBOARD_LIST("getFreeboardList","freeboardListPage.jsp"),
	PAGING("pagingBoard","freeboardListPage.jsp"),
	MOVE_NEXT("pagingNext","freeboardListPage.jsp"),
	MOVE_PREV("pagingPrev","freeboardListPage.jsp"),
	
	NONE("main","main.jsp");
	
	final static String PATH = "/view/";
	final static String AJAX_FORMMAT = "/view/ajax";
	private String route;
	private String view;
	
	private Route(String route, String view) {
		this.route = route;
		this.view = PATH+view;
	}
	public String getRoute() {
		return route;
	}

	public void setRoute(String route) {
		this.route = route;
	}
	
	public String getView() {
		return view;
	}
	public void setView(String view) {
		this.view = view;
	}

	public static String getDispathcerRoute(String route) {
		for(Route r : Route.values()) {
			if(route.equals(r.getRoute())) {
				return r.getRoute();
			}
		}
		return NONE.getRoute();
	}
	
	public static String getViewResolver(String route) {
		for(Route r : Route.values()) {
			if(route.equals(r.getRoute())) {
				return r.getView();
			}
		}
		return NONE.getView();
	}
}