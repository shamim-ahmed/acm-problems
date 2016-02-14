import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class Main {
  public static void main(String... args) {
    processInput(System.in, System.out);
  }

  public static void processInput(InputStream inStream, PrintStream outStream) {
    Scanner scanner = new Scanner(inStream);
    StringBuilder resultBuilder = new StringBuilder();
    int numberOfCases = scanner.nextInt();
    
    for (int i = 0; i < numberOfCases; i++) {
      int numberOfDays = scanner.nextInt();
      int numberOfParties = scanner.nextInt();

      boolean[] flags = new boolean[numberOfDays];
      
      // exclude weekends
      for (int j = 5; j < numberOfDays; j += 7) {
        flags[j] = true;
        
        if (j + 1 < numberOfDays) {
          flags[j + 1] = true;
        }
      }
      
      int[] hartalParams = new int[numberOfParties];

      for (int j = 0; j < numberOfParties; j++) {
        hartalParams[j] = scanner.nextInt();
      }
      
      int numberOfDaysLost = 0;
      for (int j = 0; j < numberOfParties; j++) {
        int delta = hartalParams[j];
        
        // note the starting value carefully. We are using zero based indexes
        for (int k = delta - 1; k >= 0 && k < numberOfDays; k += delta) {
          if (!flags[k]) {
            flags[k] = true;
            numberOfDaysLost++;
          }
        }
      }
      
      resultBuilder.append(numberOfDaysLost).append("\n");
    }
    
    outStream.print(resultBuilder.toString());
    scanner.close();
  }
}
