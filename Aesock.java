import java.util.Arrays;
import java.util.List;

class Aesock {
	public static void main (String[] args) {

		if (args.length == 0)
			usage();

		for (String help_opt : Arrays.asList("-h", "--help")) {
			if (Arrays.asList(args).indexOf(help_opt) != -1) {
				usage();
				break;
			}
		}
	}

	static void usage () {
		System.out.println("Usage:");
		System.out.println("    aesock <userr>\t\t Leaves a message for user <user>.");
	}
}