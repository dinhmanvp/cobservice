package cob.com.finance.utils;

import java.math.BigDecimal;
import java.security.Key;
import java.security.Security;
import java.text.Normalizer;
import java.util.Objects;
import java.util.Random;
import java.util.regex.Pattern;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.util.encoders.Base64;
import org.bouncycastle.util.encoders.Hex;
import org.springframework.util.StringUtils;

import com.google.gson.JsonElement;

public class StringUtility {
	
	private static final String IV_PARAMETER = "I2LM2q4nt6Yyz0m3";
	private static final String TRANFOR = "AES/CBC/PKCS5Padding";

	public static Boolean isEmpty(JsonElement sInput) {
		try {
			if (!sInput.isJsonObject() && (Objects.isNull(sInput) || StringUtils.isEmpty(sInput.getAsString())))
				return true;
			else if (Objects.isNull(sInput) || org.springframework.util.StringUtils.isEmpty(sInput.toString()))
				return true;

			return false;
		} catch (Exception e) {
			return true;
		}
	}

	public static String removeUnicodeString(String str) {
		try {
			String temp = Normalizer.normalize(str, Normalizer.Form.NFD);
			Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
			return pattern.matcher(temp).replaceAll("").toLowerCase().replaceAll(" ", "").replaceAll("đ", "d");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}

	@SuppressWarnings("all")
	public static boolean isBase64Data(String imageString) {
		try {
			String img = imageString.trim();
			byte[] imageByte = Base64.decode(img);
			return true;
		} catch (Exception e) {
			return false;
		}

	}

	public static byte[] ImageBase64tobytes(String imageString) {
		String img = imageString.trim();
		byte[] imageByte = Base64.decode(img);
		return imageByte;
	}
	
	public static String LPAD(String value, String padStr) {
		String result = padStr + value;
		return result.substring(result.length()-padStr.length());
	}
	
	public static String decryptData(String hexKey, String input) throws Exception {
		if (Security.getProvider(BouncyCastleProvider.PROVIDER_NAME) == null) {
			Security.addProvider(new BouncyCastleProvider());
		}
		Cipher cipher = Cipher.getInstance(TRANFOR, "BC");
		Key decryptionKey = new SecretKeySpec(Hex.decode(hexKey), "AES");
		cipher.init(Cipher.DECRYPT_MODE, decryptionKey, new IvParameterSpec(IV_PARAMETER.getBytes()));
		byte[] plainText = cipher.doFinal(Hex.decode(input));

		return new String(plainText);
	}

	public static String encryptData(String hexKey, String input) throws Exception {
		if (Security.getProvider(BouncyCastleProvider.PROVIDER_NAME) == null) {
			Security.addProvider(new BouncyCastleProvider());
		}
		Cipher cipher = Cipher.getInstance(TRANFOR, "BC");
		Key secretKeySpec = new SecretKeySpec(Hex.decode(hexKey), "AES");
		cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, new IvParameterSpec(IV_PARAMETER.getBytes()));
		byte[] plainText = cipher.doFinal(input.getBytes("UTF-8"));
		return Hex.toHexString(plainText);
	}

	public static String generaKey() throws Exception {
		if (Security.getProvider(BouncyCastleProvider.PROVIDER_NAME) == null) {
			Security.addProvider(new BouncyCastleProvider());
		}
		KeyGenerator generator = KeyGenerator.getInstance("AES", "BC");
		generator.init(128);
		Key encryptionKey = generator.generateKey();
		return Hex.toHexString(encryptionKey.getEncoded());
	}
	
	public static String OTP(int len) 
    { 
        System.out.println("Generating OTP using random() : "); 
        System.out.print("You OTP is : "); 
  
        // Using numeric values 
        String numbers = "0123456789"; 
  
        // Using random method 
        Random rndm_method = new Random(); 
        String otp = "";
        char[] character = new char[len]; 
  
        for (int i = 0; i < len; i++) 
        { 
            // Use of charAt() method : to get character value 
            // Use of nextInt() as it is scanning the value as int 
        	character[i] = numbers.charAt(rndm_method.nextInt(numbers.length())); 
        	otp += character[i];
        } 
        return otp; 
    }
	
	public static void main(String[] args) throws Exception {
		String key = "cfd4fcba234f23bd9c1fb8e306c005ee";
//		String bEncr = "e0cb065243b2d10d014d03bcd0ddeae2";
//		String abEncr = "e0cb065243b2d10d014d03bcd0ddeae2";
//		String balance = decryptData(key, bEncr);		
//		String avaibalance = decryptData(key, abEncr);
//		System.out.println("Avaiable Decrypt: " + avaibalance);
//		System.out.println("Balance Decrypt: " + balance);
//		System.out.println("------------------------------Decrypt---------------------------------");
		BigDecimal b = new BigDecimal(0);
		//String balanceEncrypt = encryptData(key, b.toString());
		String balanceEncrypt = decryptData(key, "e0cb065243b2d10d014d03bcd0ddeae2");
		System.out.println("Avaiable Decrypt: " + balanceEncrypt);
		//System.out.println("Balance Encrypt: " + balanceEncrypt);
		//String avaibalance = decryptData(key, "a6c44109fe530ecddae31ebaff003753");
		//System.out.println("Balance Decrypt: " + avaibalance);
	}
}