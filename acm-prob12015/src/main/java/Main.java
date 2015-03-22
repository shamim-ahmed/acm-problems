import java.io.InputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
  private static final int LINES_PER_CASE = 10;
  private static final Pattern INPUT_PATTERN = Pattern.compile("([^\\s]+)\\s+(\\d+)");

  public static void main(String... args) {
    processInput(System.in, System.out);
  }

  public static void processInput(InputStream inputStream, PrintStream outputStream) {
    StringBuilder resultBuilder = new StringBuilder();
    Scanner scanner = new Scanner(inputStream);
    final int numberOfCases = Integer.parseInt(scanner.nextLine());

    for (int i = 1; i <= numberOfCases; i++) {
      TreeMap<Integer, List<String>> rankingMap = new TreeMap<>();

      for (int j = 1; j <= LINES_PER_CASE; j++) {
        String line = scanner.nextLine();
        Matcher matcher = INPUT_PATTERN.matcher(line);

        if (matcher.matches()) {
          String url = matcher.group(1);
          int rank = Integer.parseInt(matcher.group(2));
          List<String> urlList = rankingMap.get(rank);

          if (urlList == null) {
            urlList = new ArrayList<>();
          }

          urlList.add(url);
          rankingMap.put(rank, urlList);
        } else {
          throw new RuntimeException(String.format("Invalid input line: %s", line));
        }
      }

      Map.Entry<Integer, List<String>> lastEntry = rankingMap.lastEntry();
      resultBuilder.append(String.format("Case #%d:%n", i));

      for (String url : lastEntry.getValue()) {
        resultBuilder.append(url).append("\n");
      }
    }

    scanner.close();
    outputStream.print(resultBuilder.toString());
  }
}
