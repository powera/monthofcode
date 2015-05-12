import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.text.*;

public class CardGame extends JApplet {
  CardGamePanel myCG;

  public void init() { /* Initializes */
    myCG=new CardGamePanel();
    this.getContentPane().add(myCG);
  }
  
  public void stop() { /* Terminates */
  }
  
}
