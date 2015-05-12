import java.awt.*;

public class Draw3D {
  public static final byte PLANE_XY=1;
  public static final byte PLANE_XZ=2;
  public static final byte PLANE_YZ=3;

  private Graphics g;

  private int xMin;
  private int xMax;
  private int xCenter() {return (xMin+xMax)/2;}
  private int yMin;
  private int yMax;
  private int yCenter() {return (yMin+yMax)/2;}

  public Draw3D(Graphics gIn, int xMinIn, int xMaxIn, int yMinIn, int yMaxIn) {
    g=gIn; xMin=xMinIn; xMax=xMaxIn; yMin=yMinIn; yMax=yMaxIn;
  }


  public void draw3DRectangle(Color c, int x1, int y1, int z1, int x2, int y2, int z2, byte plane) {
    g.setColor(c);
    switch(plane) {
     case PLANE_YZ: {
      int xCoords[]={xPC(x1,y1,z1),xPC(x1,y2,z2),xPC(x2,y2,z2),xPC(x2,y1,z1)};
      int yCoords[]={yPC(x1,y1,z1),yPC(x1,y2,z2),yPC(x2,y2,z2),yPC(x2,y1,z1)};
      g.fillPolygon(xCoords, yCoords, 4);}break;
     case PLANE_XZ: {
      int xCoords[]={xPC(x1,y1,z1),xPC(x1,y2,z1),xPC(x2,y2,z2),xPC(x2,y1,z2)};
      int yCoords[]={yPC(x1,y1,z1),yPC(x1,y2,z1),yPC(x2,y2,z2),yPC(x2,y1,z2)};
      g.fillPolygon(xCoords, yCoords, 4);}break;
     case PLANE_XY: {
      int xCoords[]={xPC(x1,y1,z1),xPC(x2,y2,z1),xPC(x2,y2,z2),xPC(x1,y1,z2)};
      int yCoords[]={yPC(x1,y1,z1),yPC(x2,y2,z1),yPC(x2,y2,z2),yPC(x1,y1,z2)};
      g.fillPolygon(xCoords, yCoords, 4);}break;
    }
  }

  public void draw3DLine(Color c, int x1, int y1, int z1, int x2, int y2, int z2) {
    g.setColor(c);
    g.drawLine(xPC(x1,y1,z1),yPC(x1,y1,z1),xPC(x2,y2,z2),yPC(x2,y2,z2));
  }

  public void draw3DCircle(Graphics g, int x1, int y1, int z1, int x2, int y2, int z2) {
    g.fillOval(xPC(x1,y1,z1), yPC(x1,y1,z1), xPC(x2,y2,z2)-xPC(x1,y1,z1), yPC(x2,y2,z2)-yPC(x1,y1,z1));
  }

  /* Functions to transform an x-y-z coordinate on a virtual plane to x-y coordinates
   * on the graphics plane.  xMin, xMax, yMin, yMax must be defined correctly.
   * 
   * The virtual plane has y=0 at the bottom, and can go to a maximum of y=520 at the horizon.
   * x=0 is at the middle of the plane, and positive x are on the right side.
   *
   * At the y=0 line, one "unit" of x is equal to 1.5 pixels on the screen.  This will decrease
   * as y increases.  At the horizon, four "units" of x is equal to 1 pixel.
   * The z direction is vertical.  
   */

  private int xPC(int x, int y, int z) {
    int yTrans=(int)(yMax-(yMax-yCenter())*(Math.min(Math.sqrt(y+9)-3,20))/20);
    return xCenter()+2*(yTrans*5/4-yCenter())*(x)/(yMax-yMin);
  }
  private int yPC(int x, int y, int z) {
    int yTrans=(int)(yMax-(yMax-yCenter())*(Math.min(Math.sqrt(y+9)-3,20))/20);
    yTrans-=z*(yTrans*5/4-yCenter())/(yMax-yMin);
    return yTrans;
  }

}