package com.revizio.moviebokka.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.revizio.moviebokka.dao.UserDAO;
import com.revizio.moviebokka.dto.Member;

public class UserService {
	private UserDAO userDAO;
	private HttpSession session;
	private Member member;
	
	public UserService() {
		userDAO = UserDAO.getInstance();
	}

	public boolean createSession(HttpServletRequest request,String id, String password) {
		if(isAthenticate(id, password)) {
			session = request.getSession();
			session.setAttribute("user", member);
			Member member =  (Member) session.getAttribute("user");
			System.out.println(member.getMem_id()+" "+member.getMem_email()+" "+member.getMem_pass());
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
	
	//TODO : 알아서 하셍 !!
}
