import javax.swing.*;
import java.awt.*;

public class RacingTrack {

  /* Unit of distance is ~6 inches */
  private double length;
  private double width;

  private Color trackColor=Color.GRAY;
  private Color sidelineColor=Color.WHITE;
  private Color groundColor=Color.GREEN;
  private Color skyColor;

  private RacingCar mainCar;
  private RacingCar secondCar;

  public void setCoords(int x, int y) {
    mainCar.move((x-250)*2/3,500-y);
  }

  public void tick() {
    secondCar.move(secondCar.getX(),secondCar.getY()+3);
    if(secondCar.getY()>450) {secondCar.move(secondCar.getX(),0);
    defaultCross=(int)(100+Math.random()*250); }
  }

  public RacingTrack() {
    mainCar=new RacingCar(0,0);
    secondCar=new RacingCar(40,0);
    length=200;
    width=20;
    skyColor=new Color(80,120,255);
  }

  private int defaultCross=245;

  public void drawTrack(Graphics g, int xMin, int xMax, int yMin, int yMax) {
    Draw3D w=new Draw3D(g, xMin, xMax, yMin, yMax);

    int xWidth=(xMax-xMin); int yHeight=(yMax-yMin);
    g.setColor(skyColor);
    g.fillRect(xMin, yMin, xWidth, yHeight/2);
    g.setColor(groundColor);
    g.fillRect(xMin, (yMax+yMin)/2, xWidth, yHeight/2);
    w.draw3DRectangle(trackColor, -xWidth/5, 0, 0, xWidth/5,520,0,Draw3D.PLANE_YZ);
    w.draw3DRectangle(sidelineColor, xWidth/5-2, 0,0, xWidth/5-5,520,0,Draw3D.PLANE_YZ);
    w.draw3DRectangle(sidelineColor, -xWidth/5+5,0,0,-xWidth/5+2,520,0,Draw3D.PLANE_YZ);
    w.draw3DRectangle(Color.YELLOW, -7, 0,0, -4,520,0,Draw3D.PLANE_YZ);
    w.draw3DRectangle(Color.YELLOW, 4,0,0,7,520,0,Draw3D.PLANE_YZ);

    int crossY=defaultCross;
    w.draw3DRectangle(trackColor, -300, crossY-35, 0, 300,crossY+35,0,Draw3D.PLANE_YZ);
    w.draw3DRectangle(sidelineColor, -300, crossY-34,0, 300,crossY-32,0,Draw3D.PLANE_YZ);
    w.draw3DRectangle(sidelineColor, -300,crossY+32,0,300,crossY+34,0,Draw3D.PLANE_YZ);
    w.draw3DRectangle(Color.YELLOW, -300, crossY-5,0, 300,crossY-2,0,Draw3D.PLANE_YZ);
    w.draw3DRectangle(Color.YELLOW, -300,crossY+2,0,300,crossY+5,0,Draw3D.PLANE_YZ);
    w.draw3DRectangle(trackColor, -xWidth/5+4, crossY-34, 0, xWidth/5-4,crossY+34,0,Draw3D.PLANE_YZ);

    w.draw3DRectangle(Color.BLUE, -95,crossY+50,0,-95,crossY+105,90,Draw3D.PLANE_XZ);
    w.draw3DRectangle(Color.BLACK, -95,crossY+65,10,-95,crossY+85,60,Draw3D.PLANE_XZ);
    w.draw3DRectangle(Color.ORANGE, -95,crossY+50,0,-135,crossY+50,120,Draw3D.PLANE_YZ);
    w.draw3DRectangle(Color.WHITE, -105,crossY+50,40,-125,crossY+50,60,Draw3D.PLANE_YZ);
    w.draw3DRectangle(Color.RED, -95,crossY+50,90,-135,crossY+105,120,Draw3D.PLANE_XZ);

    w.draw3DRectangle(Color.MAGENTA, 95,crossY+50,0,95,crossY+105,90,Draw3D.PLANE_XZ);
    w.draw3DRectangle(Color.BLACK, 95,crossY+65,10,95,crossY+85,60,Draw3D.PLANE_XZ);
    w.draw3DRectangle(Color.RED, 95,crossY+50,0,135,crossY+50,120,Draw3D.PLANE_YZ);
    w.draw3DRectangle(Color.WHITE, 105,crossY+50,40,125,crossY+50,60,Draw3D.PLANE_YZ);
    w.draw3DRectangle(Color.BLUE, 95,crossY+50,90,135,crossY+105,120,Draw3D.PLANE_XZ);

    crossY-=155;
    w.draw3DRectangle(Color.ORANGE, 95,crossY+50,0,95,crossY+105,90,Draw3D.PLANE_XZ);
    w.draw3DRectangle(Color.BLACK, 95,crossY+65,10,95,crossY+85,60,Draw3D.PLANE_XZ);
    w.draw3DRectangle(Color.MAGENTA, 95,crossY+50,0,135,crossY+50,120,Draw3D.PLANE_YZ);
    w.draw3DRectangle(Color.WHITE, 105,crossY+50,40,125,crossY+50,60,Draw3D.PLANE_YZ);
    w.draw3DRectangle(Color.LIGHT_GRAY, 95,crossY+50,90,135,crossY+105,120,Draw3D.PLANE_XZ);

    /* mainCar.drawCar(w); */
    secondCar.drawCar(w);
    /* 
    draw3DCircle(g,curX-13,curY+27,0,curX-9,curY+31,0);
    draw3DCircle(g,curX-13,curY+5,0,curX-9,curY+9,0); */
  }


}
