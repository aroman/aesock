import java.util.Arrays;
import java.util.List;

class Aesock {

	static String username = System.getProperty("user.name");

	public static void main (String[] args) {
		// Check for help flags, and display usage
		// if any were found.
		for (String help_opt : Arrays.asList("-h", "--help")) {
			if (Arrays.asList(args).indexOf(help_opt) != -1) {
				usage();
				break;
			}
		}

		switch (args.length) {
			case 0:
				usage();
				break;
			case 1:
				readMessages(args[0]);
				break;
			default:
				// Stitch together all subsequent args into an Array
				// containing the message content.
				String[] message_chunks = Arrays.copyOfRange(args, 1, args.length);
				StringBuilder message = new StringBuilder();
				for (int i = 0; i < message_chunks.length; i++) {
					message.append(message_chunks[i]);
					// Only append a space if this isn't the last iteration.
					if (i < (message_chunks.length - 1)) {
						message.append(" ");
					}
				}
				writeMessage(args[0], message.toString());
				break;
		}
	}

	static void readMessages(String from) {
		try {
			String messages = AesockIO.read(from);
			System.out.println("Messages from " + from + ":");
			System.out.println(messages);
		} catch (Exception e) {
			System.out.println("Failed to read messages.");
			System.exit(1);
		}
	}

	static void writeMessage(String to, String message) {
		try {
			AesockIO.write(username, to, message);
			System.out.println("Message written successfully.");
		} catch (Exception e) {
			System.out.println("Failed to write message.");
			System.exit(1);
		}
	}

	static void usage () {
		System.out.println("Usage:");
		System.out.println("    -h, --help\t\t\t Displays this help text.");
		System.out.println("    aesock <usr>\t\t Reads messages from user <usr>.");
		System.out.println("    aesock <usr> <msg>\t\t Sends message <msg> to user <usr>.");
		System.exit(0);
	}

}