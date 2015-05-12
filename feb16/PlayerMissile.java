import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class PlayerMissile extends SpaceWarsObject {

  private int x;
  private int y;
  
  public PlayerMissile(int xC, int yC) {
    x=xC;
    y=yC;
  }
  
  public void draw(Graphics g) {
    g.setColor(Color.YELLOW);
    g.fillRect(x-1,y-1,3,3);
  }
  
  public boolean move() {
    y-=2;
    if(y<1) return false;
    return true;
  }
  
  public int getMinX() {return x-1;}
  public int getMinY() {return y-1;}
  public int getMaxX() {return x+1;}
  public int getMaxY() {return y+1;}
  
}