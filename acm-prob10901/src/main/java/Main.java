import java.io.InputStream;
import java.io.PrintStream;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
  private enum Bank {
    LEFT("left"), RIGHT("right");
    private String name;

    Bank(String name) {
      this.name = name;
    }

    public String toString() {
      return name;
    }
  };

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

      for (int j = 0; j < numberOfLines; j++) {
        int arrivalTime = scanner.nextInt();
        String side = scanner.next();

        if (Bank.LEFT.toString().equals(side)) {
          leftQueue.offer(arrivalTime);
        } else if (Bank.RIGHT.toString().equals(side)) {
          rightQueue.offer(arrivalTime);
        }
      }

      Ferry ferry = new Ferry(Bank.LEFT, 0, ferryCapacity);

      while (!leftQueue.isEmpty() || !rightQueue.isEmpty()) {
        Queue<Integer> currentQueue, otherQueue;

        if (ferry.getCurrentBank().equals(Bank.LEFT)) {
          currentQueue = leftQueue;
          otherQueue = rightQueue;
        } else {
          currentQueue = rightQueue;
          otherQueue = leftQueue;
        }

        boolean flag = ferry.shouldMove(currentQueue, otherQueue);

        if (flag) {
          ferry.move(currentQueue, timeToCross, resultBuilder);
        } else {
          int value1 = Integer.MAX_VALUE;
          int value2 = Integer.MAX_VALUE;

          if (!currentQueue.isEmpty()) {
            value1 = currentQueue.peek();
          }

          if (!otherQueue.isEmpty()) {
            value2 = otherQueue.peek();
          }

          int minValue = value1 < value2 ? value1 : value2;
          ferry.setCurrentTime(minValue);
        }
      }
      
      if (i < numberOfCases - 1) {
        resultBuilder.append("\n");
      }
    }

    scanner.close();
    outStream.print(resultBuilder.toString());
  }

  private static class Ferry {
    private Bank currentBank;
    private int currentTime;
    private int capacity;

    public Ferry(Bank currentBank, int currentTime, int capacity) {
      this.currentBank = currentBank;
      this.currentTime = currentTime;
      this.capacity = capacity;
    }

    public Bank getCurrentBank() {
      return currentBank;
    }

    public void setCurrentBank(Bank currentBank) {
      this.currentBank = currentBank;
    }

    public int getCurrentTime() {
      return currentTime;
    }

    public void setCurrentTime(int currentTime) {
      this.currentTime = currentTime;
    }

    public int getCapacity() {
      return capacity;
    }

    public void setCapacity(int capacity) {
      this.capacity = capacity;
    }

    public boolean shouldMove(Queue<Integer> currentQueue, Queue<Integer> otherQueue) {
      if (currentQueue.isEmpty() && otherQueue.isEmpty()) {
        return false;
      }

      return queueHasCar(currentQueue) || queueHasCar(otherQueue);
    }

    private boolean queueHasCar(Queue<Integer> queue) {
      boolean result = false;

      if (!queue.isEmpty()) {
        int value = queue.peek();

        if (value <= currentTime) {
          result = true;
        }
      }

      return result;

    }

    private int loadCars(Queue<Integer> currentQueue) {

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

    public void move(Queue<Integer> currentQueue, int timeToCross, StringBuilder resultBuilder) {
      int n = loadCars(currentQueue);

      // move to the other side
      if (Bank.LEFT.equals(currentBank)) {
        currentBank = Bank.RIGHT;
      } else {
        currentBank = Bank.LEFT;
      }

      currentTime += timeToCross;

      for (int i = 0; i < n; i++) {
        resultBuilder.append(currentTime).append("\n");
      }

    }
  }
}
