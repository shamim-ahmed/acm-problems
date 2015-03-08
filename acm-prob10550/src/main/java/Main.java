import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Main {
  public static void main(String... args) {
    processInput(System.in, System.out);
  }
  
  public static void processInput(InputStream inputStream, PrintStream outputStream) {
    Scanner scanner = new Scanner(inputStream);
    StringBuilder resultBuilder = new StringBuilder();
    int[] positions = new int[3];
    
    while (scanner.hasNextLine()) {
      String line = scanner.nextLine();
      StringTokenizer tokenizer = new StringTokenizer(line, " ", false);
      int initialPosition = Integer.parseInt(tokenizer.nextToken());
      boolean done = (initialPosition == 0); 
      
      for (int i = 0; i < 3; i++) {
        positions[i] = Integer.parseInt(tokenizer.nextToken());
        done &= (positions[i] == 0);
      }
      
      if (done) {
        break;
      }
      
      int sum = 2 * 360;
      sum += (9 * ((initialPosition - positions[0] + 40) % 40));
      sum += 360;
      sum += (9 * ((positions[1] - positions[0] + 40) % 40));
      sum += (9 * ((positions[1] - positions[2] + 40) % 40));
      resultBuilder.append(sum).append("\n");
    }
    
    scanner.close();
    outputStream.print(resultBuilder.toString());
  }
}
