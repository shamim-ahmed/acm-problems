import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class Main {
  private static final int TOKEN_PER_LINE = 3;

  public static void main(String... args) {
	List<int[]> tuples = new ArrayList<int[]>();
	Scanner scanner = new Scanner(System.in);

	while (scanner.hasNextLine()) {
	  String line = scanner.nextLine();

	  // check for empty line
	  if (line.trim().equals("")) {
		continue;
	  }

	  StringTokenizer tokenizer = new StringTokenizer(line, " ", false);

	  // check for invalid input
	  if (tokenizer.countTokens() != TOKEN_PER_LINE) {
		System.err.printf("Invalid input: %s%n", line);
		System.exit(1);
	  }

	  int[] values = new int[TOKEN_PER_LINE];

	  for (int i = 0; i < TOKEN_PER_LINE; i++) {
		values[i] = Integer.parseInt(tokenizer.nextToken());
	  }

	  tuples.add(values);
	}

	scanner.close();
	process(tuples);
  }

  private static void process(List<int[]> tuples) {
	if (tuples.size() == 0) {
	  return;
	}

	TreeSet<Integer> valueSet = new TreeSet<Integer>();

	for (int[] tuple : tuples) {
	  valueSet.add(tuple[0]);
	  valueSet.add(tuple[2]);
	}

	Integer[] values = valueSet.toArray(new Integer[valueSet.size()]);
	List<int[]> ranges = new ArrayList<int[]>();

	for (int i = 0; i < values.length - 1; i++) {
	  int x = values[i];
	  int y = values[i + 1];
	  int height = 0;
	  int j = 0;
	  int[] tuple = tuples.get(j);

	  while (tuple[0] <= x) {
		if (tuple[0] <= x && tuple[2] >= y && tuple[1] > height) {
		  height = tuple[1];
		}

		j++;

		if (j >= tuples.size()) {
		  break;
		}

		tuple = tuples.get(j);
	  }

	  ranges.add(new int[] { x, height, y });
	}

	printResult(ranges);
  }

  private static void printResult(List<int[]> tuples) {
	StringBuilder resultBuilder = new StringBuilder();
	int i = 0;

	while (i < tuples.size()) {
	  int n = tuples.get(i)[1];
	  int j = i;
	  int k = j + 1;

	  while ((k < tuples.size()) && tuples.get(k)[1] == n) {
		j = k;
		k++;
	  }

	  if (i != j) {
		int[] firstTuple = tuples.get(i);
		int[] lastTuple = tuples.get(j);

		if (i == 0) {
		  resultBuilder.append(String.format("%d %d %d ", firstTuple[0], n, lastTuple[2]));
		} else {
		  resultBuilder.append(String.format("%d %d ", n, lastTuple[2]));
		}

		i = j + 1;
	  } else {
		int[] tuple = tuples.get(i);

		if (i == 0) {
		  resultBuilder.append(String.format("%d %d %d ", tuple[0], n, tuple[2]));
		} else {
		  resultBuilder.append(String.format("%d %d ", n, tuple[2]));
		}
		
		i++;
	  }
	}

	resultBuilder.append("0");
	System.out.println(resultBuilder.toString());
  }
}
