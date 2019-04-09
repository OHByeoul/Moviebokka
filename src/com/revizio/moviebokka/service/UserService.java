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

	private void sendAuthenticateMail(String id) {
		System.out.println("iiiiiiiiidd2 "+id);
		MailManager mailManager = new MailManager();
		if(true) { //이메일 인증이 되었다면
			String mail = userDAO.getSelectedUserEmail(id);
			System.out.println("mail "+mail);
			mailManager.sendMailUsingTLS("suriessay@naver.com");
			System.out.println("메일보냄");
		}
	}

	private boolean isEmailConfirmed(String id) {
		return userDAO.getIsEmailConfirmed(id);
	}
	
	//TODO : 알아서 하셍 !!
}
