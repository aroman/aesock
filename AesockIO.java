import java.security.*;
import java.io.*;
import java.math.BigInteger;
import com.google.gson.Gson;
import java.util.HashMap;
import java.util.Map;

class AesockIO {

	public static String WRITE_DIR = "/tmp/"; 

	static String calculateHash (String username) throws UnsupportedEncodingException, NoSuchAlgorithmException {
		MessageDigest md = MessageDigest.getInstance("SHA-256");
		// Calculate SHA-256 digest of username bytes.
		byte[] digest = md.digest(username.getBytes("UTF-8"));
		// Return a string of the digest in hex (String) form.
		return new BigInteger(1, digest).toString(16);
	}

	static String charToHex (char c) {
		// Dear Java,
		// Please ditch your type primitives.
		// Love,
		// Everyone

		// return Integer.toString(Character.digit(c, 2));
		return "";
	}

	static String getFilePathForUser (String user) throws UnsupportedEncodingException, NoSuchAlgorithmException {
		return WRITE_DIR + calculateHash(user) + ".aes";
	}

	// This would work great, except that the XOR'd value
	// sometimes falls out of range of ASCII, meaning it's un-reversable
	// for all intents and purposes.
	// static String XORLikeABoss (String salt, String str) {
	// 	StringBuilder sb = new StringBuilder();
	// 	for (int i = 0, j = 0; i < str.length(); i++, j++) {
	// 		if (j == salt.length()) {
	// 			j = 0;
	// 		}
	// 		System.out.println("Old: " + salt.charAt(j) + "  New: " + str.charAt(i));
	// 	    sb.append((char) (salt.getBytes()[j] ^ str.getBytes()[i]));
	//     }
	// 	return sb.toString();
	// }

	static String superAdvancedTwoWayCipherFunction (String str) {
		StringBuilder sb = new StringBuilder ();
		for (int i = 0; i < str.length(); i++) {
		    char c = str.charAt(i);
		    if       (c >= 'a' && c <= 'm') c += 13;
		    else if  (c >= 'n' && c <= 'z') c -= 13;
		    else if  (c >= 'A' && c <= 'M') c += 13;
		    else if  (c >= 'A' && c <= 'Z') c -= 13;
		    sb.append(c);
		}
		return sb.toString();
	}

	public static String read (String from) throws FileNotFoundException, IOException, NoSuchAlgorithmException {
		BufferedReader in = new BufferedReader(new FileReader(getFilePathForUser(from)));
		StringBuilder messages = new StringBuilder();
		String str;
		while ((str = in.readLine()) != null) {
		    messages.append(str + "\n");
		}
		in.close();
		return superAdvancedTwoWayCipherFunction(messages.toString());
	}

	public static void write (String from, String to, String msg) throws IOException, NoSuchAlgorithmException {
		// Open the file in append mode.
		FileWriter fstream = new FileWriter(getFilePathForUser(to), true);
		BufferedWriter out = new BufferedWriter(fstream);
		out.write("\n" + superAdvancedTwoWayCipherFunction(msg));
		out.close();
	}
}