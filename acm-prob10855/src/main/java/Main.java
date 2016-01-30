import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class Main {
  private static final int NUMBER_OF_ORIENTATIONS = 4;

  public static void main(String... args) {
    processInput(System.in, System.out);
  }

  public static void processInput(InputStream inStream, PrintStream outStream) {
    Scanner scanner = new Scanner(inStream);
    StringBuilder resultBuilder = new StringBuilder();

    while (scanner.hasNextLine()) {
      String dimensionStr = scanner.nextLine();
      String[] dimensionValues = dimensionStr.split("\\s+");

      if (dimensionValues.length != 2) {
        throw new RuntimeException(String.format("invalid input line : %s", dimensionStr));
      }

      final int m = Integer.parseInt(dimensionValues[0]);
      final int n = Integer.parseInt(dimensionValues[1]);

      if (m == 0 && n == 0) {
        break;
      }

      char[][] bigMatrix = new char[m][];
      char[][] smallMatrix = new char[n][];

      for (int i = 0; i < m; i++) {
        String line = scanner.nextLine().trim();
        bigMatrix[i] = line.toCharArray();
      }

      for (int i = 0; i < n; i++) {
        String line = scanner.nextLine().trim();
        smallMatrix[i] = line.toCharArray();
      }

      final int maxIndex = m - n;

      for (int k = 0; k < NUMBER_OF_ORIENTATIONS; k++) {
        int count = 0;

        for (int i = 0; i <= maxIndex; i++) {
          for (int j = 0; j <= maxIndex; j++) {
            if (matches(bigMatrix, smallMatrix, i, j)) {
              count++;
            }
          }
        }

        smallMatrix = rotate(smallMatrix);
        resultBuilder.append(count);

        if (k == NUMBER_OF_ORIENTATIONS - 1) {
          resultBuilder.append("\n");
        } else {
          resultBuilder.append(" ");
        }
      }
    }

    scanner.close();
    outStream.print(resultBuilder.toString());
  }

  private static boolean matches(char[][] bigMatrix, char[][] smallMatrix, int x, int y) {
    final int m = bigMatrix.length;
    final int n = smallMatrix.length;

    if (x + n > m) {
      return false;
    }

    if (y + n > m) {
      return false;
    }

    for (int i = 0; i < n; i++) {
      for (int j = 0; j < n; j++) {
        if (smallMatrix[i][j] != bigMatrix[x + i][y + j]) {
          return false;
        }
      }
    }

    return true;
  }

  private static char[][] rotate(char[][] matrix) {
    final int n = matrix.length;
    char[][] rotatedMatrix = new char[n][n];

    for (int i = 0; i < n; i++) {
      int k = n - 1 - i;

      for (int j = 0; j < n; j++) {
        rotatedMatrix[j][k] = matrix[i][j];
      }
    }

    return rotatedMatrix;
  }
}
