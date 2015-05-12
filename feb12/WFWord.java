public class WFWord {

  private String word;
  private int startX;
  private int startY;
  private int dir=0;
  private int len;
  private boolean found=false;
  public WFWord(String s) {
    word=s;
    len=word.length();
  }
  
  public void placeWord(int sX, int sY, int d) {
    startX=sX; startY=sY; dir=d; 
  }
  
  public boolean matches(int X, int Y, int d, int l) {
    return (X==startX && Y==startY && d==dir && l==len);
  }
  
  public String getWord() { return word; }
  
  public void markAsFound() { found=true; }
  
  public int getStartX() { return startX; }
  public int getStartY() { return startY; }
  public int getDir() { return dir; }
  public int getLen() { return len; }
  public boolean isFound() { return found; }
}