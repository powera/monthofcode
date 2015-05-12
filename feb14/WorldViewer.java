import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.regex.*;

public class WorldViewer extends JPanel {
  
  public WorldObject wGround;

  public WorldViewer() {
    wGround=new WorldObject();
  }
  
  public Dimension getPreferredSize() {return new Dimension(250,250);}
  public Dimension getMaximumSize() {return new Dimension(250,250);}
  
  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    wGround.draw(g,0,0,250,250);
  }
  
  public String doAction(String inString) {
  Matcher m;
  if((m=Pattern.compile("^add(.*)\\bon\\b(.*)",Pattern.CASE_INSENSITIVE).matcher(inString)).find()) {
  
    int foundColor=WorldObject.getColor(m.group(1));
    int foundSize=WorldObject.getSize(m.group(1));
    int foundShape=WorldObject.getShape(m.group(1));
    int onFoundColor=WorldObject.getColor(m.group(2));
    int onFoundSize=WorldObject.getSize(m.group(2));
    int onFoundShape=WorldObject.getShape(m.group(2));
    try {WorldObject onObj=wGround.findFirstObject(onFoundShape,onFoundSize,onFoundColor);
      if (onObj==null) return "I could not find a "+WorldObject.makeString(onFoundShape,onFoundSize,onFoundColor)+" to place the object on.";
      WorldObject newWO=new WorldObject(onObj, foundShape, foundSize, foundColor);
      repaint();
      return "Added "+newWO.getDesc()+" on the "+onObj.getDesc()+".";
    }
    catch (WorldObjectException e) {
      return e.getMessage();
    }
  }  if((m=Pattern.compile("^how many(.*)\\bon\\b(.*)",Pattern.CASE_INSENSITIVE).matcher(inString)).find()) {
    int onFoundColor=WorldObject.getColor(m.group(2));
    int onFoundSize=WorldObject.getSize(m.group(2));
    int onFoundShape=WorldObject.getShape(m.group(2));
    WorldObject onObj=wGround.findFirstObject(onFoundShape,onFoundSize,onFoundColor);
    if (onObj==null) return "I could not find a "+WorldObject.makeString(onFoundShape,onFoundSize,onFoundColor)+".";
    return onObj.findHowMany(m.group(1));
  }
  if((m=Pattern.compile("^move(.*)\\bto\\b(.*)",Pattern.CASE_INSENSITIVE).matcher(inString)).find()) {
    int foundColor=WorldObject.getColor(m.group(1));
    int foundSize=WorldObject.getSize(m.group(1));
    int foundShape=WorldObject.getShape(m.group(1));
    int onFoundColor=WorldObject.getColor(m.group(2));
    int onFoundSize=WorldObject.getSize(m.group(2));
    int onFoundShape=WorldObject.getShape(m.group(2));
    try {
      WorldObject onObj=wGround.findFirstObject(onFoundShape,onFoundSize,onFoundColor);
      if (onObj==null) return "I could not find a "+WorldObject.makeString(onFoundShape,onFoundSize,onFoundColor)+" to move the object to.";
      WorldObject movingWO=wGround.findFirstObject(foundShape, foundSize, foundColor);
      if (movingWO==null) return "I could not find a "+WorldObject.makeString(onFoundShape,onFoundSize,onFoundColor)+" to move the object to.";
      String s=movingWO.moveTo(onObj);
      repaint();
      return s;
    } catch (WorldObjectException e) {return e.getMessage(); }
  }
  if(Pattern.compile("^add",Pattern.CASE_INSENSITIVE).matcher(inString).find()) {
    int foundColor=WorldObject.getColor(inString);
    int foundSize=WorldObject.getSize(inString);
    int foundShape=WorldObject.getShape(inString);
    try {WorldObject newWO=new WorldObject(wGround, foundShape, foundSize, foundColor);
      repaint();
      return "Added "+newWO.getDesc()+".";
    }
    catch (WorldObjectException e) {
      return e.getMessage();
    }
  }
  if(Pattern.compile("^remove",Pattern.CASE_INSENSITIVE).matcher(inString).find()) {
    int foundColor=WorldObject.getColor(inString);
    int foundSize=WorldObject.getSize(inString);
    int foundShape=WorldObject.getShape(inString);
    try {String s=wGround.findAndRemove(foundShape, foundSize, foundColor);
      repaint();
      return s;
    }
    catch (WorldObjectException e) {
      return e.getMessage();
    }
  }
  if(Pattern.compile("^how many",Pattern.CASE_INSENSITIVE).matcher(inString).find()) {
    return wGround.findHowMany(inString);
  }
  if(Pattern.compile("^what supports",Pattern.CASE_INSENSITIVE).matcher(inString).find()) {
    int foundColor=WorldObject.getColor(inString);
    int foundSize=WorldObject.getSize(inString);
    int foundShape=WorldObject.getShape(inString);
    WorldObject onObj=wGround.findFirstObject(foundShape,foundSize,foundColor);
    if (onObj==null) return "I could not find a "+WorldObject.makeString(foundShape,foundSize,foundColor)+".";
    return onObj.displayBase();
  }
  return "You said "+inString;
  }
}