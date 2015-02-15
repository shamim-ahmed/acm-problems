import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Main {
  private static final String INPUT_TERMINATOR = "#";
  private static final int ROW = 4;
  private static final int COLUMN = 13;
  private static final Card[][] DECK_OF_CARDS = new Card[ROW][COLUMN];
  private static final Deque<Card> nonDealerCards = new ArrayDeque<>();
  private static final Deque<Card> dealerCards = new ArrayDeque<>();
  private static final Deque<Card> cardsOnTable = new ArrayDeque<>();
  private static boolean dealerTurn;

  static {
    for (int i = 0; i < ROW; i++) {
      for (int j = 0; j < COLUMN; j++) {
        DECK_OF_CARDS[i][j] = new Card();
      }
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
      parseLine(line, 0);

      // let us read the next three lines of the deck
      for (int i = 1; i < ROW; i++) {
        line = scanner.nextLine().trim();
        parseLine(line, i);
      }

      // at this point, we have read the whole deck. So we can start the game.
      prepareDeck();
    }

    scanner.close();
  }

  private static void parseLine(String line, int i) {
    StringTokenizer tokenizer = new StringTokenizer(line, " ", false);
    
    if (tokenizer.countTokens() != COLUMN) {
      throw new IllegalArgumentException(String.format("invalid input line : %s", line));
    }
    
    int j = 0;

    while (tokenizer.hasMoreTokens()) {
      String token = tokenizer.nextToken();
      char s = token.charAt(0);
      char r = token.charAt(1);
      Card card = DECK_OF_CARDS[i][j];
      card.setSuit(s);
      card.setRank(r);
      j++;
    }
  }

  private static void prepareDeck() {
    nonDealerCards.clear();
    dealerCards.clear();
    cardsOnTable.clear();
    int k = 0;
    
    // distribute the cards
    for (int i = 0; i < ROW; i++) {
      for (int j = 0; j < COLUMN; j++) {
        Card card = DECK_OF_CARDS[i][j];
        
        if (k % 2 == 0) {
          nonDealerCards.addLast(card);
        } else {
          dealerCards.addLast(card);
        }
        
        k++;
      }
    }
    
    dealerTurn = false;
    playWithDeck();
    
    int winner;
    int cardCount;
    
    if (dealerTurn && dealerCards.isEmpty()) {
      // non dealer has won
      winner = 2;
      cardCount = nonDealerCards.size();
    } else {
      // dealer has won
      winner = 1;
      cardCount = dealerCards.size();
    }
    
    System.out.printf("%d %2d%n", winner, cardCount);
  }
  
  private static void playWithDeck() {
    // game is over
    if ((dealerTurn && dealerCards.isEmpty()) || (!dealerTurn && nonDealerCards.isEmpty())) {
      return;
    }
    
    Card card = cardsOnTable.peekLast();
    
    if (card == null || !card.isFaceCard()) {
      if (dealerTurn) {
        cardsOnTable.addLast(dealerCards.removeLast());
      } else {
        cardsOnTable.addLast(nonDealerCards.removeLast());
      }
    } else {
      // we have a face card
      // we need to figure out how many cards to remove, and from which player
      Deque<Card> activeCards = dealerTurn ? dealerCards : nonDealerCards;
      int n = getCardValue(card);
      boolean endOfCycle = true;

      while (n > 0) {
        if (activeCards.isEmpty()) {
          // game is over
          return;
        }
        
        Card tmpCard = activeCards.removeLast();
        cardsOnTable.addLast(tmpCard);
        n--;
        
        if (tmpCard.isFaceCard()) {
          endOfCycle = false;
          break;
        }
      }
      
      if (endOfCycle) {
        // move all cards on the table to the winning pile
        Deque<Card> destinationCards = dealerTurn ? nonDealerCards : dealerCards;
        
        while (!cardsOnTable.isEmpty()) {
          destinationCards.addFirst(cardsOnTable.removeFirst());
        }
      }
    }
    
    dealerTurn = !dealerTurn;
    playWithDeck();
   
  }
  
  private static int getCardValue(Card card) {
    int n = 0;

    char rank = card.getRank();

    switch (rank) {
      case 'J':
        n = 1;
        break;
      case 'Q':
        n = 2;
        break;
      case 'K':
        n = 3;
        break;
      case 'A':
        n = 4;
        break;
      default:
        throw new IllegalArgumentException(String.format("Invalid rank : %c", rank));
    }

    return n;
  }

  private static class Card {
    private char suit;
    private char rank;

    public char getSuit() {
      return suit;
    }

    public void setSuit(char suit) {
      this.suit = suit;
    }

    public char getRank() {
      return rank;
    }

    public void setRank(char rank) {
      this.rank = rank;
    }

    public boolean isFaceCard() {
      return rank == 'J' || rank == 'Q' || rank == 'K' || rank == 'A';
    }

    @Override
    public String toString() {
      return String.format("%c%c", suit, rank);
    }
  }
}
