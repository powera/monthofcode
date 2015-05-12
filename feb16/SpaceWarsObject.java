import java.awt.*;

public abstract class SpaceWarsObject {
  public abstract void draw(Graphics g);
  public abstract int getMinX();
  public abstract int getMinY();
  public abstract int getMaxX();
  public abstract int getMaxY();
  public boolean intersect(SpaceWarsObject o) {    
    if(o.getMinX()>getMaxX() || o.getMaxX()<getMinX() || o.getMinY()>getMaxY() || o.getMaxY()<getMinY()) return false;
    return true;
  }
}