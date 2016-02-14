import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class Main {
  private static final char BLANK = ' ';
  
  public static void main(String... args) {
    processInput(System.in, System.out);
  }

  public static void processInput(InputStream inStream, PrintStream outStream) {
    Scanner scanner = new Scanner(inStream);
    StringBuilder resultBuilder = new StringBuilder();

    while (true) {
      int rowCount = Integer.parseInt(scanner.nextLine().trim());

      if (rowCount == 0) {
        break;
      }

      int[] gapLengths = new int[rowCount];
      int minLength = Integer.MAX_VALUE;

      for (int i = 0; i < rowCount; i++) {
        String line = scanner.nextLine().trim();

        int p = line.indexOf(BLANK);
        int q = line.lastIndexOf(BLANK);
        
        if (p >= 0 && q >= 0) {
          gapLengths[i] = q - p + 1;
        } else {
          gapLengths[i] = 0;
        }

        if (minLength > gapLengths[i]) {
          minLength = gapLengths[i];
        }
      }

      int sum = 0;

      for (int i = 0; i < rowCount; i++) {
        sum += (gapLengths[i] - minLength);
      }

      resultBuilder.append(sum).append("\n");
    }

    scanner.close();
    outStream.print(resultBuilder.toString());
  }
}
