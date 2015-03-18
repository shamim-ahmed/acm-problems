import java.io.InputStream;
import java.io.PrintStream;
import java.math.BigInteger;
import java.util.Scanner;

public class Main {
  public static void main(String... args) {
    processInput(System.in, System.out);
  }
  
  public static void processInput(InputStream inputStream, PrintStream outputStream) {
    StringBuilder resultBuilder = new StringBuilder();
    Scanner scanner = new Scanner(inputStream);
    
    while (scanner.hasNextBigInteger()) {
      BigInteger x = scanner.nextBigInteger();
      BigInteger y = scanner.nextBigInteger();
      BigInteger diff = x.subtract(y).abs();
      resultBuilder.append(diff).append("\n");
    }
    
    scanner.close();
    outputStream.print(resultBuilder.toString());
  }
}
