import java.io.InputStream;
import java.io.PrintStream;
import java.util.LinkedList;
import java.util.Scanner;

public class Main {
  private static final int MAX = 1000;

  public static void main(String... args) {
    processInput(System.in, System.out);
  }

  public static void processInput(InputStream inStream, PrintStream outStream) {
    Scanner scanner = new Scanner(inStream);
    StringBuilder resultBuilder = new StringBuilder();

    int caseCount = 0;

    while (scanner.hasNextInt()) {
      int population = scanner.nextInt();
      int numberOfCommands = scanner.nextInt();

      if (population == 0 && numberOfCommands == 0) {
        // end of input
        break;
      }

      caseCount++;
      resultBuilder.append(String.format("Case %d:%n", caseCount));

      LinkedList<Integer> queue = initializeQueue(population);

      for (int i = 0; i < numberOfCommands; i++) {
        String command = scanner.next().trim();

        switch (command) {
          case "N": {
            Integer p = queue.poll();
            resultBuilder.append(p).append("\n");
            queue.offer(p);

            break;
          }

          case "E": {
            Integer p = scanner.nextInt();
            queue.remove(p);
            queue.addFirst(p);

            break;
          }

          default: {
            break;
          }
        }
      }
    }

    scanner.close();
    outStream.print(resultBuilder.toString());
  }

  private static LinkedList<Integer> initializeQueue(int n) {
    LinkedList<Integer> queue = new LinkedList<>();
    int size = n;

    if (size > MAX) {
      size = MAX;
    }

    for (int i = 1; i <= size; i++) {
      queue.offer(i);
    }

    return queue;
  }
}
