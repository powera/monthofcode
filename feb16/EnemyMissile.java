import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class EnemyMissile extends SpaceWarsObject {

  private int x;
  private int y;
  private double speed;
  public EnemyMissile(int xC, int yC) {
    x=xC;
    y=yC;
    speed=1;
  }
  
  public EnemyMissile(int xC, int yC, int s) {
    x=xC;
    y=yC;
    speed=s;
  }
  
  public void draw(Graphics g) {
    g.setColor(Color.BLUE);
    g.fillRect(x,y-1,1,2);
  }
  
  public boolean move() {
    y+=speed;
    if(((speed-1)/20)>Math.random()) x+=1;
    if(((speed-1)/20)>Math.random()) x-=1;
    if(y>425) return false;
    return true;
  }
  
  public int getMinX() {return x;}
  public int getMinY() {return y-1;}
  public int getMaxX() {return x;}
  public int getMaxY() {return y;}
  
}