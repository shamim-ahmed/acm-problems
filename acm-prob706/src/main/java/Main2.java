import java.util.Scanner;
import java.util.StringTokenizer;

public class Main2 {
  private static final String EMPTY_LINE = "";
  private static final String TERMINATING_NUBMER = "0";

  public static void main(String... args) {
    Scanner scanner = null;

    try {
      scanner = new Scanner(System.in);

      while (scanner.hasNextLine()) {
        String line = scanner.nextLine().trim();

        if (line.equals(EMPTY_LINE)) {
          continue;
        }

        StringTokenizer tokenizer = new StringTokenizer(line, " ", false);

        if (tokenizer.countTokens() != 2) {
          // invalid input line
          continue;
        }

        int size = Integer.parseInt(tokenizer.nextToken());
        String numberStr = tokenizer.nextToken();

        if (size == 0 && numberStr.equals(TERMINATING_NUBMER)) {
          break;
        }

        processNumber(size, numberStr);
      }
    } finally {
      if (scanner != null) {
        scanner.close();
      }
    }
  }

  private static void processNumber(int size, String numberStr) {
    int n = numberStr.length();
    int rows = 2 * size + 3;
    int columns = n * (size + 3) - 1;

    boolean[][] pixelMatrix = new boolean[rows][columns];

    for (int i = 0; i < n; i++) {
      char c = numberStr.charAt(i);
      generatePatternForDigit(c, size, i, pixelMatrix);
    }

    printNumber(pixelMatrix, size);
  }

  private static void printNumber(boolean[][] matrix, int size) {
    for (int i = 0; i < matrix.length; i++) {
      for (int j = 0; j < matrix[i].length; j++) {
        if (i == 0 || i == (size + 1) || i == 2 * (size + 1)) {
          System.out.printf("%s", matrix[i][j] ? "-" : " ");
        } else {
          System.out.printf("%s", matrix[i][j] ? "|" : " ");
        }
      }

      System.out.println();
    }

    System.out.println();
  }

  private static void generatePatternForDigit(char digit, int size, int position, boolean[][] matrix) {
    int y = position * (size + 3);

    switch (digit) {
      case '0':
        generatePatternForZero(y, size, matrix);
        break;
      case '1':
        generatePatternForOne(y, size, matrix);
        break;
      case '2':
        generatePatternForTwo(y, size, matrix);
        break;
      case '3':
        generatePatternForThree(y, size, matrix);
        break;
      case '4':
        generatePatternForFour(y, size, matrix);
        break;
      case '5':
        generatePatternForFive(y, size, matrix);
        break;
      case '6':
        generatePatternForSix(y, size, matrix);
        break;
      case '7':
        generatePatternForSeven(y, size, matrix);
        break;
      case '8':
        generatePatternForEight(y, size, matrix);
        break;
      case '9':
        generatePatternForNine(y, size, matrix);
        break;
      default:
        throw new RuntimeException(String.format("Invalid digit: %c", digit));
    }
  }

  private static void generatePatternForZero(int columnIndex, int size, boolean[][] matrix) {
    int x = 0;
    int y = columnIndex;

    turnHorizontalRowOn(x, y, size, matrix);

    for (int k = 1; k <= 2; k++) {
      for (int i = 1; i <= size; i++) {
        x++;
        turnBothColumnOn(x, y, size, matrix);
      }

      x++;
    }

    turnHorizontalRowOn(x, y, size, matrix);
  }

  private static void generatePatternForOne(int columnIndex, int size, boolean[][] matrix) {
    int x = 0;
    int y = columnIndex;

    for (int k = 1; k <= 2; k++) {
      for (int i = 1; i <= size; i++) {
        x++;
        turnRightColumnOn(x, y, size, matrix);
      }

      x++;
    }
  }

  private static void generatePatternForTwo(int columnIndex, int size, boolean[][] matrix) {
    int x = 0;
    int y = columnIndex;

    turnHorizontalRowOn(x, y, size, matrix);

    for (int i = 1; i <= size; i++) {
      x++;
      turnRightColumnOn(x, y, size, matrix);
    }

    x++;
    turnHorizontalRowOn(x, y, size, matrix);

    for (int i = 1; i <= size; i++) {
      x++;
      turnLeftColumnOn(x, y, size, matrix);
    }

    x++;
    turnHorizontalRowOn(x, y, size, matrix);
  }

