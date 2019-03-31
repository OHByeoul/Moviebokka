package com.revizio.moviebokka.controller;

public enum Route {
	SEARCH_MOVIENAME("searchMovieName","searchMoviePage.jsp"),
	CREATE_REVIEW("createReview","reviewCreateForm.jsp"),
	GET_MOVIE_INFOES("getMovieInfoes","json"),
	GET_MOVIE_INFO("getMovieInfo","movieDetailInfoPage.jsp"),
	NONE("main","main.jsp");
	
	final static String PATH = "/view/";
	final static String JSON_FORMMAT = "/view/json";
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
