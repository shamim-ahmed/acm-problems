import java.io.InputStream;
import java.io.PrintStream;
import java.util.Arrays;
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

      final int aliceCount = readValues(scanner, m, aliceValues);
      final int bettyCount = readValues(scanner, n, bettyValues);
      int result;

      if (aliceCount < bettyCount) {
        result = computeResult(aliceValues, aliceCount, bettyValues, bettyCount);
      } else {
        result = computeResult(bettyValues, bettyCount, aliceValues, aliceCount);
      }
      
      resultBuilder.append(result).append(LINE_SEPARATOR);
    }

    outputStream.print(resultBuilder.toString());
    scanner.close();
  }

  private static int computeResult(int[] primaryArray, int m, int[] searchArray, int n) {
    int result = m;

    for (int i = 0; i < m; i++) {
      int val = primaryArray[i];

      int targetIndex = Arrays.binarySearch(searchArray, 0, n, val);

      if (targetIndex >= 0) {
        result--;
      }
    }

    return result;
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
