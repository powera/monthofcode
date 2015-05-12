import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.regex.*;

public class WorldObject {
  /* The Shapes */
  public static final String[] regexShape={"ground|table|floor", "cube|block|prism|square", "pyramid", "sphere|ball", "cylinder", "cone"};
  public static final String[] shapeName={"ground", "cube", "pyramid", "sphere", "cylinder", "cone"};
  private static final int[] onTopArray={-1,1,0,0,1,0};
  public static final int NUM_SHAPES=6;
  public static final int GROUND=0;
  public static final int CUBE=1;
  public static final int PYRAMID=2;
  public static final int SPHERE=3;
  public static final int CYLINDER=4;
  public static final int CONE=5;
  public static final int ANY_SHAPE=-1;
  
  public static final int getShape(String s) {
    for(int i=0;i<NUM_SHAPES;i++) 
      if(Pattern.compile(regexShape[i],Pattern.CASE_INSENSITIVE).matcher(s).find()) {return i;}
    return -1;
  }
  
  
  /* Sizes */
  public static final String[] regexSize={"tiny|very small", "small", "medium|average", "very large|huge|gigantic|very big", "large|big"};
  public static final String[] sizeName={"very small", "small", "medium", "very large", "large", "enormous"};
  public static final int[] sizeHeight={10,25,40,60,90,250};
  public static final int NUM_SIZES=5;
  public static final int VERY_SMALL=0;
  public static final int SMALL=1;
  public static final int MEDIUM=2;
  public static final int VERY_LARGE=3;
  public static final int LARGE=4;
  public static final int BASE_SIZE=5;
  public static final int ANY_SIZE=-1;
  
  public static final int getSize(String s) {
    for(int i=0;i<NUM_SIZES;i++) 
      if(Pattern.compile(regexSize[i],Pattern.CASE_INSENSITIVE).matcher(s).find()) {return i;}
    return -1;
  }
  
  /* Colors */
  public static final String[] regexColor={"red", "blue", "green", "orange", "yellow", "purple|magenta", "black", "gr[ae]y", "white"};
  public static final String[] colorName={"red", "blue", "green", "orange", "yellow", "purple", "black", "gray", "white"};
  public static final Color[] colorList={Color.RED, Color.BLUE, Color.GREEN, Color.ORANGE, Color.YELLOW, Color.MAGENTA,Color.BLACK, Color.GRAY, Color.WHITE};
  public static final int NUM_COLORS=9;
  public static final int RED=0;
  public static final int BLUE=1;
  public static final int GREEN=2;
  public static final int ORANGE=3;
  public static final int YELLOW=4;
  public static final int PURPLE=5;
  public static final int BLACK=6;
  public static final int GRAY=7;
  public static final int WHITE=8;
  public static final int ANY_COLOR=-1;
  
  public static final int getColor(String s) {
    for(int i=0;i<NUM_COLORS;i++) 
      if(Pattern.compile(regexColor[i],Pattern.CASE_INSENSITIVE).matcher(s).find()) {return i;}
    return -1;
  }
      
  public static String makeString(int shape, int size, int color)  {
    return (size==ANY_SIZE?"":sizeName[size]+" ")+(color==ANY_COLOR?"":colorName[color]+" ")+(shape==ANY_SHAPE?"object":shapeName[shape]);
  }

  private WorldObject base=null; /* What does it rest on? */
  private WorldObject prevOnBase=null; /* LL for level */
  private WorldObject nextOnBase=null;
  private WorldObject firstOnTop=null; /* LL for children */
  private WorldObject lastOnTop=null;
  private int numOnTop=0; /* How many things are on it */
  private int widthOnTop=0; /* Width of stuff on it */
  
  private int shapeNum;
  private int sizeNum;
  private int colorNum;
  
  public WorldObject() { /* Ground only */
    shapeNum=GROUND;
    sizeNum=BASE_SIZE;
    colorNum=BLACK;
  }
  
