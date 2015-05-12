import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.text.*;

public class CardHand {

  private Vector cardList;
  private boolean reDraw[];
  private CardDeck deck;
  
  public CardHand(CardDeck c, int numCards) {
    cardList=new Vector();
    reDraw=new boolean[5];
    if(numCards>52) numCards=52;
    if(numCards<1) numCards=1;
    for(int i=0;i<numCards;i++) {
      cardList.add(c.getTopCard());
    }
    deck=c;
  }
	  
  public void paintHand(Graphics g, int xMin, int yMin) {
    for(int i=0;i<cardList.size();i++) {
      if(reDraw[i]) {g.setColor(Color.RED);g.fillRect(xMin+55*i-5,yMin-5,50,70);}
      ((CardSingle)cardList.elementAt(i)).paintCard(g,xMin+55*i, yMin);
    }
  }

  public void changeStatus(int cNum) {
    reDraw[cNum]=!reDraw[cNum];
  }

  public void reDraw() {
    for(int i=0;i<5;i++) {
      if(reDraw[i]) cardList.setElementAt(deck.getTopCard(),i);
    }
    reDraw=new boolean[5];
  }
  
  public String getPokerValue() {
    /* Make array of values and suits */
    int[] suit=new int[5];
    int[] rank=new int[14];
    int i;
    for(i=0;i<cardList.size();i++) {
      rank[((CardSingle)cardList.elementAt(i)).getRank()]++;
      suit[((CardSingle)cardList.elementAt(i)).getSuit()]++;
    }
    int isStraight=0; int isFlush=0;
    for(i=1;i<11;i++) {
      if(rank[i]==1 && rank[i+1]==1 && rank[i+2]==1 && rank[i+3]==1 && rank[(i==10?1:i+4)]==1) isStraight=1;
    }
    if (suit[1]==5 || suit[2]==5 || suit[3]==5 || suit[4]==5) isFlush=1;
    if(isStraight==1 && isFlush==1) return "Straight Flush";
    if(isStraight==1) return "Straight";
    if(isFlush==1) return "Flush";
    Arrays.sort(rank);
    if(rank[13]==4) return "Four of a Kind";
    if(rank[13]==3 && rank[12]==2) return "Full House";
    if(rank[13]==3) return "Three of a Kind";
    if(rank[13]==2 && rank[12]==2) return "Two Pair";
    if(rank[13]==2) return "One Pair";
    return "Nothing";
  }
}
