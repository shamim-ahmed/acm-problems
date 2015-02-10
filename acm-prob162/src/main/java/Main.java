import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Main {
  private static final String INPUT_TERMINATOR = "#";
  
  private static class Card {
    private char suite;
    private char rank;
   
    public Card(char suite, char rank) {
      this.suite = suite;
      this.rank = rank;
    }
    
    public boolean isFaceCard() {
      return rank == 'J' || rank == 'Q' || rank == 'K' || rank == 'A';
    }
    
    @Override
    public String toString() {
      return String.format("%c%c", suite, rank);
    }
  }
  
  public static void main(String... args) {
    Scanner scanner = new Scanner(System.in);
    
    while (scanner.hasNextLine()) {
      String line = scanner.nextLine().trim();
      
      if (line.equals(INPUT_TERMINATOR)) {
        break;
      }
      
      // we have already read the first line of the deck
      List<Card> cardList = new ArrayList<>();
      parseLine(line, cardList);
      
      // let us read the next three lines of the deck
      for (int i = 0; i < 3; i++) {
        line = scanner.nextLine().trim();
        parseLine(line, cardList);
      }
      
      // at this point, we have read the whole deck. So we can start the game.
      playWithDeck(cardList);
    }
    
    scanner.close();
  }
  
  private static void parseLine(String line, List<Card> cardList) {
    StringTokenizer tokenizer = new StringTokenizer(line, " ", false);
    
    while (tokenizer.hasMoreTokens()) {
      String token = tokenizer.nextToken();
      char s = token.charAt(0);
      char r = token.charAt(1);
      cardList.add(new Card(s, r));
    }
  }
  
  private static void playWithDeck(List<Card> cardList) {
    
  }
}
