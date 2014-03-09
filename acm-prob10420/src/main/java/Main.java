import java.util.Map;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class Main {
  public static void main(String... args) {
	Map<String, Counter> frequencyMap = new TreeMap<>();
	Scanner scanner = new Scanner(System.in);
	final int n = Integer.parseInt(scanner.nextLine().trim());
	
	for (int i = 0; i < n; i++) {
	  String inputLine = scanner.nextLine();
	  StringTokenizer tokenizer = new StringTokenizer(inputLine, " ", false);
	  String country = tokenizer.nextToken();
	  Counter freq = frequencyMap.get(country);
	  
	  if (freq == null) {
		freq = new Counter(1);
		frequencyMap.put(country, freq);
	  } else {
		freq.increment();
	  }
	}
	
	scanner.close();
	
	for (Map.Entry<String, Counter> entry : frequencyMap.entrySet()) {
	  System.out.printf("%s %d%n", entry.getKey(), entry.getValue().getValue());
	}
  }
  
  private static class Counter {
	private int value;
	
	public Counter(int value) {
	  this.value = value;
	}
	
	public int getValue() {
	  return value;
	}
	
	public void increment() {
	  value++;
	}
  }
}
