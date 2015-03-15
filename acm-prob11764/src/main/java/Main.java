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

    for (int i = 1; i <= numberOfCases; i++) {
      final int numberOfWalls = scanner.nextInt();
      int[] values = new int[numberOfWalls];

      for (int j = 0; j < numberOfWalls; j++) {
        values[j] = scanner.nextInt();
      }

      int highJumpCount = 0;
      int lowJumpCount = 0;

      for (int j = 1; j < values.length; j++) {
        if (values[j] > values[j - 1]) {
          highJumpCount++;
        } else if (values[j] < values[j - 1]) {
          lowJumpCount++;
        }
      }

      resultBuilder.append(String.format("Case %d: %d %d%n", i, highJumpCount, lowJumpCount));
    }

    scanner.close();
    outputStream.print(resultBuilder.toString());
  }
}
