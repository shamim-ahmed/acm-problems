import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class Main {
  private static final int INVALID_POSITION = -1;

  public static void main(String... args) {
    processInput(System.in, System.out);
  }

  public static void processInput(InputStream inStream, PrintStream outStream) {
    Scanner scanner = new Scanner(inStream);

    int numberOfCases = scanner.nextInt();
    StringBuilder resultBuilder = new StringBuilder();

    for (int i = 1; i <= numberOfCases; i++) {
      int numberOfStations = scanner.nextInt();
      int[] petrolAvailable = new int[numberOfStations];
      int[] petrolNeeded = new int[numberOfStations];

      for (int j = 0; j < numberOfStations; j++) {
        petrolAvailable[j] = scanner.nextInt();
      }

      for (int j = 0; j < numberOfStations; j++) {
        petrolNeeded[j] = scanner.nextInt();
      }

      // input has been read for current case. Now compute result
      int position = INVALID_POSITION;
      int sum = 0;
      int partialSum = 0;

      for (int j = 0; j < numberOfStations; j++) {
        int delta = petrolAvailable[j] - petrolNeeded[j];
        sum += delta;

        if (position == INVALID_POSITION && delta >= 0) {
          position = j;
          partialSum = delta;
        } else if (position != INVALID_POSITION) {
          partialSum += delta;

          if (partialSum < 0) {
            position = INVALID_POSITION;
            partialSum = 0;
          }
        }
      }

      if (sum >= 0) {
        resultBuilder.append(String.format("Case %d: Possible from station %d%n", i, position + 1));
      } else {
        resultBuilder.append(String.format("Case %d: Not possible%n", i));
      }
    }

    outStream.print(resultBuilder.toString());
    scanner.close();
  }
}
