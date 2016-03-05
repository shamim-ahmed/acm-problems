import java.io.InputStream;
import java.io.PrintStream;
import java.util.Comparator;
import java.util.Objects;
import java.util.Scanner;

public class Main {
  public static void main(String... args) {
    processInput(System.in, System.out);
  }
  
  public static void processInput(InputStream inStream, PrintStream outStream) {
    Scanner scanner = new Scanner(inStream);
    StringBuilder resultBuilder = new StringBuilder();
    
    while (scanner.hasNextInt()) {
      int n = scanner.nextInt();
      int m = scanner.nextInt();
      Integer[] valueArray = new Integer[n];
      
      for (int i = 0; i < n; i++) {
        valueArray[i] = scanner.nextInt();
      }
      
      
    }
    
    scanner.close();
    outStream.print(resultBuilder.toString());
  }
  
  private static class CustomComparator implements Comparator<Integer> {
    private final int divisor;
    
    public CustomComparator(int divisor) {  
      this.divisor = divisor;
    }
    
    @Override
    public int compare(Integer value1, Integer value2) {
      int reminder1 = computeReminder(value1);
      int reminder2 = computeReminder(value2);
      
      return 0;
    }
    
    private boolean isEven(int n) {
      return n % 2 == 0;
    }
    private int computeReminder(int dividend) {
      if (dividend > 0) {
        return dividend % divisor;
      }
      
      return dividend - ((dividend/divisor) * divisor);
    }
  }
}