  public WorldObject(WorldObject b, int oType, int size, int color) throws WorldObjectException {
    if(oType==GROUND) throw new WorldObjectException("You cannot create a new ground.");
    shapeNum=oType; if(shapeNum==ANY_SHAPE) shapeNum=CUBE;
    sizeNum=size; if(sizeNum==ANY_SIZE) sizeNum=MEDIUM;
    colorNum=color; if(colorNum==ANY_COLOR) colorNum=RED;
    b.addChild(this);
  }
  
  public void draw(Graphics g, int xMin, int yMin, int xMax, int yMax) {
    int height=sizeHeight[sizeNum];
    if(shapeNum==GROUND) {
      height=40;
      g.setColor(colorList[colorNum]);
      g.fillRect(xMin,yMax-height,xMax-xMin,height);
    }
    if(shapeNum==CUBE) {
      g.setColor(colorList[colorNum]);
      g.fillRect(xMin,yMax-height,height,height);
    }
    if(shapeNum==PYRAMID) {
      g.setColor(colorList[colorNum]);
      int[] xCoords={xMin,xMax,(xMin+xMax)/2};
      int[] yCoords={yMax,yMax,yMax-height};
      g.fillPolygon(xCoords,yCoords,3);
    }
    if(shapeNum==SPHERE) {
      g.setColor(colorList[colorNum]);
      g.fillOval(xMin,yMax-height,height,height);
    }
    if(shapeNum==CYLINDER) {
      g.setColor(colorList[colorNum]);
      g.fillRect(xMin,yMax-height,height,height);
    }
    if(shapeNum==CONE) {
      g.setColor(colorList[colorNum]);
      int[] xCoords={xMin,xMax,(xMin+xMax)/2};
      int[] yCoords={yMax,yMax,yMax-height};
      g.fillPolygon(xCoords,yCoords,3);
    }
    if(numOnTop!=0) {
      WorldObject iterWO=firstOnTop;int i=0;
      int freeWidth=xMax-xMin-widthOnTop;int curX=xMin+freeWidth/(numOnTop*2);
      while(iterWO!=null) {
        iterWO.draw(g,curX,yMin,curX+iterWO.getWidth(),yMax-height);
	i++; curX+=iterWO.getWidth()+freeWidth/numOnTop; iterWO=iterWO.nextOnBase;
      }
    }
  }
  
  public String getDesc() {
    if(shapeNum==GROUND) {return "ground";}
    return sizeName[sizeNum]+" "+colorName[colorNum]+" "+shapeName[shapeNum];
  }
  
  public String addChild(WorldObject c) throws WorldObjectException{
    if(onTopArray[shapeNum]==0) throw new WorldObjectException("You can't put anything on top of the "+getDesc()+".");
    if(sizeNum<c.sizeNum) throw new WorldObjectException("You can't put a bigger object on top of a smaller one.");
    if(widthOnTop+c.getWidth()>getWidth()) throw new WorldObjectException("There is no room for the "+c.getDesc()+" on the "+getDesc()+".");
    /* Update the linked lists */
    c.addAtEnd(this);
    return "The "+c.getDesc()+" has been placed on the "+getDesc()+".";
  }
  
  public String findAndRemove(int shape, int size, int color) throws WorldObjectException {
    int i=countHowMany(shape,size,color);
    if (i==0) throw new WorldObjectException("There aren't any "+makeString(shape,size,color)+"s.");
    if (i>1) throw new WorldObjectException("There are "+i+" "+makeString(shape,size,color)+"s.  I don't know which one you are referring to.");
    WorldObject remover=findFirstObject(shape,size,color);
    if(remover.shapeNum==GROUND) throw new WorldObjectException("You cannot remove the ground.");
    if(remover.numOnTop!=0) throw new WorldObjectException("The "+remover.getDesc()+" has objects on it.");
    WorldObject removedFrom=remover.base;
    remover.removeFromBase();
    return "The "+remover.getDesc()+" was removed from on the "+removedFrom.getDesc()+".";
  }
  
