import java.io.InputStream;
import java.io.PrintStream;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
  private static final String ROCK_STR = "rock";
  private static final String SCISSOR_STR = "scissors";
  private static final String PAPER_STR = "paper";

  private static final String END_OF_INPUT_INDICATOR = "0";
  private static final Pattern GAME_LINE_PATTERN = Pattern.compile("([\\d]+)\\s+([\\w]+)\\s+([\\d]+)\\s+([\\w]+)");

  private enum Result {
    WON, LOST
  }

  public static void main(String... args) {
    processInput(System.in, System.out);
  }

  public static void processInput(InputStream inputStream, PrintStream outputStream) {
    DecimalFormat decimalFormat = new DecimalFormat();
    decimalFormat.setMaximumFractionDigits(3);
    decimalFormat.setMinimumFractionDigits(3);
    decimalFormat.setMinimumIntegerDigits(1);

    StringBuilder resultBuilder = new StringBuilder();
    Scanner scanner = new Scanner(inputStream);

    while (scanner.hasNextLine()) {
      String line = scanner.nextLine().trim();

      if (line.equals("")) {
        continue;
      }

      if (line.equals(END_OF_INPUT_INDICATOR)) {
        break;
      }

      String[] values = line.split(" ");
      final int n = Integer.parseInt(values[0]);
      final int k = Integer.parseInt(values[1]);
      int count = n * (n - 1) * k / 2;
      Map<Integer, List<Result>> resultMap = new HashMap<>();

      for (int i = 1; i <= n; i++) {
        resultMap.put(i, new ArrayList<Result>());
      }

      for (int i = 0; i < count; i++) {
        String gameLIne = scanner.nextLine();
        Matcher gameLineMatcher = GAME_LINE_PATTERN.matcher(gameLIne);

        if (!gameLineMatcher.matches()) {
          throw new RuntimeException(String.format("invalid input line %s", gameLIne));
        }

        int playerA = Integer.parseInt(gameLineMatcher.group(1));
        String choiceA = gameLineMatcher.group(2).toLowerCase();
        int playerB = Integer.parseInt(gameLineMatcher.group(3));
        String choiceB = gameLineMatcher.group(4).toLowerCase();

        if (playerA > n || playerB > n) {
          throw new RuntimeException(String.format("invalid player number in line %s", gameLineMatcher));
        }

        Result resultA = null;
        Result resultB = null;

        if (!choiceA.equals(choiceB)) {
          if (choiceA.equals(PAPER_STR) && choiceB.equals(ROCK_STR)) {
            resultA = Result.WON;
            resultB = Result.LOST;
          } else if (choiceA.equals(ROCK_STR) && choiceB.equals(PAPER_STR)) {
            resultA = Result.LOST;
            resultB = Result.WON;
          } else if (choiceA.equals(SCISSOR_STR) && choiceB.equals(PAPER_STR)) {
            resultA = Result.WON;
            resultB = Result.LOST;
          } else if (choiceA.equals(PAPER_STR) && choiceB.equals(SCISSOR_STR)) {
            resultA = Result.LOST;
            resultB = Result.WON;
          } else if (choiceA.equals(ROCK_STR) && choiceB.equals(SCISSOR_STR)) {
            resultA = Result.WON;
            resultB = Result.LOST;
          } else if (choiceA.equals(SCISSOR_STR) && choiceB.equals(ROCK_STR)) {
            resultA = Result.LOST;
            resultB = Result.WON;
          }
        }

        if (resultA != null && resultB != null) {
          resultMap.get(playerA).add(resultA);
          resultMap.get(playerB).add(resultB);
        }
      }

      for (int i = 1; i <= n; i++) {
        List<Result> resultList = resultMap.get(i);
        int winCount = 0;

        for (Result result : resultList) {
          if (result == Result.WON) {
            winCount++;
          }
        }

        int denominator = resultList.size();

        if (denominator == 0) {
          resultBuilder.append("-").append("\n");
        } else {
          BigDecimal result = BigDecimal.valueOf(winCount).divide(BigDecimal.valueOf(denominator), 6,
              RoundingMode.HALF_UP);
          resultBuilder.append(decimalFormat.format(result)).append("\n");
        }
      }

      resultBuilder.append("\n");
    }

    scanner.close();

    int len = resultBuilder.length();
    resultBuilder.delete(len - 1, len);
    outputStream.print(resultBuilder.toString());
  }
}
