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
    
    int p = heightValues[0];
    result += (blockHeight - heightValues[0]);
    
    for (int i = 1; i < heightValues.length; i++) {
      if (heightValues[i] < p) {
        result += (p - heightValues[i]);
      } 
      
      p = heightValues[i];
    }
    
    return result;
  }
}
