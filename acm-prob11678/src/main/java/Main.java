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
      int[] bettyValues = new int[n];

      final int aliceIndex = readValues(scanner, m, aliceValues);
      final int bettyIndex = readValues(scanner, n, bettyValues);

      int aliceCount = aliceIndex;
      int bettyCount = bettyIndex;

      for (int i = 0; i < aliceIndex; i++) {
        int a = aliceValues[i];
        int j = 0;

        while (j < bettyIndex) {
          int b = bettyValues[j];

          if (a < b) {
            break;
          } else if (a == b) {
            aliceCount--;
            bettyCount--;
            break;
          }

          j++;
        }
      }

      int result = aliceCount < bettyCount ? aliceCount : bettyCount;
      resultBuilder.append(result).append(LINE_SEPARATOR);
    }

    outputStream.print(resultBuilder.toString());
    scanner.close();
  }

  private static int readValues(Scanner scanner, int max, int[] values) {
    int index = 0;
    values[index++] = scanner.nextInt();

    for (int k = 1; k < max; k++) {
      int val = scanner.nextInt();

      if (val > values[index - 1]) {
        values[index++] = val;
      }
    }

    return index;
  }
}
