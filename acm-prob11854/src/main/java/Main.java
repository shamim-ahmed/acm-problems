import java.io.InputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
  private static final String RIGHT_STR = "right";
  private static final String WRONG_STR = "wrong";
  
  public static void main(String... args) {
    processInput(System.in, System.out);
  }

  public static void processInput(InputStream inStream, PrintStream outStream) {
    Scanner scanner = new Scanner(inStream);
    StringBuilder resultBuilder = new StringBuilder();

    while (scanner.hasNextInt()) {
      int a = scanner.nextInt();
      int b = scanner.nextInt();
      int c = scanner.nextInt();

      if (a == 0 && b == 0 && c == 0) {
        break;
      }

      int[] values = { a, b, c };
      Arrays.sort(values);

      if ((values[0] * values[0] + values[1] * values[1]) == values[2] * values[2]) {
        resultBuilder.append(RIGHT_STR).append("\n");
      } else {
        resultBuilder.append(WRONG_STR).append("\n");
      }
    }

    scanner.close();
    outStream.print(resultBuilder.toString());
  }
}