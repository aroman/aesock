import java.security.*;
import java.io.*;
import java.math.BigInteger;

class AesockWriter {

	public String sender;
	public String sender_hash;

	public AesockWriter (String sender) {

		this.sender = sender;
		try {
			sender_hash = calculateHash(sender);
			System.out.println(sender_hash);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	String calculateHash (String username) throws UnsupportedEncodingException, NoSuchAlgorithmException {
		byte[] bytes = username.getBytes("UTF-8");

		MessageDigest md = MessageDigest.getInstance("SHA-256");
		byte[] digest = md.digest(bytes);
		return new BigInteger(digest).toString(16);
	}

	public static boolean write (String from, String to) {
		System.out.println("Will write to " + to + " from " + to);
		return true;
	}
}