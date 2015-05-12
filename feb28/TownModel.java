import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.text.*;

public class TownModel extends JApplet {
  TownModelWindow myTW;

  public void init() { /* Initializes */
    this.getContentPane().setLayout(new BoxLayout(this.getContentPane(),BoxLayout.X_AXIS));
    myTW=new TownModelWindow();
    this.getContentPane().add(myTW);
  }
  
  public void stop() { /* Terminates */
  }
  
}