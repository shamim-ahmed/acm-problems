import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class Main {
  private enum SortOrder {
    ASCENDING, DESCENDING
  }

  private static final int VALUES_PER_GROUP = 10;

  public static void main(String... args) {
    processInput(System.in, System.out);
  }

  public static void processInput(InputStream inputStream, PrintStream outputStream) {
    StringBuilder resultBuilder = new StringBuilder();
    resultBuilder.append("Lumberjacks:").append("\n");
    Scanner scanner = new Scanner(inputStream);
    final int numberOfGroups = scanner.nextInt();

    for (int i = 0; i < numberOfGroups; i++) {
      int x = scanner.nextInt();
      int y = scanner.nextInt();
      final SortOrder expectedOrder = (x < y) ? SortOrder.ASCENDING : SortOrder.DESCENDING;
      boolean result = true;

      for (int j = 2; j < VALUES_PER_GROUP; j++) {
        x = y;
        y = scanner.nextInt();

        if (result && (x < y && expectedOrder == SortOrder.DESCENDING) || (x > y && expectedOrder == SortOrder.ASCENDING)) {
          result = false;
        }
      }

      if (result) {
        resultBuilder.append("Ordered");
      } else {
        resultBuilder.append("Unordered");
      }

      resultBuilder.append("\n");
    }

    scanner.close();
    outputStream.print(resultBuilder.toString());
  }
}
