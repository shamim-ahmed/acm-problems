import java.util.Scanner;
import java.util.StringTokenizer;

public class Main {
  public static void main(String... args) {
	Scanner scanner = new Scanner(System.in);
	String firstLine = scanner.nextLine();
	boolean[][] grid = getGrid(firstLine);

	while (scanner.hasNextLine()) {
	  String initialStatus = scanner.nextLine();
	  String instructions = scanner.nextLine();
	  Robot robot = new Robot(grid, initialStatus);

	  for (int i = 0, n = instructions.length(); i < n; i++) {
		robot.updateState(instructions.charAt(i));
	  }

	  robot.printState();
	}

	scanner.close();
  }

  private static boolean[][] getGrid(String dimensionLine) {
	StringTokenizer dimensionTokenizer = new StringTokenizer(dimensionLine, " ", false);
	int m = Integer.parseInt(dimensionTokenizer.nextToken()) + 1;
	int n = Integer.parseInt(dimensionTokenizer.nextToken()) + 1;

	return new boolean[m][n];
  }

  private enum Orientation {
	NORTH, SOUTH, EAST, WEST;

	@Override
	public String toString() {
	  String result = null;

	  switch (this) {
		case NORTH:
		  result = "N";
		  break;
		case SOUTH:
		  result = "S";
		  break;
		case EAST:
		  result = "E";
		  break;
		case WEST:
		  result = "W";
		  break;
	  }

	  return result;
	}
  }

  private static class Robot {
	private final boolean[][] grid;
	private int x;
	private int y;
	private Orientation currentOrientation;
	private boolean withinGrid;

	public Robot(boolean[][] matrix, String initialStatus) {
	  grid = matrix;

	  StringTokenizer tokenizer = new StringTokenizer(initialStatus, " ", false);
	  x = Integer.parseInt(tokenizer.nextToken());
	  y = Integer.parseInt(tokenizer.nextToken());
	  String initialDirection = tokenizer.nextToken();

	  if (initialDirection.equals("N")) {
		currentOrientation = Orientation.NORTH;
	  } else if (initialDirection.equals("S")) {
		currentOrientation = Orientation.SOUTH;
	  } else if (initialDirection.equals("E")) {
		currentOrientation = Orientation.EAST;
	  } else if (initialDirection.equals("W")) {
		currentOrientation = Orientation.WEST;
	  } else {
		throw new RuntimeException(String.format("Unknown direction %s", initialDirection));
	  }

	  withinGrid = true;
	}

	public void updateState(char c) {
	  if (withinGrid) {
		switch (c) {
		  case 'R':
			rotateRight();
			break;
		  case 'L':
			rotateLeft();
			break;
		  case 'F':
			moveForward();
			break;
		  default:
			throw new RuntimeException(String.format("Unrecognized instruction: %s", Character.toString(c)));
		}
	  }
	}

	private void rotateRight() {
	  switch (currentOrientation) {
		case NORTH:
		  currentOrientation = Orientation.EAST;
		  break;
		case EAST:
		  currentOrientation = Orientation.SOUTH;
		  break;
		case SOUTH:
		  currentOrientation = Orientation.WEST;
		  break;
		case WEST:
		  currentOrientation = Orientation.NORTH;
		  break;
	  }
	}

	private void rotateLeft() {
	  switch (currentOrientation) {
		case NORTH:
		  currentOrientation = Orientation.WEST;
		  break;
		case WEST:
		  currentOrientation = Orientation.SOUTH;
		  break;
		case SOUTH:
		  currentOrientation = Orientation.EAST;
		  break;
		case EAST:
		  currentOrientation = Orientation.NORTH;
		  break;
	  }
	}

	private void moveForward() {
	  int x1 = x;
	  int y1 = y;

	  switch (currentOrientation) {
		case NORTH:
		  y1++;
		  break;
		case SOUTH:
		  y1--;
		  break;
		case EAST:
		  x1++;
		  break;
		case WEST:
		  x1--;
		  break;
	  }

	  if (x1 >= 0 && x1 < grid.length && y1 >= 0 && y1 < grid[0].length) {
		x = x1;
		y = y1;
	  } else {
		if (!grid[x][y]) {
		  withinGrid = false;
		  grid[x][y] = true;
		}
	  }
	}

	public void printState() {
	  if (!withinGrid) {
		System.out.printf("%d %d %s LOST%n", x, y, currentOrientation);
	  } else {
		System.out.printf("%d %d %s%n", x, y, currentOrientation);
	  }
	}

  }
}
