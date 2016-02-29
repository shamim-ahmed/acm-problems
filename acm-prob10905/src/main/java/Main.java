import java.io.InputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.TreeMap;

public class Main {
  public static void main(String... args) {
    processInput(System.in, System.out);
  }

  public static void processInput(InputStream inStream, PrintStream outStream) {
    Scanner scanner = new Scanner(inStream);
    StringBuilder resultBuilder = new StringBuilder();

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

      TreeMap<Character, List<String>> bucketMap = new TreeMap<>(Collections.reverseOrder());

      for (String value : valueArray) {
        char c = value.charAt(0);

        List<String> bucket = bucketMap.get(c);

        if (bucket == null) {
          bucket = new ArrayList<>();
          bucketMap.put(c, bucket);
        }

        bucket.add(value);
      }

      Comparator<String> comparator = new CustomStringComparator();

      for (Character c : bucketMap.keySet()) {
        List<String> bucket = bucketMap.get(c);
        Collections.sort(bucket, comparator);

        for (String s : bucket) {
          resultBuilder.append(s);
        }
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
