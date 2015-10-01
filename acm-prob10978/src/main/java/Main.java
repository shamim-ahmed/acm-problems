import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
  private static final Pattern NUMERIC_PATTERN = Pattern.compile("\\d+");

  public static void main(String... args) {
    processInput(System.in, System.out);
  }

  public static void processInput(InputStream inputStream, PrintStream outputStream) {
    Scanner scanner = new Scanner(inputStream);
    StringBuilder resultBuilder = new StringBuilder();

    while (true) {
      String inputVal = scanner.nextLine().trim();
      Matcher matcher = NUMERIC_PATTERN.matcher(inputVal);

      if (!matcher.matches()) {
        continue;
      }

      final int n = Integer.parseInt(inputVal);

      if (n == 0) {
        break;
      }

      String[] cardArray = new String[n];
      String[] cardNameArray = new String[n];

      for (int i = 0; i < n; i++) {
        String line = scanner.nextLine();
        String[] tokens = line.split("\\s+");

        if (tokens.length != 2) {
          throw new RuntimeException(String.format("Invalid input line : %s", line));
        }

        cardArray[i] = tokens[0];
        cardNameArray[i] = tokens[1];
      }

      int p = -1;
      String[] resultArray = new String[n];

      for (int i = 0; i < n; i++) {
        int len = cardNameArray[i].length();
        int count = 0;

        do {
          p = (p + 1) % n;

          if (resultArray[p] == null) {
            count++;
          }
        } while (count < len);

        resultArray[p] = cardArray[i];
      }

      for (String card : resultArray) {
        resultBuilder.append(card).append(" ");
      }

      resultBuilder.deleteCharAt(resultBuilder.length() - 1);
      resultBuilder.append("\n");
    }

    scanner.close();
    outputStream.print(resultBuilder.toString());
  }
}
