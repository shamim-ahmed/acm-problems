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
    final int numberOfCases = Integer.parseInt(scanner.nextLine());

    for (int i = 1; i <= numberOfCases; i++) {
      String line = scanner.nextLine().trim();
      String[] values = line.split(" ");
      int max = 0;

      for (String s : values) {
        int x = Integer.parseInt(s);

        if (x > max) {
          max = x;
        }
      }

      resultBuilder.append(String.format("Case %d: %d%n", i, max));
    }

    scanner.close();
    outputStream.print(resultBuilder.toString());
  }
}
