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
    
    while (scanner.hasNextInt()) {
      int blockHeight = scanner.nextInt();
      int blockLength = scanner.nextInt();
      
      if (blockHeight == 0 && blockLength == 0) {
        break;
      }
      
      int[] heightValues = new int[blockLength];

      for (int i = 0; i < heightValues.length; i++) {
        heightValues[i] = scanner.nextInt();
      }

      int result = computeResult(blockHeight, heightValues);
      resultBuilder.append(result).append("\n");
    }

    scanner.close();
    outputStream.print(resultBuilder.toString());
  }

  private static int computeResult(int blockHeight, int[] heightValues) {
    int result = 0;
    
    for (int i = blockHeight; i > 0; i--) {
      int p = -1;

      for (int j = 0; j < heightValues.length; j++) {
        if (heightValues[j] >= i) {
          p = j;
          break;
        }
      } 
      
      if (p == -1) {
        result += 1;
      } else {
        if (p > 0) {
          result += 1;
        }
        
        for (int j = p + 1; j < heightValues.length; j++) {
          if (heightValues[j] >= i) {
            int q = p;
            p = j;
            
            if (p - q > 1) {
              result += 1;
            }
          }
        }
        
        if (heightValues.length - p > 1) {
          result += 1;
        }
      }
    }
    
    return result;
  }
}
