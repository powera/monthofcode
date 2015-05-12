import javax.swing.*;
import java.awt.*;

public class RacingCar {
  private int curX;
  private int curY;
  public RacingCar(int x, int y) {curX=x; curY=y;}

  public void move(int x, int y) {curX=x; curY=y;}
  public int getX() {return curX;}
  public int getY() {return curY;}

  public void drawCar(Draw3D w) {
    w.draw3DRectangle(Color.BLACK,curX-10,curY,8,curX-10,curY+35,0,Draw3D.PLANE_XY);
    w.draw3DRectangle(Color.BLACK,curX+10,curY,8,curX+10,curY+35,0,Draw3D.PLANE_XY);
    w.draw3DRectangle(Color.BLACK,curX-10,curY+35,8,curX+10,curY+35,0,Draw3D.PLANE_YZ);
    w.draw3DRectangle(Color.BLACK,curX-10,curY,8,curX+10,curY,0,Draw3D.PLANE_YZ);
    w.draw3DRectangle(Color.RED, curX-10,curY,8,curX+10,curY+35,8,Draw3D.PLANE_YZ);
    w.draw3DRectangle(Color.BLUE, curX-8,curY+4,15,curX+8,curY+24,15,Draw3D.PLANE_YZ);
    w.draw3DLine(Color.BLUE,curX-8,curY+4,15,curX-10,curY,5);
    w.draw3DLine(Color.BLUE,curX+8,curY+4,15,curX+10,curY,5);
    w.draw3DLine(Color.BLUE,curX-8,curY+24,15,curX-10,curY+31,5);
    w.draw3DLine(Color.BLUE,curX+8,curY+24,15,curX+10,curY+31,5);
  }

}