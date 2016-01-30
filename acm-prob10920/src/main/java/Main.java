import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class Main {
  private enum Direction {
    UP, LEFT, DOWN, RIGHT
  };

  public static void main(String... args) {
    processInput(System.in, System.out);
  }

  public static void processInput(InputStream inStream, PrintStream outStream) {
    Scanner scanner = new Scanner(inStream);
    StringBuilder resultBuilder = new StringBuilder();

    while (scanner.hasNextInt()) {
      final int dimension = scanner.nextInt();
      final int spiralPosition = scanner.nextInt();

      if (dimension == 0 && spiralPosition == 0) {
        break;
      }

      int startValue = dimension / 2;
      int topIndex = startValue;
      int bottomIndex = startValue;
      int leftIndex = startValue;
      int rightIndex = startValue;
      Direction currentDirection = Direction.UP;
      int i = startValue;
      int j = startValue;
      int count = 1;

      while (count < spiralPosition) {
        if (currentDirection == Direction.UP) {
          while (isValidIndex(i, dimension) && i >= topIndex) {
            i--;
            count++;
          }

          topIndex = i;
          currentDirection = Direction.LEFT;
        } else if (currentDirection == Direction.LEFT) {
          while (isValidIndex(j, dimension) && j >= leftIndex) {
            j--;
            count++;
          }

          leftIndex = j;
          currentDirection = Direction.DOWN;
        } else if (currentDirection == Direction.DOWN) {
          while (isValidIndex(i, dimension) && i <= bottomIndex) {
            i++;
            count++;
          }

          bottomIndex = i;
          currentDirection = Direction.RIGHT;
        } else if (currentDirection == Direction.RIGHT) {
          while (isValidIndex(j, dimension) && j <= rightIndex) {
            j++;
            count++;
          }

          rightIndex = j;
          currentDirection = Direction.UP;
        }
      }

      int x = dimension - i;
      int y = j + 1;
      resultBuilder.append(String.format("Line = %d, column = %d.%n", x, y));
    }

    scanner.close();
    outStream.print(resultBuilder.toString());
  }

  private static boolean isValidIndex(int index, int dimension) {
    return index >= 0 && index < dimension;
  }
}
