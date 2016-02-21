import java.io.InputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
  private static final int MAX = 10000;

  public static void main(String... args) {
    processInput(System.in, System.out);
  }

  public static void processInput(InputStream inStream, PrintStream outStream) {
    Scanner scanner = new Scanner(inStream);
    StringBuilder resultBuilder = new StringBuilder();
    long[] values = new long[MAX];
    int endIndex = 0;

    while (scanner.hasNextLine()) {
      // insert the next value in sorted array
      long n = Long.parseLong(scanner.nextLine().trim());
      insertValue(n, values, endIndex);
      endIndex++;

      // find the median
      int k = endIndex / 2;
      long median;

      if (endIndex % 2 == 0) {
        median = (values[k - 1] + values[k]) / 2;
      } else {
        median = values[k];
      }

      resultBuilder.append(median).append("\n");
    }

    scanner.close();
    outStream.print(resultBuilder.toString());
  }

  private static void insertValue(long n, long[] values, int endIndex) {
    if (endIndex == 0) {
      values[endIndex] = n;
      return;
    }

    int p = Arrays.binarySearch(values, 0, endIndex, n);
    int insertIndex;

    if (p >= 0) {
      insertIndex = p;
    } else {
      insertIndex = -(p + 1);
    }

    shiftValues(values, insertIndex, endIndex);
    values[insertIndex] = n;
  }

  private static void shiftValues(long[] values, int startIndex, int endIndex) {
    if (!validateIndex(values, startIndex)) {
      throw new IllegalArgumentException(String.format("illegal index : %d", startIndex));
    }

    if (!validateIndex(values, endIndex)) {
      throw new IllegalArgumentException(String.format("illegal index : %d", endIndex));
    }

    for (int i = endIndex; i > startIndex; i--) {
      values[i] = values[i - 1];
    }
  }

  private static boolean validateIndex(long[] values, int index) {
    return index >= 0 && index < values.length;
  }
}
