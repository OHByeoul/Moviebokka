package com.revizio.moviebokka.util;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import java.util.Properties;

public class MailManager{

		final String username = "test471471@gmail.com";
		final String password = "201904201904";
	
		public void sendMailUsingTLS(String toEmail) {
			Properties props = new Properties();
			props.put("mail.smtp.auth", "true");
			props.put("mail.smtp.starttls.enable", "true");
			props.put("mail.smtp.host", "smtp.gmail.com");
			props.put("mail.smtp.port", "587");

			Session session = Session.getInstance(props,
			  new javax.mail.Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(username, password);
				}
			  });

			try {

				Message message = new MimeMessage(session);
				message.setFrom(new InternetAddress(username));
				message.setRecipients(Message.RecipientType.TO,
					InternetAddress.parse(toEmail));
				message.setSubject("무비보까 회원 인증 메일입니다.");
				String content = "<a href='http://localhost:8090/Moviebokka/user/emailAuthenticate?email="+toEmail+"'>무비보까 회원 인증</a>";
				message.setContent(content, "text/html; charset=UTF-8");				

				Transport.send(message);

				System.out.println("Done");

			} catch (MessagingException e) {
				throw new RuntimeException(e);
			}
		}

		
		public void sendTempPassword(String email, String tempPassword) {
			Properties props = new Properties();
			props.put("mail.smtp.auth", "true");
			props.put("mail.smtp.starttls.enable", "true");
			props.put("mail.smtp.host", "smtp.gmail.com");
			props.put("mail.smtp.port", "587");
			
			Session session = Session.getInstance(props,
					new javax.mail.Authenticator() {
						protected PasswordAuthentication getPasswordAuthentication() {
							return new PasswordAuthentication(username, password);
						}
					  });
			
			try {

				Message message = new MimeMessage(session);
				message.setFrom(new InternetAddress(username));
				message.setRecipients(Message.RecipientType.TO,
					InternetAddress.parse(email));
				message.setSubject("무비보까 이메일 변경 메일입니다.");
				String content = "임시 비밀번호는 " + tempPassword + "입니다.";
				message.setContent(content, "text/html; charset=UTF-8");				

				Transport.send(message);

			} catch (MessagingException e) {
				throw new RuntimeException(e);
			}
			
		}
		

}
