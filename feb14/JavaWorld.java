import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.text.*;

public class JavaWorld extends JApplet {
  public WorldViewer myWV;
  public ChatWindow myCW;
  public void init() { /* Initializes */
    this.getContentPane().setLayout(new BoxLayout(this.getContentPane(),BoxLayout.Y_AXIS));
    myWV=new WorldViewer();
    myCW=new ChatWindow(myWV);
    this.getContentPane().add(myWV);
    this.getContentPane().add(myCW);
  }
  
  public void stop() { /* Terminates */
  }
  
}