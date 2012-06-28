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

	static String getFilePathForUser (String user) throws UnsupportedEncodingException, NoSuchAlgorithmException {
		return WRITE_DIR + calculateHash(user) + ".aes";
	}

	public static String read (String from) throws FileNotFoundException, IOException, NoSuchAlgorithmException {
		StringBuilder messages = new StringBuilder();
		String str;
		BufferedReader in = new BufferedReader(new FileReader(getFilePathForUser(from)));
		while ((str = in.readLine()) != null) {
		    messages.append(str + "\n");
		}
		in.close();
		return messages.toString();
	}

	public static void write (String from, String to, String msg) throws IOException, NoSuchAlgorithmException {
		// Open the file in append mode.
		FileWriter fstream = new FileWriter(getFilePathForUser(to), true);
		BufferedWriter out = new BufferedWriter(fstream);
		out.write("\n" + msg);
		out.close();
	}
}