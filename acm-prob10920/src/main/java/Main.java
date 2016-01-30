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

      while (count < spiralPosition && isValidIndex(i, dimension) && isValidIndex(j, dimension)) {
        if (currentDirection == Direction.UP) {
          int delta = i - topIndex + 1;

          if (delta > spiralPosition - count) {
            delta = spiralPosition - count;
          }

          count += delta;
          i -= delta;

          if (count < spiralPosition) {
            topIndex = i;
            currentDirection = Direction.LEFT;
          }
        } else if (currentDirection == Direction.LEFT) {
          int delta = j - leftIndex + 1;

          if (delta > spiralPosition - count) {
            delta = spiralPosition - count;
          }

          count += delta;
          j -= delta;

          if (count < spiralPosition) {
            leftIndex = j;
            currentDirection = Direction.DOWN;
          }
        } else if (currentDirection == Direction.DOWN) {
          int delta = bottomIndex - i + 1;

          if (delta > spiralPosition - count) {
            delta = spiralPosition - count;
          }

          count += delta;
          i += delta;
          
          if (count < spiralPosition) {
            bottomIndex = i;
            currentDirection = Direction.RIGHT;
          }
        } else if (currentDirection == Direction.RIGHT) {
          int delta = rightIndex - j + 1;

          if (delta > spiralPosition - count) {
            delta = spiralPosition - count;
          }

          count += delta;
          j += delta;

          if (count < spiralPosition) {
            rightIndex = j;
            currentDirection = Direction.UP;
          }
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
