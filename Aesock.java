import java.util.Arrays;
import java.util.List;

class Aesock {

	static AesockWriter writer;

	public static void main (String[] args) {

		if (args.length == 0)
			usage();

		for (String help_opt : Arrays.asList("-h", "--help")) {
			if (Arrays.asList(args).indexOf(help_opt) != -1) {
				usage();
				break;
			}
		}

		String recipient = args[0];
		writer = new AesockWriter (recipient);
		try {
			writer.write("johnny", "Hello!");
		} catch (Exception e) {
			System.out.println("Something dun goofed.");
		}
	}

	static void usage () {

		System.out.println("Usage:");
		System.out.println("    aesock <user>\t\t Leaves a message for user <user>.");
		System.exit(0);
	}

}