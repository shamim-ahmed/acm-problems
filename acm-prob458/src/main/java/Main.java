import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;

public class Main {
  public static void main(String... args) {
    processInput(System.in, System.out);
  }

  public static void processInput(InputStream inputStream, PrintStream outputStream) {
    StringBuilder resultBuilder = new StringBuilder();
    int c;

    try {
      while ((c = inputStream.read()) != -1) {
        if (c == '\n') {
          resultBuilder.append((char) c);
        } else {
          resultBuilder.append((char) (c - 7));
        }
      }
    } catch (IOException ex) {
      ex.printStackTrace(System.err);
    }

    outputStream.print(resultBuilder.toString());
  }
}
