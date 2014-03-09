import java.util.Scanner;
import java.util.StringTokenizer;

public class Main {
  public static void main(String... args) {
	Scanner scanner = null;

	try {
	  scanner = new Scanner(System.in);

	  while (scanner.hasNextLine()) {
		String line = scanner.nextLine();
		StringTokenizer tokenizer = new StringTokenizer(line, " ", false);

		if (tokenizer.countTokens() != 2) {
		  continue;
		}

		int initialHeight = Integer.parseInt(tokenizer.nextToken());
		int workerCatCount = Integer.parseInt(tokenizer.nextToken());

		if (initialHeight == 0 && workerCatCount == 0) {
		  break;
		}

		findSolution(initialHeight, workerCatCount);
	  }
	} finally {
	  if (scanner != null) {
		scanner.close();
	  }
	}
  }

  private static void findSolution(int initialHeight, int workerCatCount) {
	if (workerCatCount == 1) {
	  int n = 1;
	  int k = (int) Math.round((Math.log(initialHeight) / Math.log(2)));
	  printResult(n, k);
	  return;
	}

	boolean[] flags = new boolean[1000];
	flags[0] = true;
	flags[1] = true;
	final int size = flags.length;

	for (int n = 2; n < size; n++) {
	  if (flags[n]) {
		continue;
	  }

	  flags[n] = true;
	  int k = (int) Math.round((Math.log(workerCatCount) / Math.log(n)));
	  int r = (int) Math.pow(n, k);

	  if (r == workerCatCount) {
		if (initialHeight == (int) Math.pow(n + 1, k)) {
		  printResult(n, k);
		  break;
		}
	  }	else {
		int p = n * n;
		
		while (p < size) {
		  flags[p] = true;
		  p *= n;
		}
	  }
	}
  }

  private static void printResult(int n, int k) {
	int numberOfIdleCats, heightOfCatStack;
	
	if (n == 1) {
	  numberOfIdleCats = k;
	  heightOfCatStack = (int) Math.pow(2, k + 1) - 1;
	} else {
	  numberOfIdleCats = ((int) Math.pow(n, k) - 1) / (n - 1);
	  heightOfCatStack = (int)(Math.pow(n + 1, k + 1) - Math.pow(n, k + 1));
	}

	System.out.printf("%d %d%n", numberOfIdleCats, heightOfCatStack);
  }
}