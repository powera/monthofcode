import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class EnemyShip extends SpaceWarsObject {

  private SpaceGameWindow gameWindow;
  
  /* Coordinates of top-left, ship is 10x10 */
  private int x;
  private int y;
  private int state;
  private int lSize=12;
  private int lSpeed=4;
  private double fire_chance;
  private double level;
  private int shipType=1;
  private Color shipColor=Color.RED;
  
  public EnemyShip(SpaceGameWindow w, int xC, int yC, int L) {
    gameWindow=w;
    x=xC;y=yC;state=0;
    level=L;
    if(level>49) level=49;
    if(level>5) lSpeed=3; if(level>25) lSpeed=2;
    fire_chance=.06+level/180;
    if(((level-1)/15)>Math.random()) {shipType=2;shipColor=Color.ORANGE;}
    if(((level-5)/30)>Math.random()) {shipType=3;shipColor=Color.YELLOW;}
    if(((level-15)/30)>Math.random()) {shipType=4;shipColor=Color.GREEN;}
    if(((level-30)/30)>Math.random()) {shipType=5;shipColor=Color.MAGENTA;}
  }
  
  public void runTime() {state++;
    if(state%(lSpeed*lSize)==0 && Math.random()<fire_chance) fire();
    if(state==4*lSpeed*lSize) state=0;
    if(state%lSpeed!=0) return;
    if(state/(lSpeed*lSize)==0) x--;
    else if (state/(lSpeed*lSize)==1) y--;
    else if (state/(lSpeed*lSize)==2) x++;
    else if (state/(lSpeed*lSize)==3) y++;
  }
  
  public void fire() {
    gameWindow.addEnemyMissile(new EnemyMissile(x+10, y-2, shipType));
  }

  public void draw(Graphics g) {
    g.setColor(shipColor);
    g.fillRect(x,y,11,11);
    g.setColor(Color.BLUE);
    g.fillRect(x+4,y+3,3,7);
    g.setColor(Color.BLACK);
    g.fillRect(x,y+9,2,2);
    g.setColor(Color.BLACK);
    g.fillRect(x+9,y+9,2,2);
  }
  
  public int getMinX() {return x;}
  public int getMaxX() {return x+11;}
  public int getMinY() {return y;}
  public int getMaxY() {return y+11;}

}