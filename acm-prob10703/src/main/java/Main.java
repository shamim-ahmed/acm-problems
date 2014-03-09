import java.util.Scanner;
import java.util.StringTokenizer;

public class Main {
  private static final int MAX = 500;
  private static final String SPACE = " ";
  private static final String EMPTY_LINE = "";
  
  public static void main(String... args) {
    final boolean[][] board = new boolean[MAX][MAX];
    Scanner scanner = new Scanner(System.in);
    
    while (scanner.hasNextLine()) {
      String line = scanner.nextLine();
      
      if (line.trim().equals(EMPTY_LINE)) {
        continue;
      }
      
      StringTokenizer tokenizer = new StringTokenizer(line, SPACE, false);
      int width = Integer.parseInt(tokenizer.nextToken());
      int height = Integer.parseInt(tokenizer.nextToken());
      int n = Integer.parseInt(tokenizer.nextToken());
      
      if (width == 0 && height == 0 && n == 0) {
        break;
      }
      
      clear(board, width, height);
      int count = width * height;
      
      for (int k = 1; k <= n; k++) {
        String input = scanner.nextLine();
        StringTokenizer inputTokenizer = new StringTokenizer(input);
        int x1 = Integer.parseInt(inputTokenizer.nextToken()) - 1;
        int y1 = Integer.parseInt(inputTokenizer.nextToken()) - 1;
        int x2 = Integer.parseInt(inputTokenizer.nextToken()) - 1;
        int y2 = Integer.parseInt(inputTokenizer.nextToken()) - 1;
        
        int x, y, delx, dely;
        
        if (x1 < x2) {
          x = x1;
          delx = x2 - x1;
        } else {
          x = x2;
          delx = x1 - x2;
        }
        
        if (y1 < y2) {
          y = y1;
          dely = y2 - y1;
        } else {
          y = y2;
          dely = y1 - y2;
        }
        
        for (int i = x, p = x + delx; i <= p; i++) {
          for (int j = y, q = y + dely; j <= q; j++) {
            if (board[i][j] == false) {
              count--;
            }
            
            board[i][j] = true;
          }
        }
      }
      
      if (count == 0) {
        System.out.println("There is no empty spots.");
      } else if (count == 1) {
        System.out.println("There is one empty spot.");
      } else {
        System.out.printf("There are %d empty spots.%n", count);
      }
    }
    
    scanner.close();
  }
  
  private static void clear(boolean[][] board, int w, int h) {
    for (int i = 0; i < w; i++) {
      for (int j = 0; j < h; j++) {
        board[i][j] = false;
      }
    }
  }
}
