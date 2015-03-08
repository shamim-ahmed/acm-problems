import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class Main {
  public static void main(String... args) {
    processInput(System.in, System.out);
  }
  
  public static void processInput(InputStream inputStream, PrintStream outputStream) {
    Scanner scanner = new Scanner(inputStream);
    StringBuilder resultBuilder = new StringBuilder();
    int numberOfCases = scanner.nextInt();
    
    for (int i = 0; i < numberOfCases; i++) {
      int n = scanner.nextInt();
      
      int min = 100;
      int max = -1;
      
      for (int j = 0; j < n; j++) {
        int x = scanner.nextInt();
        
        if (x < min) {
          min = x;
        }
        
        if (x > max) {
          max = x;
        }
      }
      
      resultBuilder.append(2 * (max - min)).append("\n");
    }
    
    scanner.close();
    outputStream.print(resultBuilder.toString());
  }
}
