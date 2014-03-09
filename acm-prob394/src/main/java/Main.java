import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Main {
  private static final String BLANK = " ";
  private static Map<String, ArrayInfo> arrayInfoMap = new HashMap<>();

  public static void main(String... args) {
	Scanner scanner = new Scanner(System.in);
	String firstLine = scanner.nextLine();
	StringTokenizer tokenizer = new StringTokenizer(firstLine, BLANK, false);
	int numberOfArrays = Integer.parseInt(tokenizer.nextToken());
	int inputCount = Integer.parseInt(tokenizer.nextToken());

	for (int i = 0; i < numberOfArrays; i++) {
	  ArrayInfo arrayInfo = new ArrayInfo(scanner.nextLine());
	  arrayInfoMap.put(arrayInfo.getName(), arrayInfo);
	}

	for (int i = 0; i < inputCount; i++) {
	  printAddress(scanner.nextLine());
	}

	scanner.close();
  }

  private static void printAddress(String input) {
	StringTokenizer tokenizer = new StringTokenizer(input, BLANK, false);
	String arrayName = tokenizer.nextToken();
	ArrayInfo arrayInfo = arrayInfoMap.get(arrayName);

	if (arrayInfo == null) {
	  throw new RuntimeException(String.format("Invalid array name: %s", arrayName));
	}

	int dimension = arrayInfo.getDimension();
	int[] upperBounds = arrayInfo.getUpperBounds();
	int[] lowerBounds = arrayInfo.getLowerBounds();

	int[] indexArray = new int[dimension + 1];
	indexArray[0] = 1; // this is done just to make the calculation easier later

	for (int i = 1; i <= dimension; i++) {
	  indexArray[i] = Integer.parseInt(tokenizer.nextToken());
	}

	int[] c = new int[dimension + 1];

	c[dimension] = arrayInfo.getElementSize();

	for (int i = dimension - 1; i > 0; i--) {
	  c[i] = c[i + 1] * (upperBounds[i + 1] - lowerBounds[i + 1] + 1);
	}

	c[0] = arrayInfo.getBaseAddress();

	for (int i = 1; i <= dimension; i++) {
	  c[0] -= c[i] * lowerBounds[i];
	}
	
	// now we are ready to compute the address
	int address = 0;

	for (int i = 0; i <= dimension; i++) {
	  address += c[i] * indexArray[i];
	}

	String indexStr = getIndexString(indexArray);
	System.out.printf("%s[%s] = %d%n", arrayName, indexStr, address);
  }

  private static String getIndexString(int[] indexArray) {
	StringBuilder sb = new StringBuilder();

	for (int i = 1, n = indexArray.length; i < n; i++) {
	  sb.append(indexArray[i]);

	  if (i < n - 1) {
		sb.append(", ");
	  }
	}

	return sb.toString();
  }

  private static class ArrayInfo {
	private final String name;
	private final int baseAddress;
	private final int elementSize;
	private final int dimension;
	private final int[] lowerBounds;
	private final int[] upperBounds;

	public ArrayInfo(String input) {
	  StringTokenizer tokenizer = new StringTokenizer(input, BLANK, false);
	  name = tokenizer.nextToken();
	  baseAddress = Integer.parseInt(tokenizer.nextToken());
	  elementSize = Integer.parseInt(tokenizer.nextToken());
	  dimension = Integer.parseInt(tokenizer.nextToken());
	  lowerBounds = new int[dimension + 1];
	  upperBounds = new int[dimension + 1];

	  for (int i = 1; i <= dimension; i++) {
		lowerBounds[i] = Integer.parseInt(tokenizer.nextToken());
		upperBounds[i] = Integer.parseInt(tokenizer.nextToken());
	  }
	}

	public String getName() {
	  return name;
	}

	public int getBaseAddress() {
	  return baseAddress;
	}

	public int getElementSize() {
	  return elementSize;
	}

	public int getDimension() {
	  return dimension;
	}

	public int[] getLowerBounds() {
	  return lowerBounds;
	}

	public int[] getUpperBounds() {
	  return upperBounds;
	}
  }
}
