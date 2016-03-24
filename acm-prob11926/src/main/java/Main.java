import java.io.InputStream;
import java.io.PrintStream;
import java.util.BitSet;
import java.util.Scanner;

public class Main {
  private static final int MAX = 1000000;
  private static final String CONFLICT_STR = "CONFLICT";
  private static final String NO_CONFLICT_STR = "NO CONFLICT";

  public static void main(String... args) {
    processInput(System.in, System.out);
  }

  public static void processInput(InputStream inStream, PrintStream outStream) {
    Scanner scanner = new Scanner(inStream);
    StringBuilder resultBuilder = new StringBuilder();

    while (scanner.hasNextInt()) {
      int numberOfOneTimeTasks = scanner.nextInt();
      int numberOfRepeatingTasks = scanner.nextInt();

      if (numberOfOneTimeTasks == 0 && numberOfRepeatingTasks == 0) {
        break;
      }

      BitSet schedule = new BitSet(MAX + 1);
      boolean conflictFound = false;

      for (int i = 0; i < numberOfOneTimeTasks; i++) {
        int start = scanner.nextInt();
        int end = scanner.nextInt();

        if (!conflictFound) {
          conflictFound = scanInterval(schedule, start, end);
        }
      }

      for (int i = 0; i < numberOfRepeatingTasks; i++) {
        int start = scanner.nextInt();
        int end = scanner.nextInt();
        int repeatInterval = scanner.nextInt();
        int delta = end - start;

        for (int p = start; p <= MAX && !conflictFound; p += repeatInterval) {
          int q = p + delta;
          
          if (q > MAX) {
            q = MAX;
          }
          
          conflictFound = scanInterval(schedule, p, q);
        }
      }

      if (conflictFound) {
        resultBuilder.append(String.format("%s%n", CONFLICT_STR));
      } else {
        resultBuilder.append(String.format("%s%n", NO_CONFLICT_STR));
      }
    }
    scanner.close();
    outStream.print(resultBuilder.toString());
  }

  /*
   * returns true if a conflict has been found in the interval
   */
  private static boolean scanInterval(BitSet schedule, int start, int end) {
    
    for (int j = start; j < end; j++) {
      if (schedule.get(j)) {
        return true;
      }

      schedule.set(j);
    }

    return false;
  }
}
