import java.io.InputStream;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeMap;

public class Main {
  private static final String CASE_FORMAT_STR = "Case %d: ";
  private static final String NUMBER_FORMAT_STR = "%.6f";
  private static final String PERCENT_SIGN = "%";

  public static void main(String... args) {
    processInput(System.in, System.out);
  }

  public static void processInput(InputStream inStream, PrintStream outStream) {
    Scanner scanner = new Scanner(inStream);
    int numberOfCases = scanner.nextInt();
    StringBuilder resultBuilder = new StringBuilder();

    for (int i = 1; i <= numberOfCases; i++) {
      int numberOfFriends = scanner.nextInt();
      Map<Integer, Set<Integer>> stampToPersonMap = new HashMap<>();

      // find the owner(s) for each kind of stamp
      for (int j = 1; j <= numberOfFriends; j++) {
        int numberOfStamps = scanner.nextInt();

        for (int k = 1; k <= numberOfStamps; k++) {
          int stampType = scanner.nextInt();
          putInMap(stampToPersonMap, stampType, j);
        }
      }

      // now consider only those stamps for which there is only one owner
      Map<Integer, Set<Integer>> personToStampMap = new TreeMap<>();
      int count = 0;

      for (Map.Entry<Integer, Set<Integer>> entry : stampToPersonMap.entrySet()) {
        if (entry.getValue().size() == 1) {
          Integer[] stampArray = new Integer[1];
          stampArray = entry.getValue().toArray(stampArray);
          putInMap(personToStampMap, stampArray[0], entry.getKey());
          count++;
        }
      }

      // accumulate the formatted result
      generateFormattedResult(i, numberOfFriends, count, personToStampMap, resultBuilder);
    }

    scanner.close();
    outStream.print(resultBuilder.toString());
  }

  private static void putInMap(Map<Integer, Set<Integer>> map, Integer key, Integer value) {
    Set<Integer> valueSet = map.get(key);

    if (valueSet == null) {
      valueSet = new HashSet<>();
      map.put(key, valueSet);
    }

    valueSet.add(value);
  }

  // This method computes the percentage results.
  // We know the unique stamps owned by each person from the input map.
  // We also know the total number of unique stamps (given by totalCount parameter)
  private static void generateFormattedResult(int inputSetNo, int numberOfFriends, int totalCount,
      Map<Integer, Set<Integer>> personToStampMap, StringBuilder resultBuilder) {

    resultBuilder.append(String.format(CASE_FORMAT_STR, inputSetNo));

    for (int i = 1; i <= numberOfFriends; i++) {
      Set<Integer> stampSet = personToStampMap.get(i);
      double val = 0.0;

      if (stampSet != null) {
        val = (100.0 * stampSet.size()) / totalCount;
      }

      resultBuilder.append(String.format(NUMBER_FORMAT_STR, val)).append(PERCENT_SIGN);

      if (i < numberOfFriends) {
        resultBuilder.append(" ");
      }
    }

    resultBuilder.append("\n");
  }
}
