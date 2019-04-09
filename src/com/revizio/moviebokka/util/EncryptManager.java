package com.revizio.moviebokka.util;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import com.revizio.moviebokka.constant.Constants;

public class EncryptManager {
	public static String encryptString(String input) {
		try {
			MessageDigest messageDigest = MessageDigest.getInstance(Constants.SHA);
			messageDigest.update(Constants.SALT.getBytes());
			byte[] digest = messageDigest.digest(input.getBytes());
			BigInteger no = new BigInteger(1, digest);
			String hashtext = no.toString(Constants.HEXA_DECIMAL);
			System.out.println(hashtext);
			
			while(hashtext.length() < Constants.PADD_LIMIT) {
				hashtext = Constants.PADD+hashtext;
			}
			return hashtext;
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return null;
	}
}
