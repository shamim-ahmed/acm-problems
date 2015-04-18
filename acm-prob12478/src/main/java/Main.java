import java.io.InputStream;
import java.io.PrintStream;

public class Main {

  public static void main(String... args) {
    processInput(System.in, System.out);
  }

  public static void processInput(InputStream inputStream, PrintStream outputStream) {
    StringBuilder resultBuilder = new StringBuilder();
    resultBuilder.append("KABIR").append("\n");
    outputStream.print(resultBuilder.toString());
  }
}
