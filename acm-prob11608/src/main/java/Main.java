import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class Main {
  private static final int END_OF_INPUT_MARKER = -1;
  private static final int SIZE = 12;
  private static final String SUCCESS_OUTPUT = "No problem! :D";
  private static final String FAILURE_OUTPUT = "No problem. :(";
  
  public static void main(String... args) {
    processInput(System.in, System.out);
  }

  public static void processInput(InputStream inputStream, PrintStream outputStream) {
    Scanner scanner = new Scanner(inputStream);
    int problemSetCount = 0;
    StringBuilder resultBuilder = new StringBuilder();
    
    while (scanner.hasNextInt()) {
      int n = scanner.nextInt();
      
      if (n == END_OF_INPUT_MARKER) {
        break;
      }
      
      problemSetCount++;
      resultBuilder.append(String.format("Case %d:%n", problemSetCount));
      
      int[] createdValues = new int[SIZE];
      int[] requiredValues = new int[SIZE];
      int[] availableValues = new int[SIZE];
      boolean[] resultFlags = new boolean[SIZE];
            
      for (int i = 0; i < createdValues.length; i++) {
        createdValues[i] = scanner.nextInt();
      }
      
      for (int i = 0; i < requiredValues.length; i++) {
        requiredValues[i] = scanner.nextInt();
      }    

      availableValues[0] = n;
      
      for (int i = 0; i < availableValues.length; i++) {
        int x = availableValues[i] - requiredValues[i];
        resultFlags[i] = x >= 0;
        resultBuilder.append(resultFlags[i] ? SUCCESS_OUTPUT : FAILURE_OUTPUT).append("\n");
        
        if (i < availableValues.length - 1) {
          if (resultFlags[i]) {
            availableValues[i + 1] = x + createdValues[i];
          } else {
            availableValues[i + 1] = availableValues[i] + createdValues[i];
          }
        }
      }
    }
    
    scanner.close();
    outputStream.print(resultBuilder.toString());
  }
}
