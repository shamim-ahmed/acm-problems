import java.io.InputStream;
import java.io.PrintStream;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
  private static final String LEFT_SIDE = "left";
  private static final String RIGHT_SIDE = "right";

  public static void main(String... args) {
    processInput(System.in, System.out);
  }

  public static void processInput(InputStream inStream, PrintStream outStream) {
    Scanner scanner = new Scanner(inStream);
    StringBuilder resultBuilder = new StringBuilder();

    int numberOfCases = scanner.nextInt();
    Queue<Integer> leftQueue = new LinkedList<>();
    Queue<Integer> rightQueue = new LinkedList<>();

    for (int i = 0; i < numberOfCases; i++) {
      int ferryCapacity = scanner.nextInt();
      int timeToCross = scanner.nextInt();
      int numberOfLines = scanner.nextInt();

      populateQueues(leftQueue, rightQueue, scanner, numberOfLines);

      String currentSide = LEFT_SIDE;
      int currentTime = 0;

      while (!leftQueue.isEmpty() || !rightQueue.isEmpty()) {
        Queue<Integer> currentQueue, otherQueue;

        if (LEFT_SIDE.equals(currentSide)) {
          currentQueue = leftQueue;
          otherQueue = rightQueue;
        } else {
          currentQueue = rightQueue;
          otherQueue = leftQueue;
        }

        boolean shouldCross =
            queueHasCar(currentQueue, currentTime) || queueHasCar(otherQueue, currentTime);

        if (shouldCross) {
          int n = loadCars(currentQueue, currentTime, ferryCapacity);

          // move to the other side
          if (LEFT_SIDE.equals(currentSide)) {
            currentSide = RIGHT_SIDE;
          } else {
            currentSide = LEFT_SIDE;
          }

          currentTime += timeToCross;

          for (int j = 0; j < n; j++) {
            resultBuilder.append(currentTime).append("\n");
          }
        } else {
          currentTime = findMinimum(currentQueue, otherQueue);
        }
      }

      if (i < numberOfCases - 1) {
        resultBuilder.append("\n");
      }
    }

    scanner.close();
    outStream.print(resultBuilder.toString());
  }

  private static void populateQueues(Queue<Integer> leftQueue, Queue<Integer> rightQueue,
      Scanner scanner, int numberOfLines) {
    for (int j = 0; j < numberOfLines; j++) {
      int arrivalTime = scanner.nextInt();
      String side = scanner.next();

      if (LEFT_SIDE.equals(side)) {
        leftQueue.offer(arrivalTime);
      } else if (RIGHT_SIDE.equals(side)) {
        rightQueue.offer(arrivalTime);
      }
    }
  }

  private static int findMinimum(Queue<Integer> firstQueue, Queue<Integer> secondQueue) {
    int x = Integer.MAX_VALUE;
    int y = Integer.MAX_VALUE;

    if (!firstQueue.isEmpty()) {
      x = firstQueue.peek();
    }

    if (!secondQueue.isEmpty()) {
      y = secondQueue.peek();
    }

    return x < y ? x : y;

  }

  private static boolean queueHasCar(Queue<Integer> queue, int currentTime) {
    boolean result = false;

    if (!queue.isEmpty()) {
      int value = queue.peek();

      if (value <= currentTime) {
        result = true;
      }
    }

    return result;

  }

  private static int loadCars(Queue<Integer> currentQueue, int currentTime, int capacity) {

    if (currentQueue.isEmpty()) {
      return 0;
    }

    int count = 0;

    while (!currentQueue.isEmpty() && count < capacity) {
      Integer val = currentQueue.peek();

      if (val > currentTime) {
        break;
      }

      currentQueue.poll();
      count++;
    }

    return count;
  }
}
