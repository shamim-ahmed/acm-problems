import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class Main {
  private static final char[] ONE_CHAR_ARRAY = { 'o', 'n', 'e' };

  public static void main(String... args) {
    processInput(System.in, System.out);
  }

  public static void processInput(InputStream inputStream, PrintStream outputStream) {
    StringBuilder resultBuilder = new StringBuilder();
    Scanner scanner = new Scanner(inputStream);
    final int numberOfWords = Integer.parseInt(scanner.nextLine());

    for (int i = 0; i < numberOfWords; i++) {
      String line = scanner.nextLine();

      if (line.length() == 5) {
        resultBuilder.append(3).append("\n");
      } else {
        int matchCount = 0;

        for (int j = 0; j < ONE_CHAR_ARRAY.length; j++) {
          if (ONE_CHAR_ARRAY[j] == line.charAt(j)) {
            matchCount++;
          }
        }

        if (matchCount >= 2) {
          resultBuilder.append(1).append("\n");
        } else {
          resultBuilder.append(2).append("\n");
        }
      }
    }

    scanner.close();
    outputStream.print(resultBuilder.toString());
  }
}
