import java.io.InputStream;
import java.io.PrintStream;
import java.util.BitSet;
import java.util.Scanner;

public class Main {
  private static final int SIZE = 32;

  public static void main(String... args) {
    processInput(System.in, System.out);
  }

  public static void processInput(InputStream inStream, PrintStream outStream) {
    Scanner scanner = new Scanner(inStream);
    StringBuilder resultBuilder = new StringBuilder();

    while (scanner.hasNextInt()) {
      int n = scanner.nextInt();

      if (n == 0) {
        break;
      }

      BitSet input = convertToBitset(n);
      BitSet result1 = new BitSet(SIZE);
      BitSet result2 = new BitSet(SIZE);
      int count = 0;

      for (int i = 0; i < SIZE; i++) {
        if (input.get(i)) {
          count++;

          if (count % 2 == 1) {
            result1.set(i);
          } else {
            result2.set(i);
          }
        }
      }
      
      int value1 = convertToInt(result1);
      int value2 = convertToInt(result2);
      resultBuilder.append(String.format("%d %d%n", value1, value2));
    }

    scanner.close();
    outStream.print(resultBuilder.toString());
  }

  private static BitSet convertToBitset(final int n) {
    BitSet result = new BitSet(SIZE);
    int x = n;
    int i = 0;

    while (x > 0 && i < SIZE) {
      if (x % 2 == 1) {
        result.set(i);
      }

      x >>= 1;
      i++;
    }

    return result;
  }
  
  private static int convertToInt(BitSet bitSet) {
    int result = 0;
    
    for (int i = 0; i < SIZE; i++) {
      if (bitSet.get(i)) {
        result += Math.pow(2, i);
      }
    }
    
    return result;
  }
}
