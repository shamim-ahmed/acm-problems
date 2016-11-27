import java.io.InputStream;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Scanner;

public class Main {
  private static final String ENQUEUE_COMMNAD = "ENQUEUE";
  private static final String DEQUEUE_COMMNAD = "DEQUEUE";
  private static final String STOP_COMMAND = "STOP";

  public static void main(String... args) {
    processInput(System.in, System.out);
  }

  public static void processInput(InputStream inStream, PrintStream outStream) {
    StringBuilder resultBuilder = new StringBuilder();
    Scanner scanner = new Scanner(inStream);
    int scenarioCount = 0;

    while (scanner.hasNextInt()) {
      int numberOfTeams = scanner.nextInt();

      if (numberOfTeams == 0) {
        // end of input
        break;
      }

      scenarioCount++;

      resultBuilder.append(String.format("Scenario #%d%n", scenarioCount));

      Map<Integer, Integer> teamMap = new HashMap<>();
      LinkedList<LinkedList<Integer>> teamQueue = new LinkedList<>();

      for (int i = 0; i < numberOfTeams; i++) {
        teamQueue.add(new LinkedList<Integer>());
        int memberCount = scanner.nextInt();

        for (int j = 0; j < memberCount; j++) {
          int member = scanner.nextInt();
          teamMap.put(member, i);
        }
      }

      boolean done = false;
      // This queue keeps track of the order of processing of teams
      LinkedList<Integer> teamOrderQueue = new LinkedList<>();

      while (!done) {
        String command = scanner.next().trim();

        if (STOP_COMMAND.equals(command)) {
          done = true;
          continue;
        }

        if (ENQUEUE_COMMNAD.equals(command)) {
          int member = scanner.nextInt();
          int team = teamMap.get(member);

          LinkedList<Integer> memberQueue = teamQueue.get(team);

          if (memberQueue.isEmpty()) {
            teamOrderQueue.offer(team);
          }

          memberQueue.offer(member);
        } else if (DEQUEUE_COMMNAD.equals(command)) {
          if (!teamOrderQueue.isEmpty()) {
            int team = teamOrderQueue.peek();
            LinkedList<Integer> memberQueue = teamQueue.get(team);
            resultBuilder.append(memberQueue.poll()).append("\n");

            if (memberQueue.isEmpty()) {
              teamOrderQueue.poll();
            }
          }
        }
      }

      resultBuilder.append("\n");
    }


    scanner.close();
    outStream.print(resultBuilder.toString());
  }
}
