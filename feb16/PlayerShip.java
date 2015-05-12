import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class PlayerShip extends SpaceWarsObject {

  /* Coordinates of top-left, ship is 20x20 */
  private SpaceGameWindow gameWindow;
  private int x;
  private int y;
  private int timeout;
  private int shotsFired;
  private boolean autofire;
  
  public PlayerShip(SpaceGameWindow w) {
    gameWindow=w;
    x=200;y=400;timeout=0;
  }
  
  public void runTime() {if(timeout>0) timeout-=1;
    if(timeout==0 && autofire) fire();}
  
  public void moveTo(int xC, int yC) {
    if(xC<15) xC=15; if(xC>385) xC=385;
    if(yC<300) yC=300; if(yC>425) yC=425;
    x=xC-10;
    y=yC-10;
  }
  
  public void fire() {
    if(timeout!=0) return;
    timeout=10;
    gameWindow.addPlayerMissile(new PlayerMissile(x+10, y-2));
  }

  public void draw(Graphics g) {
    g.setColor(Color.RED);
    g.fillRect(x,y,21,15);
    g.setColor(Color.GREEN);
    g.fillRect(x+5,y+5,11,15);
    g.setColor(Color.GRAY);
    g.fillRect(x+9,y+1,3,10);
    if(timeout==0) {g.setColor(Color.YELLOW);
      g.fillRect(x+9,y-1,3,3);
    }
  }
  
  public void autoFire(boolean b) {
    autofire=b;
  }
  public void autoFire() {
    autofire=!autofire;
  }
  
  public int getMinX() { return x; }
  public int getMinY() { return y; }
  public int getMaxX() { return x+21; }
  public int getMaxY() { return y+21; }

}