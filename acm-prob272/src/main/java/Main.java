import java.util.Scanner;

public class Main {
  private static final char QUOTE = '"';
  private static final String OPENING_QUOTE_REPLACEMENT = "``";
  private static final String CLOSING_QUOTE_REPLACEMENT = "''";
  
  public static void main(String... args) {
	String separator = System.getProperty("line.separator");
	StringBuilder inputBuilder = new StringBuilder();

	Scanner scanner = new Scanner(System.in);

	while (scanner.hasNextLine()) {
	  inputBuilder.append(scanner.nextLine()).append(separator);
	}

	scanner.close();

	System.out.print(replaceQuotes(inputBuilder));
  }
  
  private static String replaceQuotes(StringBuilder inputBuilder) {
	int i = 0;
	boolean openingQuoteFound = false;
	
	while (i < inputBuilder.length()) {
	  char c = inputBuilder.charAt(i);
	  
	  if (c == QUOTE) {
		if (!openingQuoteFound) {
		  replaceQuote(inputBuilder, OPENING_QUOTE_REPLACEMENT, i);
		} else {
		  replaceQuote(inputBuilder, CLOSING_QUOTE_REPLACEMENT, i);
		}
		
		openingQuoteFound = !openingQuoteFound;
		i += 2; 
	  } else {
		i++;
	  }
	}
	
	return inputBuilder.toString();
  }
  
  private static void replaceQuote(StringBuilder inputBuilder, String replacement, int index) {
	inputBuilder.deleteCharAt(index);
	inputBuilder.insert(index, replacement);
  }
}
