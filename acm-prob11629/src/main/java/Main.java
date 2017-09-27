import java.io.InputStream;
import java.io.PrintStream;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
  private static final String PLUS_OP = "+";
  private static final String LESS_THAN_OP = "<";
  private static final String LESS_THAN_OR_EQUAL_OP = "<=";
  private static final String EQUAL_OP = "=";
  private static final String GREATER_THAN_OP = ">";
  private static final String GREATER_THAN_OR_EQUAL_OP = ">=";
  private static final String REGEX = "\\s+";

  private static final String INCORRECT_GUESS_OUTPUT_FORMAT = "Guess #%d was incorrect.";
  private static final String CORRECT_GUESS_OUTPUT_FORMAT = "Guess #%d was correct.";

  public static void main(String... args) {
    processInput(System.in, System.out);
  }

  public static void processInput(InputStream inStream, PrintStream outStream) {
    Scanner scanner = new Scanner(inStream);
    String[] values = splitLine(scanner.nextLine(), REGEX);
    int numberOfParties = Integer.parseInt(values[0]);
    int numberOfGuesses = Integer.parseInt(values[1]);
    Map<String, BigDecimal> votePercentageMap = new HashMap<>();

    for (int i = 0; i < numberOfParties; i++) {
      String[] strArray = splitLine(scanner.nextLine(), REGEX);
      String partyName = strArray[0];
      BigDecimal percentage = BigDecimal.valueOf(Double.parseDouble(strArray[1]));
      votePercentageMap.put(partyName, percentage);
    }

    StringBuilder resultBuilder = new StringBuilder();

    for (int i = 1; i <= numberOfGuesses; i++) {
      String guess = scanner.nextLine();
      boolean result = evaluateGuess(guess, votePercentageMap);

      if (result) {
        resultBuilder.append(String.format(CORRECT_GUESS_OUTPUT_FORMAT, i)).append("\n");
      } else {
        resultBuilder.append(String.format(INCORRECT_GUESS_OUTPUT_FORMAT, i)).append("\n");
      }
    }

    scanner.close();
    outStream.print(resultBuilder.toString());
  }

  private static boolean evaluateGuess(String guess, Map<String, BigDecimal> votePercentageMap) {
    String[] tokenArray = guess.split(REGEX);
    BigDecimal leftHandValue = BigDecimal.ZERO;
    BigDecimal rightHandValue = BigDecimal.ZERO;
    String comparisonOperator = null;

    for (String token : tokenArray) {
      if (PLUS_OP.equals(token)) {
        continue;
      }

      // there will be only one comparison operator
      if (isComparisonOperator(token)) {
        comparisonOperator = token;
        continue;
      }

      // If we have not yet found the comparison operator, we are still on the left side
      if (comparisonOperator == null) {
        BigDecimal val = votePercentageMap.get(token);

        if (val != null) {
          leftHandValue = leftHandValue.add(val);
        }
      } else {
        // We have found the value on the right hand side
        rightHandValue = BigDecimal.valueOf(Double.parseDouble(token));
      }
    }

    // leftHandValue, rightHandValue and comparisonOperator should all be set at this point
    boolean result = false;
    int comparisonOutput = leftHandValue.compareTo(rightHandValue);

    switch (comparisonOperator) {
      case LESS_THAN_OP:
        result = comparisonOutput < 0;
        break;
      case LESS_THAN_OR_EQUAL_OP:
        result = comparisonOutput <= 0;
        break;
      case EQUAL_OP:
        result = comparisonOutput == 0;
        break;
      case GREATER_THAN_OP:
        result = comparisonOutput > 0;
        break;
      case GREATER_THAN_OR_EQUAL_OP:
        result = comparisonOutput >= 0;
        break;
      default:
        break;
    }

    return result;
  }

  private static String[] splitLine(String line, String regex) {
    if (line == null) {
      return null;
    }

    return line.split(regex);
  }

  private static boolean isComparisonOperator(String token) {
    return LESS_THAN_OP.equals(token) || LESS_THAN_OR_EQUAL_OP.equals(token)
        || EQUAL_OP.equals(token) || GREATER_THAN_OP.equals(token)
        || GREATER_THAN_OR_EQUAL_OP.equals(token);
  }
}
