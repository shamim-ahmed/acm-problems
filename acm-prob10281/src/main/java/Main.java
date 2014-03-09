import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Main {
  private static final int SECONDS_PER_HOUR = 3600;
  private static final int SECONDS_PER_MINUTE = 60;
  private static final BigDecimal SECONDS_PER_HOUR_BIGDECIMAL = new BigDecimal(3600);
  private static final String COLON = ":";

  public static void main(String... args) {
    List<CarSpeedData> dataList = new ArrayList<>();
    Scanner scanner = new Scanner(System.in);
    BigDecimal currentSpeed = BigDecimal.ZERO;

    while (scanner.hasNextLine()) {
      String inputLine = scanner.nextLine();
      StringTokenizer tokenizer = new StringTokenizer(inputLine, " ", false);
      String timeStr = tokenizer.nextToken();

      boolean speedGiven = false;

      if (tokenizer.hasMoreTokens()) {
        currentSpeed = new BigDecimal(tokenizer.nextToken());
        speedGiven = true;
      }

      dataList.add(new CarSpeedData(timeStr, currentSpeed, speedGiven));
    }

    scanner.close();
    process(dataList);
  }

  private static void process(List<CarSpeedData> dataList) {
    BigDecimal totalDistance = BigDecimal.ZERO;
    totalDistance = totalDistance.setScale(2, BigDecimal.ROUND_HALF_UP);
    CarSpeedData currentData = dataList.get(0);

    if (!currentData.isSpeedGiven()) {
      System.out.printf("%s %s km%n", currentData.getFormattedTime(), totalDistance.toString());
    }

    for (int i = 1, n = dataList.size(); i < n; i++) {
      currentData = dataList.get(i);
      CarSpeedData previousData = dataList.get(i - 1);

      int previousTime = previousData.getTimeInSeconds();
      int currentTime = currentData.getTimeInSeconds();
      BigDecimal timeDelta = new BigDecimal(currentTime - previousTime);

      if (!currentData.isSpeedGiven()) {
        BigDecimal d = currentData.getSpeed().multiply(timeDelta)
            .divide(SECONDS_PER_HOUR_BIGDECIMAL, 2, BigDecimal.ROUND_HALF_UP);
        totalDistance = totalDistance.add(d);
        System.out.printf("%s %s km%n", currentData.getFormattedTime(), totalDistance.toString());
      } else {
        BigDecimal d = previousData.getSpeed().multiply(timeDelta)
            .divide(SECONDS_PER_HOUR_BIGDECIMAL, 2, BigDecimal.ROUND_HALF_UP);
        totalDistance = totalDistance.add(d);
      }
    }
  }

  private static class CarSpeedData {
    private final String formattedTime;
    private final int timeInSeconds;
    private final BigDecimal speed; // in km/hr
    private final boolean speedGiven;

    public CarSpeedData(String formattedTime, BigDecimal speed, boolean speedChanged) {
      this.formattedTime = formattedTime;
      String[] timeComponents = formattedTime.split(COLON);

      int t = 0;
      t += Integer.parseInt(timeComponents[0]) * SECONDS_PER_HOUR;
      t += Integer.parseInt(timeComponents[1]) * SECONDS_PER_MINUTE;
      t += Integer.parseInt(timeComponents[2]);

      timeInSeconds = t;
      this.speed = speed;
      this.speedGiven = speedChanged;
    }

    public BigDecimal getSpeed() {
      return speed;
    }

    public boolean isSpeedGiven() {
      return speedGiven;
    }

    public String getFormattedTime() {
      return formattedTime;
    }

    public int getTimeInSeconds() {
      return timeInSeconds;
    }
  }
}
