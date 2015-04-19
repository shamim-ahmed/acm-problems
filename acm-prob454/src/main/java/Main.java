import java.io.InputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Main {

  public static void main(String... args) {
    processInput(System.in, System.out);
  }

  public static void processInput(InputStream inputStream, PrintStream outputStream) {
    StringBuilder resultBuilder = new StringBuilder();
    Scanner scanner = new Scanner(inputStream);
    int n = Integer.parseInt(scanner.nextLine());
    scanner.nextLine();

    for (int i = 0; i < n; i++) {
      String inputLine;
      List<String> inputLineList = new ArrayList<>();
      Map<String, char[]> statsMap = new HashMap<>();

      while (scanner.hasNextLine()) {
        inputLine = scanner.nextLine().trim();

        // check if we have reached the end of current test case
        if (inputLine.equals("")) {
          break;
        }

        inputLineList.add(inputLine);
        char[] charFreqMap = computeCharacterArray(inputLine);
        statsMap.put(inputLine, charFreqMap);
      }
      
      Collections.sort(inputLineList);

      for (int j = 0, m = inputLineList.size(); j < m - 1; j++) {
        String line1 = inputLineList.get(j);
        char[] inputChars1 = statsMap.get(line1);

        for (int k = j + 1; k < m; k++) {
          String line2 = inputLineList.get(k);
          char[] inputChars2 = statsMap.get(line2);

          if (Arrays.equals(inputChars1, inputChars2)) {
            resultBuilder.append(String.format("%s = %s%n", line1, line2));
          }
        }
      }

      if (i == 0 && resultBuilder.toString().equals("")) {
        // I do not know where this requirement comes from.
        // I added this logic so that the output matches udebug output
        resultBuilder.append("");
      } else if (i != n - 1) {
        resultBuilder.append("\n");
      }
    }

    scanner.close();
    String output = resultBuilder.toString();
    
    if (output.equals("\n")) {
      output = "";
    }
    
    outputStream.print(output);
  }

  private static char[] computeCharacterArray(String inputLine) {
    inputLine = inputLine.replaceAll("\\s+", "");
    char[] inputChars = inputLine.toCharArray();
    Arrays.sort(inputChars);
    
    return inputChars;
  }
}
