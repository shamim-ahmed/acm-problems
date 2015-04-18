import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class Main {

  public static void main(String... args) {
    processInput(System.in, System.out);
  }

  public static void processInput(InputStream inputStream, PrintStream outputStream) {
    StringBuilder resultBuilder = new StringBuilder();
    Scanner scanner = new Scanner(inputStream);

    while (scanner.hasNextLine()) {
      int n = Integer.parseInt(scanner.nextLine());

      if (n == 0) {
        break;
      }

      String[] moves = scanner.nextLine().split(" ");
      String state = "+x";

      for (int i = 0; i < moves.length; i++) {
        String nextMove = moves[i];

        if (nextMove.equals("No")) {
          continue;
        }

        if (state.equals("+x")) {
          state = nextMove;
        } else if (state.equals("-x") && nextMove.equals("+y")) {
          state = "-y";
        } else if (state.equals("-x") && nextMove.equals("-y")) {
          state = "+y";
        } else if (state.equals("-x") && nextMove.equals("+z")) {
          state = "-z";
        } else if (state.equals("-x") && nextMove.equals("-z")) {
          state = "+z";
        } else if (state.equals("+y") && nextMove.equals("+y")) {
          state = "-x";
        } else if (state.equals("+y") && nextMove.equals("-y")) {
          state = "+x";
        } else if (state.equals("-y") && nextMove.equals("+y")) {
          state = "+x";
        } else if (state.equals("-y") && nextMove.equals("-y")) {
          state = "-x";
        } else if (state.equals("+z") && nextMove.equals("+z")) {
          state = "-x";
        } else if (state.equals("+z") && nextMove.equals("-z")) {
          state = "+x";
        } else if (state.equals("-z") && nextMove.equals("+z")) {
          state = "+x";
        } else if (state.equals("-z") && nextMove.equals("-z")) {
          state = "-x";
        }
      }

      resultBuilder.append(state).append("\n");
    }

    scanner.close();
    outputStream.print(resultBuilder.toString());
  }
}
