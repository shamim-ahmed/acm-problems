import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
  private static final Pattern PAST_INSTRUCTION_PATTERN = Pattern.compile("SAME AS (\\d+)");
  
  public static void main(String... args) {
    processInput(System.in, System.out);
  }
  
  public static void processInput(InputStream inputStream, PrintStream outputStream) {
    StringBuilder resultBuilder = new StringBuilder();
    Scanner scanner = new Scanner(inputStream);
    final int numberOfCases = Integer.parseInt(scanner.nextLine());
    
    for (int i = 0; i < numberOfCases; i++) {
      final int numberOfInstructions = Integer.parseInt(scanner.nextLine());
      String[] instructionArray = new String[numberOfInstructions];
      int position = 0;
      
      for (int j = 0; j < instructionArray.length; j++) {
        instructionArray[j] = scanner.nextLine().trim();
        position = executeInstruction(instructionArray, j, position);
      }
      
      resultBuilder.append(String.format("%d%n", position));
    }
    
    scanner.close();
    outputStream.print(resultBuilder.toString());
  }
  
  private static int executeInstruction(String[] instructionArray, int index, int position) {
    boolean done = false;
    
    while (!done) {
      String instruction = instructionArray[index];
      
      if (instruction.equals("LEFT")) {
        position--;
        done = true;
      } else if (instruction.equals("RIGHT")) {
        position++;
        done = true;
      } else {
        Matcher matcher = PAST_INSTRUCTION_PATTERN.matcher(instruction);
        
        if (matcher.matches()) {
          index = Integer.parseInt(matcher.group(1)) - 1;
        } else {
          throw new RuntimeException(String.format("invalid instruction %s", instruction));
        }
      }
    }
   
    return position;
  }
}
