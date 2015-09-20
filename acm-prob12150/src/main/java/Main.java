import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class Main {
  private static final int END_OF_INPUT_MARKER = 0;

  public static void main(String... args) {
    processInput(System.in, System.out);
  }

  public static void processInput(InputStream inputStream, PrintStream outputStream) {
    Scanner scanner = new Scanner(inputStream);
    StringBuilder resultBuilder = new StringBuilder();

    while (scanner.hasNextInt()) {
      int n = scanner.nextInt();

      if (n == END_OF_INPUT_MARKER) {
        break;
      }

      int[] finalSequence = new int[n];
      int[] positionalGains = new int[n];

      for (int i = 0; i < n; i++) {
        finalSequence[i] = scanner.nextInt();
        positionalGains[i] = scanner.nextInt();
      }

      int[] initialSequence = new int[n];
      boolean valid = true;

      for (int i = 0; i < n; i++) {
        int car = finalSequence[i];
        int initialPosition = i + positionalGains[i];

        if (initialPosition < 0 || initialPosition >= n || initialSequence[initialPosition] != 0) {
          valid = false;
          resultBuilder.append(-1).append("\n");
          break;
        }

        initialSequence[initialPosition] = car;
      }

      if (valid) {
        for (int i = 0; i < n; i++) {
          resultBuilder.append(initialSequence[i]).append(" ");
        }

        resultBuilder.deleteCharAt(resultBuilder.length() - 1);
        resultBuilder.append("\n");
      }
    }

    scanner.close();
    outputStream.print(resultBuilder.toString());
  }
}
