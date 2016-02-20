import java.io.InputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
  public static void main(String... args) {
    processInput(System.in, System.out);
  }

  public static void processInput(InputStream inStream, PrintStream outStream) {
    Scanner scanner = new Scanner(inStream);
    StringBuilder resultBuilder = new StringBuilder();

    final int numberOfCases = scanner.nextInt();

    for (int i = 0; i < numberOfCases; i++) {
      // read number of streets
      scanner.nextInt();
      // read number of avenues
      scanner.nextInt();

      // read number of friends
      final int numberOfFriends = scanner.nextInt();

      int[] streetValues = new int[numberOfFriends];
      int[] avenueValues = new int[numberOfFriends];

      for (int j = 0; j < numberOfFriends; j++) {
        streetValues[j] = scanner.nextInt();
        avenueValues[j] = scanner.nextInt();
      }

      Arrays.sort(streetValues);
      Arrays.sort(avenueValues);

      int streetIndex;
      int avenueIndex;

      if (streetValues.length % 2 == 0) {
        streetIndex = streetValues.length / 2 - 1;
      } else {
        streetIndex = streetValues.length / 2;
      }

      if (avenueValues.length % 2 == 0) {
        avenueIndex = avenueValues.length / 2 - 1;
      } else {
        avenueIndex = avenueValues.length / 2;
      }

      int street = streetValues[streetIndex];
      int avenue = avenueValues[avenueIndex];

      resultBuilder.append(String.format("(Street: %d, Avenue: %d)%n", street, avenue));
    }

    scanner.close();
    outStream.print(resultBuilder.toString());
  }
}
