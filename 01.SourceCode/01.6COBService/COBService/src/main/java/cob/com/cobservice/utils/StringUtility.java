package cob.com.cobservice.utils;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.Normalizer;
import java.util.Objects;
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
	
	public static String HexSHA(String Content) throws NoSuchAlgorithmException {

		//String password = "123456";

        MessageDigest md = MessageDigest.getInstance("SHA-256");
        byte[] hashInBytes = md.digest(Content.getBytes(StandardCharsets.UTF_8));

		// bytes to hex
        StringBuilder sb = new StringBuilder();
        for (byte b : hashInBytes) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();


    }
}
