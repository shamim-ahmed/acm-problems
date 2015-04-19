import java.io.InputStream;
import java.io.PrintStream;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Scanner;

public class Main {

  public static void main(String... args) {
    processInput(System.in, System.out);
  }

  public static void processInput(InputStream inputStream, PrintStream outputStream) {
    StringBuilder resultBuilder = new StringBuilder();
    Scanner scanner = new Scanner(inputStream);
    DecimalFormat decimalFormat = new DecimalFormat();
    decimalFormat.setMaximumFractionDigits(2);
    decimalFormat.setMinimumFractionDigits(2);

    while (scanner.hasNextLine()) {
      String firstName = scanner.nextLine().toLowerCase();
      String secondName = scanner.nextLine().toLowerCase();
            
      int firstNameValue = evaluate(firstName.toCharArray());
      int secondNameValue = evaluate(secondName.toCharArray());
      
      if (firstNameValue == 0 && secondNameValue == 0) {
        resultBuilder.append("\n");
        continue;
      }
            
      BigDecimal dividend, divisor;
      
      if (firstNameValue < secondNameValue) {
        dividend = BigDecimal.valueOf(firstNameValue);
        divisor = BigDecimal.valueOf(secondNameValue);
      } else {
        dividend = BigDecimal.valueOf(secondNameValue);
        divisor = BigDecimal.valueOf(firstNameValue);
      }
      
      BigDecimal result = dividend.divide(divisor, 4, BigDecimal.ROUND_HALF_UP);
      result = result.multiply(BigDecimal.valueOf(100.0));
      resultBuilder.append(decimalFormat.format(result)).append(" %").append("\n");
    }
    
    scanner.close();
    outputStream.print(resultBuilder.toString());
  }
  
  private static int evaluate(char[] nameChars) {
    int result = 0;
    
    for (int i = 0; i < nameChars.length; i++) {
      char c = nameChars[i];
      
      if (Character.isLetter(c)) {
        result += (c - 'a' + 1);
      }
    }
    
    while (result >= 10) {
      char[] digits = Integer.toString(result).toCharArray();
      int temp = 0;
      
      for (int i = 0; i < digits.length; i++) {
        temp += (digits[i] - '0');
      }
      
      result = temp;
    }
    
    return result;
  }
}
