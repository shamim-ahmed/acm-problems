import java.math.BigDecimal;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Main {
  public static void main(String... args) {
	Scanner scanner = new Scanner(System.in);

	while (scanner.hasNextLine()) {
	  String line = scanner.nextLine();
	  StringTokenizer tokenizer = new StringTokenizer(line);
	  int heightOfWell = Integer.parseInt(tokenizer.nextToken());

	  if (heightOfWell == 0) {
		break;
	  }

	  int distanceClimbedDuringDay = Integer.parseInt(tokenizer.nextToken());
	  int distanceSlidedDuringNight = Integer.parseInt(tokenizer.nextToken());
	  int fatigueFactorAsPercentage = Integer.parseInt(tokenizer.nextToken());
	  solve(heightOfWell, distanceClimbedDuringDay, distanceSlidedDuringNight, fatigueFactorAsPercentage);
	}

	scanner.close();
  }

  private static void solve(int height, int climbedDist, int slidedDist, int ff) {
	final BigDecimal heightOfWell = BigDecimal.valueOf(height);
	BigDecimal distanceClimbedDuringDay = BigDecimal.valueOf(climbedDist);
	final BigDecimal distanceSlidedDuringNight = BigDecimal.valueOf(slidedDist);
	final BigDecimal fatigueFactor = BigDecimal.valueOf(ff).divide(BigDecimal.valueOf(100.0));

	BigDecimal heightOfSnail = BigDecimal.ZERO;
	final BigDecimal delta = distanceClimbedDuringDay.multiply(fatigueFactor);
	int dayCount = 1;
	boolean done = false;

	while (!done) {
	  if (dayCount > 1) {
		distanceClimbedDuringDay = distanceClimbedDuringDay.subtract(delta);
	  }

	  if (distanceClimbedDuringDay.compareTo(BigDecimal.ZERO) > 0) {
		  heightOfSnail = heightOfSnail.add(distanceClimbedDuringDay);
	  }

	  if (heightOfSnail.compareTo(heightOfWell) > 0) {
		done = true;
	  } else {
		heightOfSnail = heightOfSnail.subtract(distanceSlidedDuringNight);

		if (heightOfSnail.compareTo(BigDecimal.ZERO) < 0) {
		  done = true;
		} else {
		  dayCount++;
		}
	  }
	}

	if (heightOfSnail.compareTo(heightOfWell) >= 0) {
	  System.out.printf("success on day %d%n", dayCount);
	} else {
	  System.out.printf("failure on day %d%n", dayCount);
	}
  }
}
