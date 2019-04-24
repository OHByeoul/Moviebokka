package com.revizio.moviebokka.service;

import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.revizio.moviebokka.dao.UserDAO;
import com.revizio.moviebokka.dto.Member;
import com.revizio.moviebokka.util.EncryptManager;
import com.revizio.moviebokka.util.MailManager;

public class UserService {
	private UserDAO userDAO;
	private HttpSession session;
	private Member member;

	public UserService() {
		userDAO = UserDAO.getInstance();
	}

	//로그인 세션 받아오기
	public boolean createSession(HttpServletRequest request, String id, String password) {
		boolean result = false;
		String hashPassword = EncryptManager.encryptString(password);
		if(isAuthenticate(id, hashPassword)) {
			session = request.getSession();
			session.setAttribute("name", id);
		
			String name = session.getAttribute("name").toString();
			System.out.println(name);
	
			result = true;
		}
		return result;
	}
	
	private boolean isAuthenticate(String id, String password) {
		
		return userDAO.isAuthenticateCheck(id, password);
	}

	//회원 삭제
	public boolean deleteUser(HttpServletRequest request, String email, String password) {
		// TODO Auto-generated method stub
		boolean result = false; 
		String hashPassword = EncryptManager.encryptString(password);
		
		if(checkUserEmailPass(email, hashPassword)) {
			userDAO.deleteUser(email);
			HttpSession session = request.getSession();
			session.invalidate();
			result = true;
		}
		return result;
	}
	
	//삭제할 아이디 비번 확인
	private boolean checkUserEmailPass(String email, String password) {
		// TODO Auto-generated method stub
		
		return userDAO.deletePasswordCheck(email, password);
	}

	
	//회원 수정  -------- 이게 왜 되는거지?
	public boolean editUser(HttpServletRequest request, String email, String password, String nickname) throws IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		boolean result = false;
		System.out.println(nickname);
		String hashPassword = EncryptManager.encryptString(password);
		
