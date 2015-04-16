import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class Main {
  private static final int INVALID_POSITION = -1;

  public static void main(String... args) {
    processInput(System.in, System.out);
  }

  public static void processInput(InputStream inputStream, PrintStream outputStream) {
    Scanner scanner = new Scanner(inputStream);
    StringBuilder resultBuilder = new StringBuilder();

    while (scanner.hasNextLine()) {
      int n = Integer.parseInt(scanner.nextLine());

      if (n == 0) {
        break;
      }

      int lastRestaurantPos = INVALID_POSITION;
      int lastDrugStorePos = INVALID_POSITION;
      int minDistance = Integer.MAX_VALUE;

      char[] inputData = scanner.nextLine().toCharArray();

      for (int i = 0, m = inputData.length; i < m; i++) {
        char c = inputData[i];

        if (c == 'Z') {
          minDistance = 0;
          break;
        }

        if (c == 'R') {
          lastRestaurantPos = i;
        } else if (c == 'D') {
          lastDrugStorePos = i;
        }

        if (lastRestaurantPos != INVALID_POSITION && lastDrugStorePos != INVALID_POSITION) {
          int d = Math.abs(lastRestaurantPos - lastDrugStorePos);

          if (d < minDistance) {
            minDistance = d;
          }
        }
      }

      resultBuilder.append(minDistance).append("\n");
    }

    scanner.close();
    outputStream.print(resultBuilder.toString());
  }
}
