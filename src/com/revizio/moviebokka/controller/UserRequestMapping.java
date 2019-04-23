package com.revizio.moviebokka.controller;

import java.io.IOException;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.revizio.moviebokka.dao.UserDAO;
import com.revizio.moviebokka.dto.Member;
import com.revizio.moviebokka.service.UserService;

public class UserRequestMapping implements RequestDispatcher{
	private UserService userService;
	
	public UserRequestMapping() {
		userService = new UserService();
	}
	@Override
	public void dispatcherRoute(String route, HttpServletRequest request, HttpServletResponse response) throws IOException {
		request.setCharacterEncoding("utf-8");
			if(route.equals(Route.JOIN.getRoute())) {	//회원가입 폼
			
			} else if(route.equals(Route.JOIN_ACTION.getRoute())) {	//회원가입 수행
				
				String email = request.getParameter("email");
				String password = request.getParameter("password");
				String nickname = request.getParameter("nickname");
				boolean result = userService.userJoin(request, email, password, nickname);
				
				request.setAttribute("result", result);
				
			} else if(route.equals(Route.JOIN_AUTHENTICATE.getRoute())) {	//회원가입 이메일 인증
				//MailManager -> content 수정 해줄 것
				String email = request.getParameter("email");
				
				userService.updateUserEmailAuthenticate(email);
				
			} else if(route.equals(Route.LOGIN.getRoute())) {	//로그인
				
			} else if(route.equals(Route.GET_MYPAGE.getRoute())) {	//마이페이지
				HttpSession session = request.getSession();
				Member sessionInfo = (Member)session.getAttribute("user");
				String email = sessionInfo.getMem_email();
				Member memberInfo = userService.userInfo(email);
				
				request.setAttribute("email", memberInfo.getMem_email());
				request.setAttribute("nickname", memberInfo.getMem_nick());
				request.setAttribute("regDate", memberInfo.getMem_regdate());
				request.setAttribute("picture", memberInfo.getMem_pic());
	
			} else if(route.equals(Route.EDIT_USER.getRoute())) {	//회원정보 수정 
				HttpSession session = request.getSession();
				Member sessionInfo = (Member)session.getAttribute("user");
				String email = sessionInfo.getMem_email();
				Member memberInfo = userService.userInfo(email);
				
				request.setAttribute("nickname", memberInfo.getMem_nick());
				request.setAttribute("picture", memberInfo.getMem_pic());
				
			} else if(route.equals(Route.EDIT_USER_ACTION.getRoute())) { //수정 실행
				HttpSession session = request.getSession();
				Member member = (Member)session.getAttribute("user");
				String email = member.getMem_email();
				String password = request.getParameter("password");
				String nickname = request.getParameter("nickname");
				boolean result = userService.editUser(request, email, password, nickname);
				
				request.setAttribute("result", result);
			
			} else if(route.equals(Route.USER_DELETE_FORM.getRoute())) { //회원 탈퇴   ★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★ 끝
				HttpSession session = request.getSession();
				Member member = (Member)session.getAttribute("user");
				String email = member.getMem_email();
				
				request.setAttribute("email", email);
			} else if(route.equals(Route.USER_DELETE_ACTION.getRoute())) { 	//삭제 수행 ★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★ 끝
				String pass = request.getParameter("pass");
				String email = request.getParameter("email");
				boolean result = userService.deleteUser(request, email, pass);
				
				request.setAttribute("result", result);

			} else if(route.equals(Route.CHECK_AUTHENTICATE.getRoute())) {
				String id = request.getParameter("id");
				String password = request.getParameter("password");
				boolean result = userService.createSession(request,id,password);
				
				request.setAttribute("id", id);
				request.setAttribute("result",result);
	
			} else if(route.equals(Route.LOGIN_CHECK.getRoute())){	//로그인
				String email = request.getParameter("email");
				String password = request.getParameter("password"); 
				boolean result = userService.userCheck(request, email, password);
		
				if(result) {
					userService.createSessionTest(request, email);
					HttpSession session = request.getSession();
					Member member = (Member)session.getAttribute("user");
					request.setAttribute("nickname", member.getMem_nick());
				}
			
				request.setAttribute("result", result);
				
			} else if(route.equals(Route.USER_PICTURE_INSERT.getRoute())){	//사진 저장 폼
				HttpSession session = request.getSession();
				Member member = (Member)session.getAttribute("user");
				String email = member.getMem_email();
				
				request.setAttribute("email", email);
				
			} else if(route.equals(Route.SAVE_PICTURE.getRoute())){		//사진 저장 실행
				HttpSession session = request.getSession();
				Member member = (Member)session.getAttribute("user");
				String email = member.getMem_email();
				boolean result = userService.savePicture(request, email);
			
				request.setAttribute("result", result);
				
			} else if(route.equals(Route.DELETE_PICTURE.getRoute())){	//사진 삭제 버튼 누르면 사진 삭제 실행
				HttpSession session = request.getSession();
				Member member = (Member)session.getAttribute("user");
				String email = member.getMem_email();
				Member memInfo = userService.userInfo(email);
				String picture = memInfo.getMem_pic();
				boolean result = userService.deletePicture(request, email, picture);
				
				request.setAttribute("result", result);
			} else if(route.equals(Route.CHECK_EMAIL.getRoute())){	//이메일 중복 확인
				String email = request.getParameter("email");
				boolean result = userService.checkExistEmail(request, email);
				
				request.setAttribute("result", result);
				
			} else if(route.equals(Route.CHECK_NICKNAME.getRoute())){	//닉네임 중복 확인
				String nickname = request.getParameter("nickname");
				boolean result = userService.chekExistNickname(request, nickname);
				
				request.setAttribute("result", result);
			} else if(route.equals(Route.FIND_PASSWORD.getRoute())){	//비밀번호 찾기
		
				
			} else if(route.equals(Route.FIND_PASSWORD_CHECK.getRoute())){	//비밀번호 찾기
				String email = request.getParameter("email");
				boolean result = userService.checkExistEmail(request, email);
				
				if(result) {
					userService.sendTempPasswordMail(email);
				}
				
				request.setAttribute("result", result);
			}
			
	}

}
