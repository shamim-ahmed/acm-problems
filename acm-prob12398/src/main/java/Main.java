import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class Main {
  private static final int DIMENSION = 3;

  public static void main(String... args) {
    processInput(System.in, System.out);
  }

  public static void processInput(InputStream inStream, PrintStream outStream) {
    Scanner scanner = new Scanner(inStream);
    StringBuilder resultBuilder = new StringBuilder();
    int counter = 0;

    while (scanner.hasNextLine()) {
      String line = scanner.nextLine();
      int[][] matrix = convertMovesToInitialConfiguration(line);
      counter++;
      
      resultBuilder.append(String.format("Case #%d:%n", counter));
      
      for (int i = 0; i < DIMENSION; i++) {
        for (int j = 0; j < DIMENSION; j++) {
          if (j == DIMENSION - 1) {
            resultBuilder.append(String.format("%d%n", matrix[i][j]));
          } else {
            resultBuilder.append(String.format("%d ", matrix[i][j]));
          }
        }
      }
    }

    scanner.close();
    outStream.print(resultBuilder.toString());
  }

  private static int[][] convertMovesToInitialConfiguration(String input) {
    int[][] matrix = new int[DIMENSION][DIMENSION];
    char[] charArray = input.toCharArray();

    for (int k = charArray.length - 1; k >= 0; k--) {
      char c = charArray[k];
      int value = c - 'a';
      int i = value / DIMENSION;
      int j = value % DIMENSION;

      incrementValues(matrix, i, j);
    }

    return matrix;
  }

  private static void incrementValues(int[][] matrix, int i, int j) {
    if (isValidLocation(i, j)) {
      matrix[i][j] = (matrix[i][j] + 1) % 10;
    }

    if (isValidLocation(i - 1, j)) {
      matrix[i - 1][j] = (matrix[i - 1][j] + 1) % 10;
    }

    if (isValidLocation(i + 1, j)) {
      matrix[i + 1][j] = (matrix[i + 1][j] + 1) % 10;
    }

    if (isValidLocation(i, j - 1)) {
      matrix[i][j - 1] = (matrix[i][j - 1] + 1) % 10;
    }

    if (isValidLocation(i, j + 1)) {
      matrix[i][j + 1] = (matrix[i][j + 1] + 1) % 10;
    }
  }

  private static boolean isValidLocation(int row, int column) {
    return row >= 0 && row < DIMENSION && column >= 0 && column < DIMENSION;
  }
}
