import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class Main {
  private static final String ESCAPE_MSG = "Escaped again! More 2D grid problems!";
  private static final String CAPTURE_MSG = "Party time! Let’s find a restaurant!";

  public static void main(String... args) {
    processInput(System.in, System.out);
  }

  public static void processInput(InputStream inStream, PrintStream outStream) {
    Scanner scanner = new Scanner(inStream);
    StringBuilder resultBuilder = new StringBuilder();
    int caseCount = 0;

    while (scanner.hasNextInt()) {
      caseCount++;

      // read parameters for the current case
      int numberOfRows = scanner.nextInt();
      int numberOfColumns = scanner.nextInt();
      int numberOfProblemSetters = scanner.nextInt();

      if (numberOfRows == 0 && numberOfColumns == 0 && numberOfProblemSetters == 0) {
        break;
      }

      boolean[] rowFlags = new boolean[numberOfRows];
      boolean[] columnFlags = new boolean[numberOfColumns];

      for (int k = 1; k <= numberOfProblemSetters; k++) {
        int r = scanner.nextInt();
        int c = scanner.nextInt();
        rowFlags[r] = true;
        columnFlags[c] = true;
      }

      // now read Arif's position and check if he can escape
      int row = scanner.nextInt();
      int column = scanner.nextInt();

      if (isEscapePossible(row, column, rowFlags, columnFlags)) {
        resultBuilder.append(String.format("Case %d: %s%n", caseCount, ESCAPE_MSG));
      } else {
        resultBuilder.append(String.format("Case %d: %s%n", caseCount, CAPTURE_MSG));
      }
    }

    scanner.close();
    outStream.print(resultBuilder.toString());
  }

  private static boolean isEscapePossible(int r, int c, boolean[] rowFlags, boolean[] columnFlags) {
    if (isFree(r, c, rowFlags, columnFlags)) {
      return true;
    }

    if (isFree(r - 1, c, rowFlags, columnFlags)) {
      return true;
    }

    if (isFree(r + 1, c, rowFlags, columnFlags)) {
      return true;
    }

    if (isFree(r, c - 1, rowFlags, columnFlags)) {
      return true;
    }

    if (isFree(r, c + 1, rowFlags, columnFlags)) {
      return true;
    }

    return false;
  }

  private static boolean isFree(int r, int c, boolean[] rowFlags, boolean[] columnFlags) {
    return r >= 0 && r < rowFlags.length && c >= 0 && c < columnFlags.length && !rowFlags[r]
        && !columnFlags[c];
  }
}