  private static void generatePatternForThree(int columnIndex, int size, boolean[][] matrix) {
    int x = 0;
    int y = columnIndex;

    for (int k = 1; k <= 2; k++) {
      turnHorizontalRowOn(x, y, size, matrix);

      for (int i = 1; i <= size; i++) {
        x++;
        turnRightColumnOn(x, y, size, matrix);
      }

      x++;
    }

    turnHorizontalRowOn(x, y, size, matrix);
  }

  private static void generatePatternForFour(int columnIndex, int size, boolean[][] matrix) {
    int x = 0;
    int y = columnIndex;

    for (int i = 1; i <= size; i++) {
      x++;
      turnBothColumnOn(x, y, size, matrix);
    }

    x++;
    turnHorizontalRowOn(x, y, size, matrix);

    for (int i = 1; i <= size; i++) {
      x++;
      turnRightColumnOn(x, y, size, matrix);
    }
  }

  private static void generatePatternForFive(int columnIndex, int size, boolean[][] matrix) {
    int x = 0;
    int y = columnIndex;

    turnHorizontalRowOn(x, y, size, matrix);

    for (int i = 1; i <= size; i++) {
      x++;
      turnLeftColumnOn(x, y, size, matrix);
    }

    x++;
    turnHorizontalRowOn(x, y, size, matrix);

    for (int i = 1; i <= size; i++) {
      x++;
      turnRightColumnOn(x, y, size, matrix);
    }

    x++;
    turnHorizontalRowOn(x, y, size, matrix);
  }

  private static void generatePatternForSix(int columnIndex, int size, boolean[][] matrix) {
    int x = 0;
    int y = columnIndex;

    turnHorizontalRowOn(x, y, size, matrix);

    for (int i = 1; i <= size; i++) {
      x++;
      turnLeftColumnOn(x, y, size, matrix);
    }

    x++;
    turnHorizontalRowOn(x, y, size, matrix);

    for (int i = 1; i <= size; i++) {
      x++;
      turnBothColumnOn(x, y, size, matrix);
    }

    x++;
    turnHorizontalRowOn(x, y, size, matrix);
  }

  private static void generatePatternForSeven(int columnIndex, int size, boolean[][] matrix) {
    int x = 0;
    int y = columnIndex;

    turnHorizontalRowOn(x, y, size, matrix);

    for (int k = 1; k <= 2; k++) {
      for (int i = 1; i <= size; i++) {
        x++;
        turnRightColumnOn(x, y, size, matrix);
      }

      x++;
    }
  }

  private static void generatePatternForEight(int columnIndex, int size, boolean[][] matrix) {
    int x = 0;
    int y = columnIndex;

    turnHorizontalRowOn(x, y, size, matrix);

    for (int k = 1; k <= 2; k++) {
      for (int i = 1; i <= size; i++) {
        x++;
        turnBothColumnOn(x, y, size, matrix);
      }

      x++;
      turnHorizontalRowOn(x, y, size, matrix);
    }
  }

  private static void generatePatternForNine(int columnIndex, int size, boolean[][] matrix) {
    int x = 0;
    int y = columnIndex;

    turnHorizontalRowOn(x, y, size, matrix);

    for (int i = 1; i <= size; i++) {
      x++;
      turnBothColumnOn(x, y, size, matrix);
    }

    x++;
    turnHorizontalRowOn(x, y, size, matrix);

    for (int i = 1; i <= size; i++) {
      x++;
      turnRightColumnOn(x, y, size, matrix);
    }

    x++;
    turnHorizontalRowOn(x, y, size, matrix);
  }

  private static void turnHorizontalRowOn(int x, int y, int size, boolean[][] matrix) {
    for (int j = 1; j <= size; j++) {
      matrix[x][y + j] = true;
    }
  }

  private static void turnLeftColumnOn(int x, int y, int size, boolean[][] matrix) {
    matrix[x][y] = true;
  }

  private static void turnRightColumnOn(int x, int y, int size, boolean[][] matrix) {
    matrix[x][y + size + 1] = true;
  }

  private static void turnBothColumnOn(int x, int y, int size, boolean[][] matrix) {
    matrix[x][y] = true;
    matrix[x][y + size + 1] = true;
  }
}
