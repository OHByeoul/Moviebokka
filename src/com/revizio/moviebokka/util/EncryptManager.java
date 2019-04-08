package com.revizio.moviebokka.util;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class EncryptManager {
	public static String encryptString(String input) {
		String salt = "sea salt";
		try {
			MessageDigest messageDigest = MessageDigest.getInstance("SHA-512");
			messageDigest.update(salt.getBytes());
			byte[] digest = messageDigest.digest(input.getBytes());
			BigInteger no = new BigInteger(1, digest);
			String hashtext = no.toString(16);
			System.out.println(hashtext);
			while(hashtext.length() < 32) {
				hashtext = "0"+hashtext;
			}
			return hashtext;
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return salt;
	}
}
