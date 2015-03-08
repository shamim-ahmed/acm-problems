import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class Main {
  private static final String BORDER = "divisa";

  public static void main(String... args) {
    processInput(System.in, System.out);
  }

  public static void processInput(InputStream inputStream, PrintStream outputStream) {
    Scanner scanner = new Scanner(inputStream);
    StringBuilder resultBuilder = new StringBuilder();

    while (scanner.hasNextInt()) {
      int n = scanner.nextInt();

      if (n == 0) {
        break;
      }

      int a = scanner.nextInt();
      int b = scanner.nextInt();

      for (int i = 0; i < n; i++) {
        int x = scanner.nextInt();
        int y = scanner.nextInt();
        int tx = x - a;
        int ty = y - b;

        if (tx == 0 || ty == 0) {
          resultBuilder.append(BORDER).append("\n");
        } else {
          if (ty > 0) {
            resultBuilder.append("N");
          } else {
            resultBuilder.append("S");
          }

          if (tx > 0) {
            resultBuilder.append("E");
          } else {
            resultBuilder.append("O");
          }

          resultBuilder.append("\n");
        }
      }
    }

    scanner.close();
    outputStream.print(resultBuilder.toString());
  }
}
