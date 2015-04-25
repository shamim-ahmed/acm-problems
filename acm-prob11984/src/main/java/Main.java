import java.io.InputStream;
import java.io.PrintStream;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.Scanner;

public class Main {
  private static final int SCALE = 6;

  public static void main(String... args) {
    processInput(System.in, System.out);
  }

  public static void processInput(InputStream inputStream, PrintStream outputStream) {
    DecimalFormat decimalFormat = new DecimalFormat();
    decimalFormat.setMaximumFractionDigits(2);
    decimalFormat.setMinimumFractionDigits(2);

    StringBuilder resultBuilder = new StringBuilder();
    Scanner scanner = new Scanner(inputStream);
    int numberOfCases = scanner.nextInt();

    for (int i = 1; i <= numberOfCases; i++) {
      BigDecimal initialValueInCelsius = BigDecimal.valueOf(scanner.nextInt());
      BigDecimal increaseInFahrenheit = BigDecimal.valueOf(scanner.nextInt());

      BigDecimal initialValueInFahrenheit = convertCelsiusToFahrenheit(initialValueInCelsius);
      BigDecimal resultInFahrenheit = initialValueInFahrenheit.add(increaseInFahrenheit);
      BigDecimal resultInCelsius = convertFahrenheitToCelsius(resultInFahrenheit);

      resultBuilder.append(String.format("Case %d: %s%n", i, decimalFormat.format(resultInCelsius)));
    }

    scanner.close();
    outputStream.print(resultBuilder.toString());
  }

  private static BigDecimal convertCelsiusToFahrenheit(BigDecimal celsiusValue) {
    return celsiusValue.multiply(BigDecimal.valueOf(9)).divide(BigDecimal.valueOf(5), SCALE, RoundingMode.HALF_UP)
        .add(BigDecimal.valueOf(32));
  }

  private static BigDecimal convertFahrenheitToCelsius(BigDecimal fahrenheitValue) {
    return fahrenheitValue.subtract(BigDecimal.valueOf(32)).multiply(BigDecimal.valueOf(5))
        .divide(BigDecimal.valueOf(9), SCALE, RoundingMode.HALF_UP);
  }
}
