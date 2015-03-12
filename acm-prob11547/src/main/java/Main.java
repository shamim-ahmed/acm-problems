import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class Main {
  public static void main(String... args) {
    processInput(System.in, System.out);
  }

  public static void processInput(InputStream inputStream, PrintStream outputStream) {
    StringBuilder resultBuilder = new StringBuilder();
    Scanner scanner = new Scanner(inputStream);
    final int numberOfCases = scanner.nextInt();

    for (int i = 0; i < numberOfCases; i++) {
      int n = scanner.nextInt();
      int result = (Math.abs(315 * n + 36962) / 10) % 10;
      resultBuilder.append(result).append("\n");
    }

    scanner.close();
    outputStream.print(resultBuilder.toString());
  }
}
