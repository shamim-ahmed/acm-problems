import java.io.InputStream;
import java.io.PrintStream;
import java.util.Collections;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Main {
  
  public static void main(String... args) {
    processInput(System.in, System.out);
  }

  public static void processInput(InputStream inStream, PrintStream outStream) {
    StringBuilder resultBuilder = new StringBuilder();
    Scanner scanner = new Scanner(inStream);
    int numberOfCases = scanner.nextInt();

    for (int i = 0; i < numberOfCases; i++) {
      int numberOfJobs = scanner.nextInt();
      int myJobPosition = scanner.nextInt();

      LinkedList<PrintJob> jobQueue = new LinkedList<>();
      PriorityQueue<Integer> pQueue = new PriorityQueue<>(Collections.reverseOrder());

      for (int j = 0; j < numberOfJobs; j++) {
        int priority = scanner.nextInt();
        PrintJob job = new PrintJob(priority, j == myJobPosition);
        jobQueue.add(job);
        pQueue.offer(priority);
      }

      int time = processPrintJobs(jobQueue, pQueue);
      resultBuilder.append(time).append("\n");
    }

    scanner.close();
    outStream.print(resultBuilder.toString());
  }

  private static int processPrintJobs(LinkedList<PrintJob> jobQueue,
      PriorityQueue<Integer> pQueue) {
    int time = 0;
    boolean done = false;

    while (!done && !jobQueue.isEmpty()) {
      PrintJob job = jobQueue.peek();

      if (isHighestPriorityJob(job, pQueue)) {
        // print the job
        jobQueue.poll();
        time++;
        pQueue.poll();

        if (job.isOwnedByMe()) {
          done = true;
        }
      } else {
        // move job to the end of queue
        jobQueue.poll();
        jobQueue.offer(job);
      }

    }

    return time;
  }

  private static boolean isHighestPriorityJob(PrintJob job, PriorityQueue<Integer> pQueue) {
    if (pQueue.isEmpty()) {
      return true;
    }

    return job.getPriority() == pQueue.peek();
  }

  private static class PrintJob {
    private final Integer priority;
    private final boolean ownedByMe;

    public PrintJob(Integer priority, boolean ownedByMe) {
      this.priority = priority;
      this.ownedByMe = ownedByMe;
    }

    public int getPriority() {
      return priority;
    }

    public boolean isOwnedByMe() {
      return ownedByMe;
    }
  }
}
