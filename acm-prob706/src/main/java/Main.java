import java.util.Scanner;
import java.util.StringTokenizer;

public class Main {
  private static final String EMPTY_LINE = "";
  private static final String TERMINATING_NUBMER = "0";
  private static final String[][][] led_patterns = {
	  { { " ", "-", " " }, { " ", " ", " " }, { " ", "-", " " }, { " ", "-", " " }, { " ", " ", " " },
	      { " ", "-", " " }, { " ", "-", " " }, { " ", "-", " " }, { " ", "-", " " }, { " ", "-", " " } },
	  { { "|", " ", "|" }, { " ", " ", "|" }, { " ", " ", "|" }, { " ", " ", "|" }, { "|", " ", "|" },
	      { "|", " ", " " }, { "|", " ", " " }, { " ", " ", "|" }, { "|", " ", "|" }, { "|", " ", "|" } },
	  { { " ", " ", " " }, { " ", " ", " " }, { " ", "-", " " }, { " ", "-", " " }, { " ", "-", " " },
	      { " ", "-", " " }, { " ", "-", " " }, { " ", " ", " " }, { " ", "-", " " }, { " ", "-", " " } },
	  { { "|", " ", "|" }, { " ", " ", "|" }, { "|", " ", " " }, { " ", " ", "|" }, { " ", " ", "|" },
	      { " ", " ", "|" }, { "|", " ", "|" }, { " ", " ", "|" }, { "|", " ", "|" }, { " ", " ", "|" } },
	  { { " ", "-", " " }, { " ", " ", " " }, { " ", "-", " " }, { " ", "-", " " }, { " ", " ", " " },
	      { " ", "-", " " }, { " ", "-", " " }, { " ", " ", " " }, { " ", "-", " " }, { " ", "-", " " } } };

  public static void main(String... args) {
	Scanner scanner = null;

	try {
	  scanner = new Scanner(System.in);

	  while (scanner.hasNextLine()) {
		String line = scanner.nextLine().trim();

		if (line.equals(EMPTY_LINE)) {
		  continue;
		}

		StringTokenizer tokenizer = new StringTokenizer(line, " ", false);

		if (tokenizer.countTokens() != 2) {
		  // invalid input line
		  continue;
		}

		int size = Integer.parseInt(tokenizer.nextToken());
		String numberStr = tokenizer.nextToken();

		if (size == 0 && numberStr.equals(TERMINATING_NUBMER)) {
		  break;
		}

		processNumber(size, numberStr);
	  }
	} finally {
	  if (scanner != null) {
		scanner.close();
	  }
	}
  }

  private static void processNumber(int size, String numberStr) {
	int n = numberStr.length();

	for (int i = 0; i < led_patterns.length; i++) {
	  int m = (i % 2 == 0) ? 1 : size;

	  for (int p = 0; p < m; p++) {
		for (int j = 0; j < n; j++) {
		  int digit = numberStr.charAt(j) - '0';
		  String[] patterns = led_patterns[i][digit];
		  printRow(patterns, size, n, j);
		}
		
		System.out.println();
	  }
	}

	System.out.println();
  }

  private static void printRow(String[] patterns, int size, int n, int j) {
	System.out.print(patterns[0]);

	for (int k = 0; k < size; k++) {
	  System.out.print(patterns[1]);
	}

	System.out.print(patterns[2]);

	if (j != n - 1) {
	  System.out.print(" ");
	}
  }
}
