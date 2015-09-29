import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
  private static final Pattern INPUT_LINE_PATTERN = Pattern.compile("(\\d+)\\s+([a-zA-Z0-9]+)");
  private static final String END_OF_INPUT_MARKER = "0";

  public static void main(String... args) {
    processInput(System.in, System.out);
  }

  public static void processInput(InputStream inputStream, PrintStream outputStream) {
    Scanner scanner = new Scanner(inputStream);
    StringBuilder resultBuilder = new StringBuilder();

    while (scanner.hasNextLine()) {
      String line = scanner.nextLine().trim();

      if (line.equals(END_OF_INPUT_MARKER)) {
        break;
      }

      Matcher matcher = INPUT_LINE_PATTERN.matcher(line);

      if (!matcher.matches()) {
        throw new RuntimeException(String.format("Invalid input line : %s", line));
      }

      int groupCount = Integer.parseInt(matcher.group(1));
      String input = matcher.group(2);
      char[] inputArray = input.toCharArray();

      if (inputArray.length % groupCount != 0) {
        throw new RuntimeException(String.format(
            "The length of the input %s is not divisible by %d", input, groupCount));
      }

      int n = inputArray.length / groupCount;
      int i = 0;

      while (i < inputArray.length) {
        int j = i + n - 1;
        reverse(inputArray, i, j);
        i = j + 1;
      }

      resultBuilder.append(new String(inputArray)).append("\n");
    }

    scanner.close();
    outputStream.print(resultBuilder.toString());
  }

  private static void reverse(char[] inputArray, int i, int j) {
    if (i < 0 || j >= inputArray.length) {
      return;
    }

    while (i < j) {
      char c = inputArray[i];
      inputArray[i] = inputArray[j];
      inputArray[j] = c;
      i++;
      j--;
    }
  }
}
