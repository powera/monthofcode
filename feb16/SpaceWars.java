import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.text.*;

public class SpaceWars extends JApplet {
  SpaceGameWindow mySGW;

  public void init() { /* Initializes */
    this.getContentPane().setLayout(new BoxLayout(this.getContentPane(),BoxLayout.X_AXIS));
    mySGW=new SpaceGameWindow();
    this.getContentPane().add(mySGW);
  }
  
  public void stop() { /* Terminates */
  }
  
}