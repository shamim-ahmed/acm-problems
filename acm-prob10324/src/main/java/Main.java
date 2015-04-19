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
    int count = 0;

    while (scanner.hasNextLine()) {
      String inputLine = scanner.nextLine().trim();

      if (inputLine.isEmpty()) {
        break;
      }

      count++;

      int n = Integer.parseInt(scanner.nextLine());
      resultBuilder.append(String.format("Case %d:%n", count));
      for (int i = 0; i < n; i++) {
        String indexLine = scanner.nextLine();
        boolean result = checkInterval(inputLine.toCharArray(), indexLine);

        if (result) {
          resultBuilder.append("Yes").append("\n");
        } else {
          resultBuilder.append("No").append("\n");
        }
      }
    }

    scanner.close();
    outputStream.print(resultBuilder.toString());
  }

  private static final boolean checkInterval(char[] inputChars, String indexLine) {
    String[] indexArray = indexLine.split(" ");
    int p = Integer.parseInt(indexArray[0]);
    int q = Integer.parseInt(indexArray[1]);

    if (p == q) {
      return true;
    }

    if (p >= inputChars.length || q >= inputChars.length) {
      return false;
    }

    int start, end;

    if (p < q) {
      start = p;
      end = q;
    } else {
      start = q;
      end = p;
    }

    boolean result = true;
    char valueToCheck = inputChars[start];

    for (int i = start + 1; i <= end; i++) {
      if (valueToCheck != inputChars[i]) {
        result = false;
        break;
      }
    }

    return result;
  }
}
