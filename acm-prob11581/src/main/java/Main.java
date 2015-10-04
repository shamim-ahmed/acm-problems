import java.io.InputStream;
import java.io.PrintStream;
import java.util.HashSet;
import java.util.Scanner;

public class Main {
  public static final int SIZE = 3;

  public static void main(String... args) {
    processInput(System.in, System.out);
  }

  public static void processInput(InputStream inputStream, PrintStream outputStream) {
    Scanner scanner = new Scanner(inputStream);
    StringBuilder resultBuilder = new StringBuilder();

    final int n = Integer.parseInt(scanner.nextLine());

    for (int k = 0; k < n; k++) {
      // remove the empty line from input
      scanner.nextLine();

      boolean[][] values = new boolean[SIZE][SIZE];

      for (int i = 0; i < SIZE; i++) {
        String s = scanner.nextLine().trim();

        for (int j = 0; j < SIZE; j++) {
          char c = s.charAt(j);
          values[i][j] = (c == '1');
        }
      }

      Grid grid = new Grid(values);
      HashSet<Grid> gridSet = new HashSet<>();
      gridSet.add(grid);
      int count = 0;

      while (true) {
        Grid nextGrid = grid.computeNextGrid();

        // check if we have found a repeated grid
        if (gridSet.contains(nextGrid)) {
          count = -1;
          break;
        }

        // we have found the grid consisting of all zeros
        if (nextGrid.isAllZeroGrid()) {
          break;
        }

        count++;
        gridSet.add(nextGrid);
        grid = nextGrid;
      }

      resultBuilder.append(count).append("\n");
    }

    scanner.close();
    outputStream.print(resultBuilder.toString());
  }

  private static class Grid {
    private static final int[] PRIME_VALUES = {7, 11, 13};

    private final boolean[][] values;

    public Grid(boolean[][] values) {
      this.values = values;
    }

    public Grid computeNextGrid() {
      boolean[][] newValues = new boolean[SIZE][SIZE];

      for (int i = 0; i < SIZE; i++) {
        for (int j = 0; j < SIZE; j++) {
          boolean b = false;

          if (isValidPosition(i - 1, j)) {
            b ^= values[i - 1][j];
          }

          if (isValidPosition(i, j - 1)) {
            b ^= values[i][j - 1];
          }

          if (isValidPosition(i + 1, j)) {
            b ^= values[i + 1][j];
          }

          if (isValidPosition(i, j + 1)) {
            b ^= values[i][j + 1];
          }

          newValues[i][j] = b;
        }
      }

      return new Grid(newValues);
    }

    public boolean isAllZeroGrid() {
      boolean result = true;

      for (int i = 0; i < SIZE; i++) {
        for (int j = 0; j < SIZE; j++) {
          if (values[i][j]) {
            result = false;
            break;
          }
        }
      }

      return result;
    }

    private boolean isValidPosition(int x, int y) {
      return x >= 0 && x < SIZE && y >= 0 && y < SIZE;
    }

    @Override
    public boolean equals(Object obj) {
      if (!(obj instanceof Grid)) {
        return false;
      }

      Grid otherGrid = (Grid) obj;
      boolean result = true;

      for (int i = 0; i < SIZE; i++) {
        for (int j = 0; j < SIZE; j++) {
          if (values[i][j] != otherGrid.values[i][j]) {
            result = false;
            break;
          }
        }
      }

      return result;

    }

    @Override
    public int hashCode() {
      int result = 17;

      for (int i = 0; i < values.length; i++) {
        int count = 0;

        for (int j = 0; j < values[i].length; j++) {
          if (values[i][j]) {
            count++;
          }
        }

        result += PRIME_VALUES[i] * count;
      }

      return result;
    }
  }
}
