import java.io.InputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Main {
  private static final String LESS_THAN = "<";
  private static final String EQUAL_TO = "=";
  private static final String GREATER_THAN = ">";

  public static void main(String... args) {
    processInput(System.in, System.out);
  }

  public static void processInput(InputStream inStream, PrintStream outStream) {
    Scanner scanner = new Scanner(inStream);
    StringBuilder resultBuilder = new StringBuilder();

    final int m = Integer.parseInt(scanner.nextLine());

    for (int p = 0; p < m; p++) {
      // check for the empty line
      String line = scanner.nextLine().trim();
      
      if (line.equals("")) {
        line = scanner.nextLine().trim();
      }
      
      // read the data set
      String[] values = line.split(" ");
      final int numberOfCoins = Integer.parseInt(values[0]);
      final int numberOfWeightings = Integer.parseInt(values[1]);

      // position 0 is excluded from all calculations
      boolean[] coinFlags = new boolean[numberOfCoins + 1];

      for (int i = 0; i < numberOfWeightings; i++) {
        StringTokenizer tokenizer = new StringTokenizer(scanner.nextLine().trim(), " ", false);
        String resultLine = scanner.nextLine().trim();

        int coinPerScale = Integer.parseInt(tokenizer.nextToken());

        if (resultLine.equals(EQUAL_TO)) {
          for (int j = 0; j < 2 * coinPerScale; j++) {
            int k = Integer.parseInt(tokenizer.nextToken());
            coinFlags[k] = true;
          }
        } else if (resultLine.equals(GREATER_THAN) || resultLine.equals(LESS_THAN)) {
          int[] suspectCoins = new int[2 * coinPerScale];

          for (int j = 0; j < 2 * coinPerScale; j++) {
            suspectCoins[j] = Integer.parseInt(tokenizer.nextToken());
          }

          Arrays.sort(suspectCoins);

          for (int j = 1; j < coinFlags.length; j++) {
            if (Arrays.binarySearch(suspectCoins, j) < 0) {
              coinFlags[j] = true;
            }
          }
        }
      }


      // find the fake coin
      int fakeCoin = findFakeCoin(coinFlags);
      resultBuilder.append(fakeCoin).append("\n");
      
      if (p < m - 1) {
        resultBuilder.append("\n");
      }
    }

    scanner.close();
    outStream.print(resultBuilder.toString());
  }

  public static int findFakeCoin(boolean[] coinFlags) {
    int fakeCoin = 0;
    int count = 0;

    for (int i = 1; i < coinFlags.length; i++) {
      if (!coinFlags[i]) {
        fakeCoin = i;
        count++;
      }
    }

    if (count > 1) {
      fakeCoin = 0;
    }

    return fakeCoin;
  }
}
