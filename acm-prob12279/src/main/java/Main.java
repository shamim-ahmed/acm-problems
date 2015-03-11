import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class Main {

  public static void main(String... args) {
    processInput(System.in, System.out);
  }

  public static void processInput(InputStream inputStream, PrintStream outputStream) {
    StringBuilder resultBuilder = new StringBuilder();
    Scanner scanner = new Scanner(inputStream);
    int i = 0;
    
    while (scanner.hasNextInt()) {
      int n = scanner.nextInt();
      
      if (n == 0) {
        break;
      }
      
      i++;
      int expectedTreatCount = 0;
      int actualTreatCount = 0;
      
      for (int j = 0; j < n; j++) {
        int x = scanner.nextInt();
        
        if (x > 0) {
          expectedTreatCount++;
        } else {
          actualTreatCount++;
        }
      }
      
      resultBuilder.append(String.format("Case %d: %d%n", i, (expectedTreatCount - actualTreatCount)));
    }
    
    scanner.close();
    outputStream.print(resultBuilder.toString());
  }
}
