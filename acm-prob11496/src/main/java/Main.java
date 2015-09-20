import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class Main {
  public static final int END_OF_INPUT_MARKER = 0;
  
  public static void main(String... args) {
    processInput(System.in, System.out);
  }

  public static void processInput(InputStream inputStream, PrintStream outputStream) {
    Scanner scanner = new Scanner(inputStream);
    StringBuilder resultBuilder = new StringBuilder();
    
    while (scanner.hasNextInt()) {
      int n = scanner.nextInt();
      
      if (n == END_OF_INPUT_MARKER) {
        break;
      }
      
      int[] values = new int[n];
      
      for (int i = 0; i < values.length; i++) {
        values[i] = scanner.nextInt();
      }
      
      int count = 0;
      
      for (int i = 0; i < n; i++) {
        int prev = (i - 1 + n) % n;
        int next = (i + 1 + n) % n;
        
        int x = values[i] - values[prev];
        int y = values[i] - values[next];
        
        if ((x > 0 && y > 0) || (x < 0 && y < 0)) {
          count++;
        }
      }
      
      resultBuilder.append(count).append("\n");
    }
    
    scanner.close();
    outputStream.print(resultBuilder.toString());
  }
}
