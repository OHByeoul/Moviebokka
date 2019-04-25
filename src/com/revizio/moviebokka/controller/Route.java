package com.revizio.moviebokka.controller;

public enum Route {
	//User
		JOIN("join","/user/"+"userJoinForm.jsp"),		//회원가입
		JOIN_ACESS_TERM("joinAccessTerm", "/user/"+"joinAccessTerm.jsp"),	//이용약관
		JOIN_ACTION("joinAction", "/user/"+"userJoinPage.jsp"),	//회원가입 확인
		JOIN_AUTHENTICATE("emailAuthenticate","mainPage.jsp"),	//회원가입 이메일 인증
		CHECK_EMAIL("emailCheck","/user/"+"emailCheckPage.jsp"),		//회원가입 이메일 중복 체크
		CHECK_NICKNAME("nicknameCheck", "/user/"+"nicknameCheckPage.jsp"),	//회원가입, 회원수정 닉네임 중복 체크
		LOGIN1("login2", "/user/"+"loginPage.jsp"),				//********안씀********
		LOGIN_CHECK("loginCheck", "/user/"+"userLoginCheckPage.jsp"),	//입력받은 아이디 비밀번호 db랑 비교
		LOGIN("login","/user/"+"loginPage.jsp"),			//로그인
		GET_MYPAGE("mypage","/user/"+"mypagePage.jsp"),		//마이페이지
		EDIT_USER("edit","/user/"+"userEditForm.jsp"),		//회원정보 수정
		EDIT_USER_ACTION("editAction", "/user/"+"userEditPage.jsp"),	//수정 실행
		CHECK_AUTHENTICATE("isAuthenticate","/user/"+"tempCheckPage.jsp"), //********안씀********
		USER_DELETE_FORM("deleteForm", "/user/"+"userDelete.jsp"),	//회원 탈퇴(비밀번호 입력받는 창)
		USER_DELETE_ACTION("deleteAction", "/user/"+"userDeletePage.jsp"),	//회원탈퇴 실행
		USER_PICTURE_INSERT("insertUserPicture", "/user/"+"userPictureInsertForm.jsp"),	//사진 삽입
		SAVE_PICTURE("savePicture", "/user/"+"userPictureInsertCheckPage.jsp"),	//사진 선택해서 경로 db에 저장
		DELETE_PICTURE("deletePicture", "/user/"+"userPictureDeletePage.jsp"), //회원 수정페이지에서 사진 삭제 버튼 누를 시 사진 삭제
		FIND_PASSWORD("findPassowrd", "/user/"+"findPassword.jsp"),	
		FIND_PASSWORD_CHECK("findPassowrdCheck", "/user/"+"findPassowrdCheckPage.jsp"),
		LOGOUT("logout", "/user/"+"logoutPage.jsp"),	//로그아웃
	
	//Movie
	SEARCH_MOVIENAME("searchMovieName","/movie/"+"searchMoviePage.jsp"),
	SEARCH_DETAIL_INFO("searchDetailInfo","searchPage.jsp"),
	GET_MOVIE_SEARCH("searchMovies", "searchPage.jsp"),
	GET_MOVIE_INFOES("getMovieInfoes","ajax"),
	GET_MOVIE_INFO("getMovieInfo","/movie/"+"movieDetailInfoPage.jsp"),
	GET_MOVIE_DETAIL("getMovieDetail","/movie/"+"movieDetailInfoPage.jsp"),
	GET_MOVIE_GENRE("getMovieGenre","/movie/"+"movieGenreInfoPage.jsp"),
	GET_MOVIE_MAIN("main","mainPage.jsp"),
	GET_MAIN("/","index.jsp"),
	
	//Search
	GET_SEARCH_ALL("searchContents","searchPage.jsp"),
	GET_MOVIE_MORE("searchMovieMore", "ajax"),
	GET_REVIEW_MORE("searchReviewMore","ajax"),
	
	//Company
	COMPANY_INTRO("companyIntro","/company/"+"companyIntro.jsp"),
	COMPANY_ORG("companyOrg","/company/"+"companyOrg.jsp"),
	
	//Review
	REVIEW_FORM("reviewForm","/review/"+"reviewCreateForm.jsp"),
	CREATE_REVIEW("createReview","/review/"+"reviewDetailPage.jsp"),
	GET_REVIEW_INFO("getSelectedReview","/review/"+"reviewDetailPage.jsp"),
	UPDATE_REVIEW_FORM("updateReviewForm", "/review/"+"reviewUpdateForm.jsp"),
	UPDATE_REVIEW_PAGE("updateReview","/review/"+"reviewDetailPage.jsp"),
	DELETE_REVIEW_PAGE("deleteReview", "/movie/"+"movieDetailInfoPage.jsp"),
	GET_REVIEWlIST_MORE("getReviewMore","ajax"),
	RECOMMAND_REVIEW("recommandReview","ajax"),
	UNRECOMMAND_REVIEW("unrecommandReview","ajax"),
	CHECK_USER_RECOMMAND("checkUserRecommand","ajax"),
	
	//comment
	CREATE_COMMENT("createComment","/review/"+"reviewDetailPage.jsp"),
	UPDATE_COMMENT("updateComment","/review/"+"reviewDetailPage.jsp"),
	DELETE_COMMENT("deleteComment","ajax"),
	
	
	//Board
		BOARD_LIST("boardList","board/list.jsp"),
		BOARD_VIEW("boardView", "board/view.jsp"),
		BOARD_INSERT("boardInsert","board/insert.jsp"),
		BOARD_INSERTPRO("boardInsertPro","board/insertPro.jsp"),
		BOARD_COMMENTPRO("boardCommentPro","board/contentPro.jsp"),
		BOARD_UPDATE("boardUpdate","board/update.jsp"),
		BOARD_UPDATEPRO("boardUpdatePro","board/updatePro.jsp"),
		BOARD_DELETE("boardDelete", "board/deletePro.jsp"),
		VIEW_MY_CONTENT("myContentList", "/user/"+"myContentListPage.jsp"), 
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
