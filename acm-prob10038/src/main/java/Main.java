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
    
    while (scanner.hasNextInt()) {
      final int n = scanner.nextInt();
      int[] values = new int[n];
      
      for (int i = 0; i < n; i++) {
        values[i] = scanner.nextInt();
      }
      
      boolean[] flags = new boolean[n];
      flags[0] = true;
      boolean result = true;
      
      for (int i = 1; i < n; i++) {
        int k = Math.abs(values[i] - values[i - 1]);
        
        if (k < 1 || k > (n - 1) || flags[k]) {
          result = false;
          break;
        }
       
        flags[k] = true;
      }
      
      if (result) {
        resultBuilder.append("Jolly\n");
      } else {
        resultBuilder.append("Not jolly\n");
      }
    }
    
    scanner.close();
    outputStream.print(resultBuilder.toString());
  }
}