  public String moveTo(WorldObject targetWO) throws WorldObjectException {
    if (this.base==null) throw new WorldObjectException("You cannot move the ground!");
    if (this==targetWO) throw new WorldObjectException("You cannot move an object onto itself!");
    WorldObject iterWO=targetWO.base;
    while(iterWO!=null) {if(this==iterWO) throw new WorldObjectException("You cannot move an object onto something on top of it!"); iterWO=iterWO.base; }
    if(targetWO.widthOnTop+this.getWidth()>targetWO.getWidth()) throw new WorldObjectException("There is no room on the "+targetWO.getDesc()+" for the "+this.getDesc()+".");
    this.removeFromBase();
    this.addAtEnd(targetWO);
    return "Moved "+getDesc()+" onto the "+targetWO.getDesc()+".";
  }
  
  public WorldObject findFirstObject(int shape, int size, int color) {
    if((shape==ANY_SHAPE || shape==shapeNum) && (size==ANY_SIZE || size==sizeNum) && (color==ANY_COLOR || color==colorNum)) {return this;}
    if(firstOnTop!=null) {
      WorldObject iterWO=firstOnTop;
      while (iterWO!=null) {
        WorldObject findWO=iterWO.findFirstObject(shape,size,color);
        if(findWO!=null) return findWO;
	iterWO=iterWO.nextOnBase;
      }
    }
    return null;
  }
  
  public String findHowMany(String s) {
    int foundColor=WorldObject.getColor(s);
    int foundSize=WorldObject.getSize(s);
    int foundShape=WorldObject.getShape(s);
    return findHowMany(foundShape, foundSize, foundColor);
  }
  
  public String displayBase() {
    if (base==null) {return "The ground is not supported by any other object.";}
    String s="The "+this.getDesc();
    WorldObject iterWO=this.base;
    while(iterWO.base!=null) {s=s+" is supported by a "+iterWO.getDesc()+", which";iterWO=iterWO.base;}
    s=s+" is supported by the ground.";
    return s;
  }
  
  private String findHowMany(int shape,int size, int color) {
    int i=countHowManyChildren(shape,size,color);
    String s="There "+(i==1?"is ":"are ")+i+" "+makeString(shape,size,color)+(i==1?"":"s")+(this.base==null?"":" on the "+getDesc())+".";
    return s;
  }
  
  private int countHowManyChildren(int shape, int size, int color) {
    int i=0;
    if(firstOnTop!=null) {
      WorldObject iterWO=firstOnTop;
      while (iterWO!=null) {
        i+=iterWO.countHowMany(shape,size,color);
	iterWO=iterWO.nextOnBase;
      }
    }
    return i;
  }
  
  private int countHowMany(int shape, int size, int color) {
    int i=0;
    if((shape==ANY_SHAPE || shape==shapeNum) && (size==ANY_SIZE || size==sizeNum) && (color==ANY_COLOR || color==colorNum)) {i++;}
    i+=countHowManyChildren(shape,size,color);
    return i;
  }
  
  private void removeFromBase() {
    if(base==null) return;
    if(this==base.firstOnTop) base.firstOnTop=this.nextOnBase;
    if(this==base.lastOnTop) base.lastOnTop=this.prevOnBase;
    if(this.prevOnBase!=null) this.prevOnBase.nextOnBase=this.nextOnBase;
    if(this.nextOnBase!=null) this.nextOnBase.prevOnBase=this.prevOnBase;
    base.numOnTop--;
    base.widthOnTop-=getWidth();
    this.base=null; this.prevOnBase=null; this.nextOnBase=null;
  }
  
  private void addAtEnd(WorldObject newBase) {
    WorldObject oldLast=newBase.lastOnTop;
    if(oldLast!=null) oldLast.nextOnBase=this;
    this.prevOnBase=oldLast;
    newBase.lastOnTop=this;
    if(oldLast==null) newBase.firstOnTop=this;
    this.base=newBase;
    newBase.numOnTop++;
    newBase.widthOnTop+=getWidth();
  }
  
  private int getMaxHeight() {
    if(numOnTop==0) return sizeHeight[sizeNum];
    WorldObject curWO=firstOnTop; int maxH=0;
    while(curWO!=null) {
      if (curWO.getMaxHeight()>maxH) maxH=curWO.getMaxHeight();
      curWO=curWO.nextOnBase;
    }
    return maxH+sizeHeight[sizeNum];
  }
  
  private int getWidth() {
    return sizeHeight[sizeNum];
  }


}