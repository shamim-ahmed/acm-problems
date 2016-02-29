import java.io.InputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class Main {
  public static void main(String... args) {
    processInput(System.in, System.out);
  }

  public static void processInput(InputStream inStream, PrintStream outStream) {
    Scanner scanner = new Scanner(inStream);
    StringBuilder resultBuilder = new StringBuilder();
    final Comparator<String> comparator = new CustomStringComparator();

    while (scanner.hasNextLine()) {
      int n = Integer.parseInt(scanner.nextLine().trim());

      if (n == 0) {
        break;
      }

      String line = scanner.nextLine().trim();
      String[] valueArray = line.split("\\s+");

      if (valueArray.length != n) {
        throw new RuntimeException(String.format("invalid input line : %s", line));
      }

      Arrays.sort(valueArray, comparator);

      for (String value : valueArray) {
        resultBuilder.append(value);
      }

      resultBuilder.append("\n");
    }

    scanner.close();
    outStream.print(resultBuilder.toString());
  }

  private static class CustomStringComparator implements Comparator<String> {

    public int compare(String str1, String str2) {
      String temp1 = str1.concat(str2);
      String temp2 = str2.concat(str1);

      return temp2.compareTo(temp1);
    }
  }
}
