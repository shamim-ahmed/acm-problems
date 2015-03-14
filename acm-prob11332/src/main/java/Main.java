import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class Main {
  private static final String INPUT_TERMINATOR = "0";
  
  public static void main(String... args) {
    processInput(System.in, System.out);
  }

  public static void processInput(InputStream inputStream, PrintStream outputStream) {
    StringBuilder resultBuilder = new StringBuilder();
    Scanner scanner = new Scanner(inputStream);

    while (scanner.hasNextLine()) {
      String numberStr = scanner.nextLine().trim();
      
      if (numberStr.equals(INPUT_TERMINATOR)) {
        break;
      }
      
      String result = computeResult(numberStr);
      resultBuilder.append(result).append("\n");
    }

    scanner.close();
    outputStream.print(resultBuilder.toString());
  }

  public static String computeResult(String numberStr) {
    if (numberStr.length() == 1) {
      return numberStr;
    }

    String currentStr = numberStr;

    while (currentStr.length() > 1) {
      int sum = 0;

      for (int i = 0, n = currentStr.length(); i < n; i++) {
        char c = currentStr.charAt(i);
        sum += (c - '0');
      }

      currentStr = Integer.toString(sum);
    }

    return currentStr;
  }
}
