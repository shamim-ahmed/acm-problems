import java.io.InputStream;
import java.io.PrintStream;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Scanner;
import java.util.TreeMap;

public class Main {
  public static void main(String... args) {
    processInput(System.in, System.out);
  }

  public static void processInput(InputStream inputStream, PrintStream outputStream) {
    StringBuilder resultBuilder = new StringBuilder();
    Scanner scanner = new Scanner(inputStream);

    while (scanner.hasNextInt()) {
      int loanDuration = scanner.nextInt();

      if (loanDuration < 0) {
        break;
      }

      final BigDecimal downPayment = scanner.nextBigDecimal();
      final BigDecimal loanAmount = scanner.nextBigDecimal();
      final int numberOfRecords = scanner.nextInt();
                  
      TreeMap<Integer, BigDecimal> depreciationMap = new TreeMap<>();

      for (int i = 0; i < numberOfRecords; i++) {
        int month = scanner.nextInt();
        BigDecimal depreciactionRate = scanner.nextBigDecimal();
        depreciationMap.put(month, depreciactionRate);
      }
      
      final BigDecimal monthlyPayment = loanAmount.divide(new BigDecimal(loanDuration), 6, RoundingMode.HALF_UP);
      BigDecimal amountToPay = loanAmount;
      BigDecimal carPrice = loanAmount.add(downPayment);
      carPrice = carPrice.subtract(carPrice.multiply(depreciationMap.get(0)));
      int monthCount = 0;

      while (amountToPay.compareTo(carPrice) > 0) {
        monthCount++;
        amountToPay = amountToPay.subtract(monthlyPayment);
        BigDecimal depreciationRate = depreciationMap.floorEntry(monthCount).getValue();
        carPrice = carPrice.subtract(carPrice.multiply(depreciationRate));        
      }

      if (monthCount == 1) {
        resultBuilder.append(String.format("%d month%n", monthCount));
      } else {
        resultBuilder.append(String.format("%d months%n", monthCount));
      }
    }

    scanner.close();
    outputStream.print(resultBuilder.toString());
  }
}
