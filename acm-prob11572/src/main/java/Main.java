import java.io.InputStream;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class Main {
  public static void main(String... args) {
    processInput(System.in, System.out);
  }

  public static void processInput(InputStream inStream, PrintStream outStream) {
    Scanner scanner = new Scanner(inStream);
    StringBuilder resultBuilder = new StringBuilder();
    int numberOfCases = scanner.nextInt();

    for (int i = 0; i < numberOfCases; i++) {
      int n = scanner.nextInt();
      int[] valueArray = new int[n];

      for (int j = 0; j < n; j++) {
        valueArray[j] = scanner.nextInt();
      }

      Map<Integer, Integer> indexMap = new HashMap<>();
      Set<Integer> uniqueValues = new HashSet<>();
      int start = 0;
      int maxLength = 0;

      for (int j = 0; j < n; j++) {
        if (!uniqueValues.contains(valueArray[j])) {
          uniqueValues.add(valueArray[j]);
          indexMap.put(valueArray[j], j);
          continue;
        }

        // the current value has appeared before
        // this signals the end of the current sequence
        int length = j - start;

        if (maxLength < length) {
          maxLength = length;
        }

        // reset start value
        // we can forget items in valueArray that have index less than start
        start = indexMap.get(valueArray[j]) + 1;
        uniqueValues.clear();
        indexMap.clear();

        for (int k = start; k <= j; k++) {
          uniqueValues.add(valueArray[k]);
          indexMap.put(valueArray[k], k);
        }
      }

      // check if the last sequence was the longest one
      int lastSequenceLength = uniqueValues.size();
      
      if (maxLength < lastSequenceLength) {
        maxLength = lastSequenceLength;
      }

      resultBuilder.append(maxLength).append("\n");
    }

    scanner.close();
    outStream.print(resultBuilder.toString());
  }
}