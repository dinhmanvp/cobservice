package cob.com.accounts.utils;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
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
		return result.substring(result.length() - padStr.length());
	}

	public static String HexSHA(String Content) throws NoSuchAlgorithmException {

		// String password = "123456";

		MessageDigest md = MessageDigest.getInstance("SHA-256");
		byte[] hashInBytes = md.digest(Content.getBytes(StandardCharsets.UTF_8));

		// bytes to hex
		StringBuilder sb = new StringBuilder();
		for (byte b : hashInBytes) {
			sb.append(String.format("%02x", b));
		}
		return sb.toString();

	}

	public static String geek_Password(int len) {
		System.out.println("Generating password using random() : ");
		System.out.print("Your new password is : ");

		// A strong password has Cap_chars, Lower_chars,
		// numeric value and symbols. So we are using all of
		// them to generate our password
		String Capital_chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		String Small_chars = "abcdefghijklmnopqrstuvwxyz";
		String numbers = "0123456789";
		// String symbols = "!@#$%^&*_=+-/.?<>)";

		String values = Capital_chars + Small_chars + numbers;// + symbols;

		// Using random method
		Random rndm_method = new Random();

		char[] password = new char[len];
		String resutl = "";

		for (int i = 0; i < len; i++) {
			// Use of charAt() method : to get character value
			// Use of nextInt() as it is scanning the value as int
			password[i] = values.charAt(rndm_method.nextInt(values.length()));
			resutl += password[i];
		}
		return resutl;
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

	public static void main(String[] args) throws Exception {

		//String key = generaKey();
		//System.out.println(key);
		String tem = encryptData("babe9b372cb154025929a6f72c10dae2", "c@bb@ck0ffic2");
		System.out.println(tem);
		//String temde = decryptData("89cea2097630c552c4d9434f32595aee", "496e342aac0b59a26b65fd134fdb7c11");
		//System.out.println(temde);

	}
}
