import java.io.InputStream;
import java.io.PrintStream;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Scanner;

public class Main {
  private static final int INTERVAL_LENGTH = 5;
  private static final BigDecimal DIVISOR = BigDecimal.valueOf(INTERVAL_LENGTH);
  private static final int SCALE = 4;

  public static void main(String... args) {
    processInput(System.in, System.out);
  }

  public static void processInput(InputStream inputStream, PrintStream outputStream) {
    StringBuilder resultBuilder = new StringBuilder();
    Scanner scanner = new Scanner(inputStream);
    int caseCounter = 0;

    while (scanner.hasNextInt()) {
      final int fileSize = scanner.nextInt();

      if (fileSize == 0) {
        break;
      }

      caseCounter++;
      resultBuilder.append(String.format("Output for data set %d, %d bytes:%n", caseCounter, fileSize));

      int bytesTransferred = 0;
      int intervalByteCount = 0;
      int index = 0;
      int totalTime = 0;

      while (bytesTransferred != fileSize) {
        int m = scanner.nextInt();
        bytesTransferred += m;
        intervalByteCount += m;
        totalTime++;
        index++;

        if (index == INTERVAL_LENGTH) {
          computeTransferRate(fileSize, bytesTransferred, intervalByteCount, resultBuilder);
          index = 0;
          intervalByteCount = 0;
        }
      }

      resultBuilder.append(String.format("Total time: %d seconds%n", totalTime));
      resultBuilder.append("\n");
    }

    scanner.close();
    outputStream.print(resultBuilder.toString());
  }

  private static int computeTransferRate(int fileSize, int bytesTransferred, int intervalByteCount,
      StringBuilder resultBuilder) {
    if (intervalByteCount == 0) {
      resultBuilder.append("   Time remaining: stalled").append("\n");
    } else {
      int bytesRemaining = fileSize - bytesTransferred;
      BigDecimal transferRate = BigDecimal.valueOf(intervalByteCount).divide(DIVISOR, SCALE, RoundingMode.HALF_UP);
      BigDecimal exactRemainingTime = BigDecimal.valueOf(bytesRemaining).divide(transferRate, SCALE,
          RoundingMode.HALF_UP);
      int roundedRemainingTime = (int) Math.ceil(exactRemainingTime.doubleValue());
      resultBuilder.append(String.format("   Time remaining: %d seconds%n", roundedRemainingTime));
    }

    return intervalByteCount;
  }
}
