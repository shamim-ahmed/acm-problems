import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Main {
  private static final int SIZE = 8;

  public static void main(String... args) {
    Scanner scanner = new Scanner(System.in);
    int numberOfBoards = Integer.parseInt(scanner.nextLine());
    int[][][] chessBoards = new int[numberOfBoards][][];

    for (int k = 0; k < chessBoards.length; k++) {
      chessBoards[k] = readChessBoard(scanner);
    }

    printMaxValues(chessBoards);
  }

  private static int[][] readChessBoard(Scanner scanner) {
    int[][] board = new int[SIZE][SIZE];

    for (int i = 0; i < SIZE; i++) {
      String line = scanner.nextLine();
      StringTokenizer tokenizer = new StringTokenizer(line, " ", false);

      if (tokenizer.countTokens() != SIZE) {
        throw new RuntimeException(String.format("Invalid input line: %s%n", line));
      }

      int j = 0;

      while (tokenizer.hasMoreTokens()) {
        board[i][j++] = Integer.parseInt(tokenizer.nextToken());
      }
    }

    return board;
  }

  private static void printMaxValues(int[][][] chessBoards) {
    List<Coordinate[]> configurations = new NQueensSolver(SIZE).findSolution();

    for (int i = 0; i < chessBoards.length; i++) {
      int maxSum = 0;

      for (Coordinate[] config : configurations) {
        int sum = addValuesForBoard(chessBoards[i], config);

        if (sum > maxSum) {
          maxSum = sum;
        }
      }

      System.out.printf("%5d%n", maxSum);
    }
  }

  private static int addValuesForBoard(int[][] board, Coordinate[] points) {
    int sum = 0;

    for (Coordinate point : points) {
      sum += board[point.getX()][point.getY()];
    }

    return sum;
  }

  private static class NQueensSolver {
    private final int n;
    private final int[][] board;
    private final List<Coordinate[]> solutions;
    private static final int[] HORIZONTAL_INCREMENTS = { -1, 1 };
    private static final int[] VERTICAL_INCREMENTS = { -1, 1 };

    public NQueensSolver(int size) {
      n = size;
      board = new int[n][n];
      solutions = new ArrayList<Coordinate[]>();
    }

    public List<Coordinate[]> findSolution() {
      solve(0, new Coordinate[n]);
      return solutions;
    }

    private void solve(int row, Coordinate[] coordinates) {
      if (row == n) {
        solutions.add(Arrays.copyOf(coordinates, coordinates.length));
        return;
      }

      for (int j = 0; j < n; j++) {
        if (board[row][j] == 0) {
          occupy(row, j);
          coordinates[row] = new Coordinate(row, j);
          solve(row + 1, coordinates);
          coordinates[row] = null;
          unOccupy(row, j);
        }
      }
    }

    private void occupy(int x, int y) {
      addValue(x, y, 1);
    }

    private void unOccupy(int x, int y) {
      addValue(x, y, -1);
    }

    private void addValue(int x, int y, int value) {
      for (int i = 0; i < n; i++) {
        board[i][y] += value;
      }

      for (int j = 0; j < n; j++) {
        if (j != y) {
          board[x][j] += value;
        }
      }

      for (int dx : HORIZONTAL_INCREMENTS) {
        for (int dy : VERTICAL_INCREMENTS) {
          int i = x + dx;
          int j = y + dy;

          while (withinBoard(i, j)) {
            board[i][j] += value;
            i += dx;
            j += dy;
          }
        }
      }
    }

    private boolean withinBoard(int x, int y) {
      return x >= 0 && x < n && y >= 0 && y < n;
    }
  }

  private static class Coordinate {
    private final int x;
    private final int y;

    public Coordinate(int x, int y) {
      this.x = x;
      this.y = y;
    }

    public int getX() {
      return x;
    }

    public int getY() {
      return y;
    }

    @Override
    public String toString() {
      return String.format("(%d, %d)", x, y);
    }
  }
}
