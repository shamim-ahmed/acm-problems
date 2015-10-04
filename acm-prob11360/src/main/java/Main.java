import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class Main {
  private static final int MAX = 10;
  private static final String ROW_COMMAND_PREFIX = "row";
  private static final String COL_COMMAND_PREFIX = "col";
  private static final String INC_COMMAND = "inc";
  private static final String DEC_COMMAND = "dec";
  private static final String TRANSPOSE_COMMAND = "transpose";

  public static void main(String... args) {
    processInput(System.in, System.out);
  }

  public static void processInput(InputStream inputStream, PrintStream outputStream) {
    Scanner scanner = new Scanner(inputStream);
    StringBuilder resultBuilder = new StringBuilder();

    final int numberOfCases = Integer.parseInt(scanner.nextLine());

    for (int k = 0; k < numberOfCases; k++) {
      int n = Integer.parseInt(scanner.nextLine());
      int[][] values = new int[n][n];

      for (int i = 0; i < n; i++) {
        String line = scanner.nextLine();

        for (int j = 0; j < n; j++) {
          values[i][j] = Integer.parseInt(String.valueOf(line.charAt(j)));
        }
      }

      int numberOfCommands = Integer.parseInt(scanner.nextLine());

      for (int i = 0; i < numberOfCommands; i++) {
        String command = scanner.nextLine();

        if (command.matches(INC_COMMAND)) {
          increment(values);
        } else if (command.matches(DEC_COMMAND)) {
          decrement(values);
        } else if (command.matches(TRANSPOSE_COMMAND)) {
          transpose(values);
        } else if (command.startsWith(ROW_COMMAND_PREFIX)) {

        } else if (command.startsWith(COL_COMMAND_PREFIX)) {

        }
      }
    }

    scanner.close();
    outputStream.print(resultBuilder.toString());
  }

  private static void increment(int[][] values) {
    for (int i = 0; i < values.length; i++) {
      for (int j = 0; j < values[i].length; j++) {
        values[i][j] = (values[i][j] + 1) % MAX;
      }
    }
  }

  private static void decrement(int[][] values) {
    for (int i = 0; i < values.length; i++) {
      for (int j = 0; j < values[i].length; j++) {
        values[i][j] = (values[i][j] - 1 + MAX) % MAX;
      }
    }
  }

  private static void transpose(int[][] values) {
    for (int i = 0; i < values.length; i++) {
      for (int j = 0; j < i; j++) {
        int temp = values[i][j];
        values[i][j] = values[j][i];
        values[j][i] = temp;
      }
    }
  }
}
