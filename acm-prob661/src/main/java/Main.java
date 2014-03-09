import java.util.Scanner;
import java.util.StringTokenizer;

public class Main {
  private static final String SPACE_STR = " ";

  public static void main(String... args) {
	Scanner scanner = new Scanner(System.in);
	int sequenceCount = 0;

	while (scanner.hasNextLine()) {
	  String line = scanner.nextLine();

	  if (line.trim().equals("")) {
		continue;
	  }

	  sequenceCount++;
	  StringTokenizer tokenizer = new StringTokenizer(line, SPACE_STR, false);
	  int numberOfDevices = Integer.parseInt(tokenizer.nextToken());
	  int numberOfOperations = Integer.parseInt(tokenizer.nextToken());
	  int fuseCapacity = Integer.parseInt(tokenizer.nextToken());

	  if (numberOfDevices == 0 && numberOfOperations == 0 && fuseCapacity == 0) {
		break;
	  }

	  solveSequence(sequenceCount, numberOfDevices, numberOfOperations, fuseCapacity, scanner);
	}

	scanner.close();
  }

  private static void solveSequence(int sequenceCount, int numberOfDevices, int numberOfOperations, int fuseCapacity,
	  Scanner scanner) {
	int[] powerRequirements = new int[numberOfDevices + 1];
	boolean[] statuses = new boolean[numberOfDevices + 1];

	for (int i = 1; i <= numberOfDevices; i++) {
	  powerRequirements[i] = Integer.parseInt(scanner.nextLine().trim());
	}

	int totalPower = 0;
	int maxPower = 0;
	boolean fuseBlown = false;

	for (int i = 1; i <= numberOfOperations; i++) {
	  int deviceIndex = Integer.parseInt(scanner.nextLine().trim());

	  if (!fuseBlown) {
		statuses[deviceIndex] = !statuses[deviceIndex];

		if (statuses[deviceIndex]) {
		  totalPower += powerRequirements[deviceIndex];
		} else {
		  totalPower -= powerRequirements[deviceIndex];
		}

		if (totalPower > maxPower) {
		  maxPower = totalPower;
		}

		if (totalPower > fuseCapacity) {
		  fuseBlown = true;
		}
	  }
	}

	System.out.printf("Sequence %d%n", sequenceCount);

	if (fuseBlown) {
	  System.out.println("Fuse was blown.");
	} else {
	  System.out.println("Fuse was not blown.");
	  System.out.printf("Maximal power consumption was %d amperes.%n", maxPower);
	}

	System.out.println();
  }
}
