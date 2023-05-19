package db;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Arrays;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class EncryptionDecryptionMechanism {
	public static String retrieveAESString() {
		String aesKeyPath = "/home/whoami/.ssh/aes.key";
        BufferedReader reader;
		try {
			reader = new BufferedReader(new FileReader(aesKeyPath));
			StringBuilder sb = new StringBuilder();
	        String line = reader.readLine();
	        sb.append(line);
	        reader.close();
	        return sb.toString();
		} catch (IOException e) {
			e.printStackTrace();
		}
        return null;
	}
	public static String generateAESString() {
		KeyGenerator keyGen;
		try {
			keyGen = KeyGenerator.getInstance("AES");
			keyGen.init(256);
			SecretKey secretKey = keyGen.generateKey();
			String encodedKeyString = Base64.getEncoder().encodeToString(secretKey.getEncoded());
			System.out.format("generateAESString: %s\n", encodedKeyString);
			return encodedKeyString;
		} catch (GeneralSecurityException e) {
			e.printStackTrace();
		}
		return null;
	}
	public static String encryptKeyWord(String aesKeyString, String keywordString) {
		String encryptedString = null;
		try {
			byte[] decodedKey = Base64.getDecoder().decode(aesKeyString);
			SecretKey originalKey = new SecretKeySpec(decodedKey, 0, decodedKey.length, "AES"); 
			Cipher cipher = Cipher.getInstance("AES");
			cipher.init(Cipher.ENCRYPT_MODE, originalKey);
			byte[] encryptedBytes = cipher.doFinal(keywordString.getBytes());
			encryptedString = new String(encryptedBytes);
			
			System.out.format("encryptKeyWord: %s \t EncryptedString: %s\n", 
					keywordString, encryptedString);
		} catch (GeneralSecurityException e) {
			e.printStackTrace();
		}
		
		
		return encryptedString;
	}
	
	public static String decryptKeyword(String aesKeyString, String encryptedBytesString) {
		String decryptedBytesString = null;
		try {
			byte[] decodedKey = Base64.getDecoder().decode(aesKeyString);
			SecretKey originalKey = new SecretKeySpec(decodedKey, 0, decodedKey.length, "AES");
			Cipher cipher = Cipher.getInstance("AES");
			cipher.init(Cipher.DECRYPT_MODE, originalKey);
			byte[] decryptedBytes = cipher.doFinal(encryptedBytesString.getBytes());
			decryptedBytesString = new String(decryptedBytes);
			System.out.format("decryptKeyword: %s \t DecryptedString: %s\n", 
					encryptedBytesString, decryptedBytesString);
		} catch (GeneralSecurityException e) {
			e.printStackTrace();
		}
		return decryptedBytesString;
	}
	
	public static String [] buildKeyWordValueArray(String aesKeyString, String keywords) {
		String[] splitedKeyWords = keywords.trim().split("\\s+");
		String [] tempCollectionStrings = new String [1000];
		int i = 0;
		for (String string : splitedKeyWords) {
			tempCollectionStrings[i++] = encryptKeyWord(aesKeyString, string);
		}
		String [] blindKeyWordStrings = new String [i];
		System.arraycopy(tempCollectionStrings, 0, blindKeyWordStrings, 0, i);
		System.out.format("buildKeyWordValueArray: %s\n \t blindKeyWordStrings: %s\n\n", 
				keywords, Arrays.toString(blindKeyWordStrings));
		return blindKeyWordStrings;
	}
	// TODO: return array of Strings.
	// For each String, create an SQL JOIN statement
	public static String makeTrapdoor(String aesKeyString, String keyword) {
		String trapdoorString = encryptKeyWord(aesKeyString, keyword);
		System.out.format("makeTrapdoor: %s \t trapdoorString: %s\n", 
				keyword, trapdoorString);
		return trapdoorString;
	}
	

}
