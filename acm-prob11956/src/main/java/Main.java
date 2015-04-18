import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class Main {
  private static final int MEMORY_SIZE = 100;
  private static final int UPPER_LIMIT = 256;

  public static void main(String... args) {
    processInput(System.in, System.out);
  }

  public static void processInput(InputStream inputStream, PrintStream outputStream) {
    StringBuilder resultBuilder = new StringBuilder();
    Scanner scanner = new Scanner(inputStream);
    int n = Integer.parseInt(scanner.nextLine().trim());

    for (int i = 1; i <= n; i++) {
      String line = scanner.nextLine();
      char[] instructions = line.toCharArray();
      
      int[] memory = new int[MEMORY_SIZE];
      int index = 0;

      for (int j = 0; j < instructions.length; j++) {
        switch (instructions[j]) {
          case '>':
            index = (index + 1) % MEMORY_SIZE;
            break;
          case '<':
            index = (index - 1 + MEMORY_SIZE) % MEMORY_SIZE;
            break;
          case '+':
            memory[index] = (memory[index] + 1) % UPPER_LIMIT;
            break;
          case '-':
            memory[index] = (memory[index] - 1 + UPPER_LIMIT) % UPPER_LIMIT;
            break;
          case '.':
            break;
        }
      }

      resultBuilder.append(String.format("Case %d: ", i));

      for (int j = 0; j < memory.length; j++) {
        String s = "";

        if (j == memory.length - 1) {
          s = String.format("%02X", memory[j]);
        } else {
          s = String.format("%02X ", memory[j]);
        }

        resultBuilder.append(s);
      }
      
      resultBuilder.append("\n");
    }

    scanner.close();
    outputStream.print(resultBuilder.toString());
  }
}
