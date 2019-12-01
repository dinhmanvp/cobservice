package cob.com.communication.utils;

import java.text.Normalizer;
import java.util.Objects;
import java.util.Random;
import java.util.regex.Pattern;

import org.bouncycastle.util.encoders.Base64;
import org.springframework.util.StringUtils;

import com.google.gson.JsonElement;

public class StringUtility {

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
	
	public static char[] geek_Password(int len) 
    { 
        System.out.println("Generating password using random() : "); 
        System.out.print("Your new password is : "); 
  
        // A strong password has Cap_chars, Lower_chars, 
        // numeric value and symbols. So we are using all of 
        // them to generate our password 
        String Capital_chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"; 
        String Small_chars = "abcdefghijklmnopqrstuvwxyz"; 
        String numbers = "0123456789"; 
                //String symbols = "!@#$%^&*_=+-/.?<>)"; 
  
  
        String values = Capital_chars + Small_chars + 
                        numbers;// + symbols; 
  
        // Using random method 
        Random rndm_method = new Random(); 
  
        char[] password = new char[len]; 
  
        for (int i = 0; i < len; i++) 
        { 
            // Use of charAt() method : to get character value 
            // Use of nextInt() as it is scanning the value as int 
            password[i] = 
              values.charAt(rndm_method.nextInt(values.length())); 
  
        } 
        return password; 
    } 
}
