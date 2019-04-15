package com.revizio.moviebokka.controller;

public enum Route {
	//User
	JOIN("join","tempUserJoinForm.jsp"),
	JOIN_ACTION("joinAction","mainPage.jsp"),
	JOIN_AUTHENTICATE("emailAuthenticate","mainPage.jsp"),
	LOGIN("login","temploginForm.jsp"),
	CHECK_AUTHENTICATE("isAuthenticate","mainPage.jsp"),
	GET_MYPAGE("mypage","mypagePage.jsp"),
	EDIT_MYPAGE("",""),
	
	//Movie
	SEARCH_MOVIENAME("searchMovieName","/movie/"+"searchMoviePage.jsp"),
	SEARCH_DETAIL_INFO("searchDetailInfo","searchPage.jsp"),
	GET_MOVIE_SEARCH("searchMovies", "searchPage.jsp"),
	GET_MOVIE_INFOES("getMovieInfoes","ajax"),
	GET_MOVIE_INFO("getMovieInfo","/movie/"+"movieDetailInfoPage.jsp"),
	GET_MOVIE_DETAIL("getMovieDetail","/movie/"+"movieDetailInfoPage.jsp"),
	GET_MOVIE_MAIN("main","mainPage.jsp"),
	GET_MAIN("/","mainPage.jsp"),
	
	//Search
	GET_SEARCH_ALL("searchContents","searchPage.jsp"),
	GET_MOVIE_MORE("searchMovieMore", "ajax"),
	GET_REVIEW_MORE("searchReviewMore","ajax"),
	
	//Review
	GET_REVIEW_SEARCH("searchReviews","searchPage.jsp"),//GET_REVIEW_SEARCH("searchReviews","ajax"),
//	GET_REVIEW_MORE("searchReviewMore","ajax"),
	REVIEW_FORM("reviewForm","/review/"+"reviewCreateForm.jsp"),
	CREATE_REVIEW("createReview","/review/"+"reviewDetailPage.jsp"),
	GET_REVIEW_INFO("getSelectedReview","/review/"+"reviewDetailPage.jsp"),
	UPDATE_REVIEW_FORM("updateReviewForm", "/review/"+"reviewUpdateForm.jsp"),
	UPDATE_REVIEW_PAGE("updateReview","/review/"+"reviewDetailPage.jsp"),
	DELETE_REVIEW_PAGE("deleteReview", "/movie/"+"movieDetailInfoPage.jsp"),
	
	
	//Board
	CREATE_BOARD("createBoard","boardCreateForm.jsp"),
	GET_FREEBOARD_LIST("getFreeboardList","freeboardListPage.jsp"),
	//Paging
	PAGING("pagingBoard","freeboardListPage.jsp"),
	MOVE_NEXT("pagingNext","freeboardListPage.jsp"),
	MOVE_PREV("pagingPrev","freeboardListPage.jsp"),
	
	NONE("main","mainPage.jsp");
	
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
