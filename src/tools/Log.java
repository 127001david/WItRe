package tools;

public class Log {
	private final static boolean show = true;

	public static void show(final String... log) {
		StringBuffer logs = new StringBuffer();

		for (String l : log) {
			logs.append(l + " ");
		}

		if (show)
			System.out.println("Tuier log: " + logs.toString());
	}
}