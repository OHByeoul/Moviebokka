package com.revizio.moviebokka.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.revizio.moviebokka.dao.UserDAO;
import com.revizio.moviebokka.dto.Member;
import com.revizio.moviebokka.dto.Member1;
import com.revizio.moviebokka.util.EncryptManager;
import com.revizio.moviebokka.util.MailManager;

public class UserService {
	private UserDAO userDAO;
	private HttpSession session;
	private Member1 member;
	
	public UserService() {
		userDAO = UserDAO.getInstance();
	}

	public boolean createSession(HttpServletRequest request,String id, String password) {
		if(isAthenticate(id, password)) {
			session = request.getSession();
			session.setAttribute("user", member);
			Member1 member =  (Member1) session.getAttribute("user");
			//System.out.println(member.getMem_id()+" "+member.getMem_email()+" "+member.getMem_pass());
			return true;
		} else {
			System.out.println("session 망함");
			return false;
		}
	}
	
	private boolean isAthenticate(String id, String password) {
		member =  userDAO.isAthenticate(id,password);
		boolean result = false;
		if(member != null) {
			result = true;
		}
		System.out.println("isAthenticate "+result);
		return result;
	}
	
	public String getClientIP(HttpServletRequest request) {
        String ip = request.getHeader("X-FORWARDED-FOR"); 
        
        if (ip == null || ip.length() == 0) {
            ip = request.getHeader("Proxy-Client-IP");
        }

        if (ip == null || ip.length() == 0) {
            ip = request.getHeader("WL-Proxy-Client-IP");  // 웹로직
        }

        if (ip == null || ip.length() == 0) {
            ip = request.getRemoteAddr() ;
        }
        return ip;
    }

	public void userJoin(HttpServletRequest request, String id, String password) {
		String hashPassword = EncryptManager.encryptString(password);
		Member1 member1 = new Member1(id, password, hashPassword, 0);
		int result = userDAO.userJoin(member1);
		if(result==1) {
			System.out.println("회원가입");
			createSession(request,id,password);
			sendAuthenticateMail(id);
		} else {
			System.out.println("응 꺼져");
		}
	}
	//TODO : 1. 메일 보낸 후에 메인페이지로 이동하지 않고 (이메일 인증 후 이용해주세요 뭐 이런 페이지로 이동시켜야됨 현재는 mainPage.jsp)
	// 2. 인증 누른 후에 (이메일 인증이 되었습니다. 라고 메세지를 주던지 아니면 페이지로 이동시켜야됨 mainPage.jsp)
	private void sendAuthenticateMail(String email) {
		System.out.println(email);
		MailManager mailManager = new MailManager();
		mailManager.sendMailUsingTLS(email);
	}

	public void updateUserEmailAuthenticate(String email) {
		String confirmedMail = userDAO.getUserEmail(email);
		if(email.equals(confirmedMail)) {
			userDAO.updateUserEmailAuthenticate(email);			
			System.out.println("이메일 인증이 완료됬당");
		} else {
			System.out.println("인증 실패");
		}
	}
	
	
}
