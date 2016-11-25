import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class Main {

  public static void main(String... args) {
    processInput(System.in, System.out);
  }

  public static void processInput(InputStream inStream, PrintStream outStream) {
    StringBuilder resultBuilder = new StringBuilder();
    
    Scanner scanner = new Scanner(inStream);
    scanner.close();
  }
}
