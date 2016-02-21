import java.io.InputStream;
import java.io.PrintStream;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Main {
  private enum FloorColor {
    RED, BLUE
  };

  public static void main(String... args) {
    processInput(System.in, System.out);
  }

  public static void processInput(InputStream inStream, PrintStream outStream) {
    Scanner scanner = new Scanner(inStream);
    StringBuilder resultBuilder = new StringBuilder();
    final int numberOfCases = scanner.nextInt();

    for (int i = 0; i < numberOfCases; i++) {
      final int n = scanner.nextInt();
      PriorityQueue<Integer> redSizeQueue = new PriorityQueue<>();
      PriorityQueue<Integer> blueSizeQueue = new PriorityQueue<>();

      for (int j = 0; j < n; j++) {
        int size = scanner.nextInt();

        if (size < 0) {
          redSizeQueue.add(Math.abs(size));
        } else {
          blueSizeQueue.add(size);
        }
      }

      // corner cases
      if (redSizeQueue.isEmpty() && blueSizeQueue.isEmpty()) {
        resultBuilder.append(0).append("\n");
        continue;
      }

      if (redSizeQueue.isEmpty() || blueSizeQueue.isEmpty()) {
        resultBuilder.append(1).append("\n");
        continue;
      }

      int[] sizeArray = new int[n];
      int j = 0;
      FloorColor currentColor;

      // choose the smaller floor size at the beginning
      if (redSizeQueue.peek() < blueSizeQueue.peek()) {
        sizeArray[j++] = redSizeQueue.poll();
        currentColor = FloorColor.RED;
      } else {
        sizeArray[j++] = blueSizeQueue.poll();
        currentColor = FloorColor.BLUE;
      }

      while (true) {
        if (currentColor == FloorColor.RED) {
          if (blueSizeQueue.isEmpty()) {
            break;
          }

          if (blueSizeQueue.peek() < sizeArray[j - 1]) {
            blueSizeQueue.poll();
          } else {
            sizeArray[j++] = blueSizeQueue.poll();
            currentColor = FloorColor.BLUE;
          }
        } else if (currentColor == FloorColor.BLUE) {
          if (redSizeQueue.isEmpty()) {
            break;
          }

          if (redSizeQueue.peek() < sizeArray[j - 1]) {
            redSizeQueue.poll();
          } else {
            sizeArray[j++] = redSizeQueue.poll();
            currentColor = FloorColor.RED;
          }
        }
      }

      resultBuilder.append(j).append("\n");
    }

    scanner.close();
    outStream.print(resultBuilder.toString());
  }
}