		if(userDAO.editUser(email, hashPassword, nickname)) {
			result = true;
		}
		return result;
	}
	
	//로그인 할 때 아이디 비밀번호 확인
	public boolean userCheck(HttpServletRequest request, String email, String password) {
		// TODO Auto-generated method stub
		boolean result = false;
		String hashPassword = EncryptManager.encryptString(password);

		member = userDAO.loginUserCheck(email, hashPassword);
		
		if(member.getMem_pass() == null) {
			result = false;
		}else if(member.getMem_pass().equals(hashPassword)) {
			result = true;
		}

		return result;
	}



	public boolean createSessionTest(HttpServletRequest request, String email) {
		// TODO Auto-generated method stub
		boolean result = false;
		
		session = request.getSession();
		session.setAttribute("user", member);
			
		Member member = (Member)session.getAttribute("user");
		
		if(member != null) {
			result = true;
		}
		return result;
	}
	
	//유저 정보 불러오기
	public Member userInfo(String email) {
		// TODO Auto-generated method stub
		Member member = userDAO.loadUserInfo(email);

		return member;
	}
	//사진 저장
	public boolean savePicture(HttpServletRequest request, String email) throws IOException {
		boolean result = false;
		int maxSize = 10 * 1024 * 1024;
		String path = "C:/Users/Byeoul/eclipse-workspace/Moviebokka/WebContent/static/images/user";
		MultipartRequest multi = new MultipartRequest(request, path, maxSize, "utf-8", new DefaultFileRenamePolicy());
		Enumeration en = multi.getFileNames();
		
		//input 태그의 속성이 file인 태그의 name 속성값: 파라미터 이름
		String filename1 = (String)en.nextElement();
		//서버에 저장된 파일 이름
		String filename = multi.getFilesystemName(filename1);
		
		//확장자 추출
		String filePath = path + "/" + filename;
		int pos = filePath.lastIndexOf(".");
		String fileExt = filePath.substring(pos+1);
		
		//파일 이름 변경
		File uploadPicture = new File(path + "/" + filename);
		File userPicture = new File(path + "/" + email + "." + fileExt);

		//기존 프로필 있을경우 삭제
		if(userPicture.exists()) {
			userPicture.delete();
		}
		//저장된 파일 이름 변경 실행
		uploadPicture.renameTo(userPicture);
		
		String finalPath = "../static/images/user/" + email + "." +fileExt;
		if(userDAO.updateUserPicture(email, finalPath)) {
			result = true;
		}
		return result;
	}
	
	//사진 삭제
	public boolean deletePicture(HttpServletRequest request, String email, String picture) throws IOException {
		// TODO Auto-generated method stub
		boolean result = false; 
		String path = "/Moviebokka/static/images/user/";
		
		if(userDAO.deletePicture(email)) {
			//확장자 추출
			try {
				int pos = picture.lastIndexOf(".");
				String fileExt = picture.substring(pos+1);
				File userPicture = new File(path + "/" + email + "." + fileExt);
				userPicture.delete();
			}catch (Exception e) {
				// TODO: handle exception
				return false;
			}

			result = true;
		}
		return result;
	}

	//회원가입
	public boolean userJoin(HttpServletRequest request, String email, String password, String nickname) throws IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		
		String hashPassword = EncryptManager.encryptString(password);
		InetAddress local = InetAddress.getLocalHost();
		String ip = local.getHostAddress();

	    
	    Member member = new Member(email, hashPassword, nickname, 0, ip);
		boolean result = false;
		
		if(userDAO.userJoin(member)) {
			System.out.println("회원가입");
			createSessionTest(request,email);
			sendAuthenticateMail(email);
			result = true;
		}else {
			System.out.println("ㅃㅇ~");
		}
		return result;
	}

	//TODO : 1. 메일 보낸 후에 메인페이지로 이동하지 않고 (이메일 인증 후 이용해주세요 뭐 이런 페이지로 이동시켜야됨 현재는 mainPage.jsp)
	// 2. 인증 누른 후에 (이메일 인증이 되었습니다. 라고 메세지를 주던지 아니면 페이지로 이동시켜야됨 mainPage.jsp)
	private void sendAuthenticateMail(String email) {
		// TODO Auto-generated method stub
		System.out.println("email");
		MailManager mailManager = new MailManager();
		mailManager.sendMailUsingTLS(email);
		
	}
	
	public void updateUserEmailAuthenticate(String email) {
		String confirmedMail = userDAO.getUserEmail(email);
		
		if(email.equals(confirmedMail)) {
			userDAO.updateUserEmailAuthenticate(email);
			System.out.println("이메일 인증이 완료됬당");
		}else {
			System.out.println("인증 실패");
		}
	}

	public boolean checkExistEmail(HttpServletRequest request, String email) {
		// TODO Auto-generated method stub
		boolean result = false;

		if(userDAO.searchExistingEmail(email)) {
			result = true;
		}

		return result;
	}

	public boolean chekExistNickname(HttpServletRequest request, String nickname) {
		// TODO Auto-generated method stub
		boolean result = false;

		if(userDAO.searchExistingNickname(nickname)) {
			result = true;
		}

		return result;
	}
	
	
	/*public Member userInfo(String email) {
		// TODO Auto-generated method stub
		Member member = userDAO.loadUserInfo(email);

		return member;
	}*/
	
	//임시 비밀번호로 변경하고 이메일 전송
	public void sendTempPasswordMail(String email) {
		String tempPassword = getRandomPassword();
		String hashPassword = EncryptManager.encryptString(tempPassword);
		
		userDAO.updateTempPassowrd(email, hashPassword);
		sendTempPasswordMail(email, tempPassword);

		
	}
	private void sendTempPasswordMail(String email, String tempPassword) {
		// TODO Auto-generated method stub
		MailManager mailManager = new MailManager();
		mailManager.sendTempPassword(email, tempPassword);
	}

	//임시 비밀번호 생성
	private String getRandomPassword() {
		// TODO Auto-generated method stub
		char[] charSet = new char[] { 
				'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 
				'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 
				'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 
				'U', 'V', 'W', 'X', 'Y', 'Z',
				'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j',
				'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't',
				'u', 'v', 'w', 'x', 'y', 'z'
			};
		int index = 0;
		StringBuffer sb = new StringBuffer();
		
		for(int i=0; i<10; i++) {
			index = (int)(charSet.length * Math.random());
			sb.append(charSet[index]);
		}
		

		return sb.toString();
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
