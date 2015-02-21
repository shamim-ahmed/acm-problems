import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class Main {
  private static final String LINE_SEPARATOR = "\n";

  public static void main(String... args) {
    processInput(System.in, System.out);
  }

  public static void processInput(InputStream inputStream, PrintStream outputStream) {
    StringBuilder resultBuilder = new StringBuilder();

    Scanner scanner = new Scanner(inputStream);

    while (scanner.hasNext()) {
      final int m = scanner.nextInt();
      final int n = scanner.nextInt();

      if (m == 0 && n == 0) {
        break;
      }

      int[] aliceValues = new int[m];
      boolean[] aliceFlags = new boolean[m];
      int[] bettyValues = new int[n];
      boolean[] bettyFlags = new boolean[n];

      for (int k = 0; k < m; k++) {
        aliceValues[k] = scanner.nextInt();
        aliceFlags[k] = true;
      }

      for (int k = 0; k < n; k++) {
        bettyValues[k] = scanner.nextInt();
        bettyFlags[k] = true;
      }

      for (int i = 0; i < aliceValues.length; i++) {
        boolean matchFound = false;

        for (int j = 0; j < bettyValues.length; j++) {
          if (aliceValues[i] == bettyValues[j]) {
            // this must be always done
            bettyFlags[j] = false;

            // now check if duplicate entries are there, and eliminate them
            int k = j + 1;

            while ((k < bettyValues.length - 1) && (bettyValues[k] == bettyValues[k + 1])) {
              bettyFlags[k++] = false;
            }

            j = k - 1;
          } else {
            // match was not found
            // eliminate entries with same value, except the first one
            while ((j < bettyValues.length - 1) && (bettyValues[j] == bettyValues[j + 1])) {
              bettyFlags[++j] = false;
            }
          }
        }

        if (matchFound) {
          // this must always be done
          aliceFlags[i] = false;

          // now check if duplicate entries are there, and eliminate them
          int k = i + 1;

          while ((k < aliceValues.length - 1) && (aliceValues[k] == aliceValues[k + 1])) {
            aliceFlags[k++] = false;
          }

          i = k - 1;
        } else {
          // match was not found
          // eliminate entries with same value, except the first one
          while ((i < aliceValues.length - 1) && (aliceValues[i] == aliceValues[i + 1])) {
            aliceFlags[++i] = false;
          }
        }
      }

      int aliceCount = getCount(aliceFlags);
      int bettyCount = getCount(bettyFlags);

      int result = (aliceCount < bettyCount) ? aliceCount : bettyCount;
      resultBuilder.append(result).append(LINE_SEPARATOR);
    }

    outputStream.print(resultBuilder.toString());
    scanner.close();
  }

  private static int getCount(boolean[] flags) {
    int result = 0;

    for (boolean f : flags) {
      if (f) {
        result++;
      }
    }

    return result;
  }
}
