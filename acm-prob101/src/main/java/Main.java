import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Main {
  private static final String MOVE = "move";
  private static final String PILE = "pile";
  private static final String ONTO = "onto";
  private static final String OVER = "over";
  private static final String QUIT = "quit";

  public static void main(String... args) {
	Scanner scanner = null;

	try {
	  scanner = new Scanner(System.in);
	  int n = Integer.parseInt(scanner.nextLine());

	  List<List<Integer>> table = new ArrayList<List<Integer>>();

	  for (int i = 0; i < n; i++) {
		List<Integer> list = new ArrayList<Integer>();
		list.add(i);
		table.add(list);
	  }

	  while (scanner.hasNextLine()) {
		String line = scanner.nextLine();
		StringTokenizer tokenizer = new StringTokenizer(line, " ", false);

		if (tokenizer.countTokens() == 1) {
		  String token = tokenizer.nextToken();

		  if (token.equalsIgnoreCase(QUIT)) {
			break;
		  } else {
			continue;
		  }
		}

		if (tokenizer.countTokens() != 4) {
		  continue;
		}

		String primaryCommand = tokenizer.nextToken();
		int source = Integer.parseInt(tokenizer.nextToken());
		String secondaryCommand = tokenizer.nextToken();
		int destination = Integer.parseInt(tokenizer.nextToken());

		int sourceIndex = find(table, source);
		int destinationIndex = find(table, destination);

		if (sourceIndex == destinationIndex) {
		  continue;
		}

		if (primaryCommand.equalsIgnoreCase(MOVE) && secondaryCommand.equalsIgnoreCase(ONTO)) {
		  restore(source, table, sourceIndex);
		  restore(destination, table, destinationIndex);
		} else if (primaryCommand.equalsIgnoreCase(MOVE) && secondaryCommand.equalsIgnoreCase(OVER)) {
		  restore(source, table, sourceIndex);
		} else if (primaryCommand.equalsIgnoreCase(PILE) && secondaryCommand.equalsIgnoreCase(ONTO)) {
		  restore(destination, table, destinationIndex);
		}

		List<Integer> sourceList = table.get(sourceIndex);
		List<Integer> destinationList = table.get(destinationIndex);
		move(source, sourceList, destinationList);
	  }

	  print(table);
	} finally {
	  if (scanner != null) {
		scanner.close();
	  }
	}
  }

  private static int find(List<List<Integer>> table, int value) {
	int index = -1;

	for (int i = 0; i < table.size(); i++) {
	  if (table.get(i).contains(value)) {
		index = i;
		break;
	  }
	}

	return index;
  }

  private static void move(int source, List<Integer> sourceList, List<Integer> destinationList) {
	int index = sourceList.indexOf(source);
	List<Integer> items = new ArrayList<Integer>(sourceList.subList(index, sourceList.size()));
	sourceList.removeAll(items);
	destinationList.addAll(items);
  }

  private static void restore(int value, List<List<Integer>> table, int tableIndex) {
	List<Integer> list = table.get(tableIndex);
	int index = list.indexOf(value);
	List<Integer> items = new ArrayList<Integer>(list.subList(index + 1, list.size()));

	for (int n : items) {
	  table.get(n).add(n);
	}

	list.removeAll(items);
  }

  private static void print(List<List<Integer>> table) {
	for (int i = 0, m = table.size(); i < m; i++) {
	  List<Integer> list = table.get(i);

	  System.out.printf("%d:", i);

	  if (list.size() > 0) {
		System.out.print(" ");
	  }

	  for (int j = 0, n = list.size(); j < n; j++) {
		int value = list.get(j);

		if (j == n - 1) {
		  System.out.print(value);
		} else {
		  System.out.printf("%d ", value);
		}
	  }

	  System.out.println();
	}
  }
}
