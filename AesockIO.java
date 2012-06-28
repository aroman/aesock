import java.security.*;
import java.io.*;
import java.math.BigInteger;

class AesockIO {

	public static String WRITE_DIR = "/tmp/"; 

	static String calculateHash (String username) throws UnsupportedEncodingException, NoSuchAlgorithmException {
		MessageDigest md = MessageDigest.getInstance("SHA-256");
		// Calculate SHA-256 digest of username bytes.
		byte[] digest = md.digest(username.getBytes("UTF-8"));
		// Return a string of the digest in hex (String) form.
		return new BigInteger(1, digest).toString(16);
	}

	public static String[] read (String from) {
		return new String[] {};
	}

	public static void write (String from, String to, String msg) throws IOException, NoSuchAlgorithmException {
		System.out.println("Will write to " + to + " from " + from);
		FileWriter fstream = new FileWriter(WRITE_DIR + calculateHash(to) + ".aes");
		BufferedWriter out = new BufferedWriter(fstream);
		out.write(msg);
		out.close();
	}
}