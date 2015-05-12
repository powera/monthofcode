import java.awt.*;

public class TownPerson {
  private int x;
  private int y;
  private TownModelWindow t;
  private Color personColor=Color.RED;

  public TownPerson(int x, int y, TownModelWindow t) {
    this.x=x;
    this.y=y;
    this.t=t;
  }

  public void draw(Graphics g) {
    g.setColor(personColor);
    g.fillOval(x-1,y-1,3,3);
  }

  public void step() {
    Point p=new Point(x,y);
    Point q=t.getBias(p);
    double bX=1/(1+Math.exp((x-q.getX())/100));
    double bY=1/(1+Math.exp((y-q.getY())/100));
    if(Math.random()<bX) x++;
    if(Math.random()<1-bX) x--;
    if(Math.random()<bY) y++;
    if(Math.random()<1-bY) y--;
  }

}