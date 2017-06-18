import java.io.InputStream;
import java.io.PrintStream;
import java.util.LinkedList;
import java.util.PriorityQueue;
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

    for (int i = 0; i < numberOfCases; i++) {
      int ferryCapacity = scanner.nextInt();
      int timeToCross = scanner.nextInt();
      int numberOfLines = scanner.nextInt();
      
      Queue<ArrivalEvent> leftQueue = new LinkedList<>();
      Queue<ArrivalEvent> rightQueue = new LinkedList<>();
      PriorityQueue<Duration> durationQueue = new PriorityQueue<>();

      populateQueues(leftQueue, rightQueue, scanner, numberOfLines);

      String currentSide = LEFT_SIDE;
      int currentTime = 0;

      while (!leftQueue.isEmpty() || !rightQueue.isEmpty()) {
        Queue<ArrivalEvent> currentQueue, otherQueue;

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
           loadCars(currentQueue, durationQueue, ferryCapacity, currentTime, timeToCross);

          // move to the other side
          if (LEFT_SIDE.equals(currentSide)) {
            currentSide = RIGHT_SIDE;
          } else {
            currentSide = LEFT_SIDE;
          }

          currentTime += timeToCross;
        } else {
          currentTime = findMinimum(currentQueue, otherQueue);
        }
      }
      
      while (!durationQueue.isEmpty()) {
        Duration duration = durationQueue.poll();
        resultBuilder.append(duration.getEnd()).append("\n");      
      }

      if (i < numberOfCases - 1) {
        resultBuilder.append("\n");
      }
    }

    scanner.close();
    outStream.print(resultBuilder.toString());
  }

  private static void populateQueues(Queue<ArrivalEvent> leftQueue, Queue<ArrivalEvent> rightQueue,
      Scanner scanner, int numberOfLines) {
    for (int j = 0; j < numberOfLines; j++) {
      int arrivalTime = scanner.nextInt();
      String side = scanner.next();
      ArrivalEvent event = new ArrivalEvent(arrivalTime, j);

      if (LEFT_SIDE.equals(side)) {
        leftQueue.offer(event);
      } else if (RIGHT_SIDE.equals(side)) {
        rightQueue.offer(event);
      }
    }
  }

  private static int findMinimum(Queue<ArrivalEvent> firstQueue, Queue<ArrivalEvent> secondQueue) {
    int x = Integer.MAX_VALUE;
    int y = Integer.MAX_VALUE;

    if (!firstQueue.isEmpty()) {
      x = firstQueue.peek().getTime();
    }

    if (!secondQueue.isEmpty()) {
      y = secondQueue.peek().getTime();
    }

    return x < y ? x : y;

  }

  private static boolean queueHasCar(Queue<ArrivalEvent> queue, int currentTime) {
    boolean result = false;

    if (!queue.isEmpty()) {
      int value = queue.peek().getTime();

      if (value <= currentTime) {
        result = true;
      }
    }

    return result;

  }

  private static void loadCars(Queue<ArrivalEvent> currentQueue, PriorityQueue<Duration> durationQueue, int capacity, int currentTime, int timeToCross) {
    if (currentQueue.isEmpty()) {
      return;
    }

    int count = 0;
    int endTime = currentTime + timeToCross;

    while (!currentQueue.isEmpty() && count < capacity) {
      Integer val = currentQueue.peek().getTime();

      if (val > currentTime) {
        break;
      }

      ArrivalEvent event = currentQueue.poll();
      int startTime = event.getTime();
      int order = event.getOrder();
      Duration duration = new Duration(startTime, endTime, order);
      durationQueue.offer(duration);
      count++;
    }
  }
  
  private static class ArrivalEvent {
    private final int time;    
    private final int order;
    
    public ArrivalEvent(int time, int order) {
      this.time = time;
      this.order = order;
    }
    
    public int getTime() {
      return time;
    }

    public int getOrder() {
      return order;
    }
  }
  
  private static class Duration implements Comparable<Duration> {
    private final int start;    
    private final int end;
    private final int order;
    
    public Duration(int start, int end, int order) {
      this.start = start;
      this.end = end;
      this.order = order;
    }
    
    public int getStart() {
      return start;
    }

    public int getEnd() {
      return end;
    }
    
    public int getOrder() {
      return order;
    }

    @Override
    public int compareTo(Duration otherDuration) {
      int otherStart = otherDuration.getStart();
      
      if (start < otherStart) {
        return -1;
      }
      
      if (start > otherStart) {
        return 1;
      }
      
      int otherOrder = otherDuration.getOrder();
      
      if (order < otherOrder) {
        return -1;
      }
      
      if (order > otherOrder) {
        return 1;
      }
      
      return 0;
    }    
  }
}
