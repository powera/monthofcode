import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.text.*;

public class RacingGame extends JApplet {
  RacingWindow myRW;

  public void init() { /* Initializes */
    this.getContentPane().setLayout(new BoxLayout(this.getContentPane(),BoxLayout.X_AXIS));
    myRW=new RacingWindow();
    this.getContentPane().add(myRW);
  }
  
  public void stop() { /* Terminates */
  }
  
}