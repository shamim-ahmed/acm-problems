import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Main {
  private static final String POSITIVE_RESULT_PATTERN = "1|4|78";
  private static final String NEGATIVE_RESULT_PATTERN = "(\\d+)35";
  private static final String FAILED_EXPERIMENT_PATTERN = "9(\\d+)4";
  private static final String NOT_COMPLETED_EXPERIMENT_PATTERN = "190(\\d+)";

  public static void main(String... args) {
    processInput(System.in, System.out);
  }

  public static void processInput(InputStream inputStream, PrintStream outputStream) {
    StringBuilder resultBuilder = new StringBuilder();
    Scanner scanner = new Scanner(inputStream);
    final int n = Integer.parseInt(scanner.nextLine().trim());

    for (int i = 0; i < n; i++) {
      String line = scanner.nextLine();

      if (Pattern.matches(POSITIVE_RESULT_PATTERN, line)) {
        resultBuilder.append("+").append("\n");
      } else if (Pattern.matches(NEGATIVE_RESULT_PATTERN, line)) {
        resultBuilder.append("-").append("\n");
      } else if (Pattern.matches(FAILED_EXPERIMENT_PATTERN, line)) {
        resultBuilder.append("*").append("\n");
      } else if (Pattern.matches(NOT_COMPLETED_EXPERIMENT_PATTERN, line)) {
        resultBuilder.append("?").append("\n");
      }
    }

    scanner.close();
    outputStream.print(resultBuilder.toString());
  }
}
