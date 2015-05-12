import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.text.*;

public class CardSingle {

  public static final int SUIT_HEARTS=1;
  public static final int SUIT_DIAMONDS=2;
  public static final int SUIT_CLUBS=3;
  public static final int SUIT_SPADES=4;

  public static final int RANK_ACE=1;
  public static final int RANK_TWO=2;
  public static final int RANK_THREE=3;
  public static final int RANK_FOUR=4;
  public static final int RANK_FIVE=5;
  public static final int RANK_SIX=6;
  public static final int RANK_SEVEN=7;
  public static final int RANK_EIGHT=8;
  public static final int RANK_NINE=9;
  public static final int RANK_TEN=10;
  public static final int RANK_JACK=11;
  public static final int RANK_QUEEN=12;
  public static final int RANK_KING=13;

  private int suit;
  private int rank;

  private final String[] rankName={"", "A", "2", "3", "4", "5", "6", "7",
	  "8", "9", "10", "J", "Q", "K"};

  private final String[] suitName={"", "\u2665", "\u2666", "\u2663", "\u2660"}; 
  public CardSingle(int mySuit, int myRank) {
    suit=mySuit; rank=myRank;
    cardFont=new Font("Dialog", Font.PLAIN, 14);
  }

  private Font cardFont;
  public int getSuit() { return suit; }
  public int getRank() { return rank; }

  public void paintCard(Graphics g, int xMin, int yMin) {
    g.setFont(cardFont);
    int width=40; int height=60;
    g.setColor(Color.BLUE);
    g.fillRoundRect(xMin, yMin, width, height,2,2);
    g.setColor(Color.WHITE);
    g.fillRoundRect(xMin+1, yMin+1, width-2, height-2,2,2);
    switch(suit) {
     case SUIT_HEARTS: 
      g.setColor(Color.RED);
      break;
     case SUIT_DIAMONDS: 
      g.setColor(Color.RED);
     break;
     case SUIT_CLUBS: 
      g.setColor(Color.BLACK);
     break;
     case SUIT_SPADES: 
      g.setColor(Color.BLACK);
     break;
    }
  g.drawString(rankName[rank],xMin+3,yMin+16);
  g.drawString(suitName[suit],xMin+12,yMin+38);
  g.drawString(rankName[rank],xMin+37-g.getFontMetrics().stringWidth(rankName[rank]),yMin+57);
  }
  
}
