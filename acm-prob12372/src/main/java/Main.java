import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class Main {
  private static final int MAX = 20;
  private static final String GOOD_RESULT = "good";
  private static final String BAD_RESULT = "bad";

  public static void main(String... args) {
    processInput(System.in, System.out);
  }

  public static void processInput(InputStream inputStream, PrintStream outputStream) {
    StringBuilder resultBuilder = new StringBuilder();
    Scanner scanner = new Scanner(inputStream);
    final int numberOfCases = scanner.nextInt();

    for (int i = 1; i <= numberOfCases; i++) {
      int x = scanner.nextInt();
      int y = scanner.nextInt();
      int z = scanner.nextInt();
      String result = GOOD_RESULT;

      if (x > MAX || y > MAX || z > MAX) {
        result = BAD_RESULT;
      }

      resultBuilder.append(String.format("Case %d: %s%n", i, result));
    }

    scanner.close();
    outputStream.print(resultBuilder.toString());
  }
}
