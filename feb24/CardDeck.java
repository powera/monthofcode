import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.text.*;

public class CardDeck {

  private Stack cardList;

  public CardDeck() {
    cardList=new Stack();
    for(int i=0;i<52;i++) {
      cardList.push(new CardSingle((i/13)+1,(i%13)+1));
    }
    Collections.shuffle(cardList);
  }
  
  public CardSingle getTopCard() {
    if(cardList.empty()) return null;
    return (CardSingle)cardList.pop();
  }

}
