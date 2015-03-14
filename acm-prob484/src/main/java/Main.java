import java.io.InputStream;
import java.io.PrintStream;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
  public static void main(String... args) {
    processInput(System.in, System.out);
  }
  
  public static void processInput(InputStream inputStream, PrintStream outputStream) {
    Scanner scanner = new Scanner(inputStream);
    
    Map<Integer, Integer> frequencyMap = new LinkedHashMap<>();
    
    while (scanner.hasNextInt()) {
      Integer value = scanner.nextInt();
      Integer freq = frequencyMap.get(value);
      
      if (freq == null) {
        freq = Integer.valueOf(1);
      } else {
        freq++;
      }
      
      frequencyMap.put(value, freq);
    }
    
    scanner.close();
    StringBuilder resultBuilder = new StringBuilder();

    for (Map.Entry<Integer, Integer> entry : frequencyMap.entrySet()) {
      resultBuilder.append(String.format("%s %s%n", entry.getKey(), entry.getValue()));
    }
    
    outputStream.print(resultBuilder.toString());
  }
}
