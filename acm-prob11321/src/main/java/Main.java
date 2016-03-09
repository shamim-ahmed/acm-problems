import java.io.InputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class Main {
  public static void main(String... args) {
    processInput(System.in, System.out);
  }

  public static void processInput(InputStream inStream, PrintStream outStream) {
    Scanner scanner = new Scanner(inStream);
    StringBuilder resultBuilder = new StringBuilder();

    while (scanner.hasNextInt()) {
      int n = scanner.nextInt();
      int m = scanner.nextInt();
      resultBuilder.append(String.format("%d %d%n", n, m));

      if (n == 0 && m == 0) {
        break;
      }

      Integer[] valueArray = new Integer[n];

      for (int i = 0; i < n; i++) {
        valueArray[i] = scanner.nextInt();
      }

      Arrays.sort(valueArray, new CustomComparator(m));

      for (int i = 0; i < valueArray.length; i++) {
        resultBuilder.append(valueArray[i]).append("\n");
      }
    }

    scanner.close();
    outStream.print(resultBuilder.toString());
  }

  private static class CustomComparator implements Comparator<Integer> {
    private final int divisor;

    public CustomComparator(int divisor) {
      if (divisor == 0) {
        throw new IllegalArgumentException("divisor cannot be zero");
      }

      this.divisor = divisor;
    }

    @Override
    public int compare(Integer value1, Integer value2) {
      int remainder1 = computeReminder(value1);
      int remainder2 = computeReminder(value2);

      if (remainder1 < remainder2) {
        return -1;
      }

      if (remainder1 > remainder2) {
        return 1;
      }

      // if we reach this point, then both numbers have the same remainder
      boolean evenFlag1 = isEven(value1);
      boolean evenFlag2 = isEven(value2);

      // if one of the numbers is odd and the other one is even, then the odd
      // number will precede the even number
      if (!evenFlag1 && evenFlag2) {
        return -1;
      }

      if (evenFlag1 && !evenFlag2) {
        return 1;
      }

      // if there is a tie between two odd numbers, then the larger odd number
      // will precede the smaller one
      if (!evenFlag1 && !evenFlag2) {
        if (value1 < value2) {
          return 1;
        }

        if (value1 > value2) {
          return -1;
        }
      }

      // if there is a tie between two even numbers, then the smaller odd number
      // will precede the larger one
      if (evenFlag1 && evenFlag2) {
        if (value1 < value2) {
          return -1;
        }

        if (value1 > value2) {
          return 1;
        }
      }

      return 0;
    }

    private boolean isEven(int n) {
      return n % 2 == 0;
    }

    private int computeReminder(int dividend) {
      if (dividend > 0) {
        return dividend % divisor;
      }

      return dividend - ((dividend / divisor) * divisor);
    }
  }
}