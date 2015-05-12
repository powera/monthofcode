import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class WordFindBoard extends JPanel 
  implements MouseListener, MouseMotionListener {

  private WFWord[] wordlist;
  private final int numWords=24;
  
  private WordListPanel wlPanel;
  public void setWLPanel(WordListPanel p) {wlPanel=p;}

  private char[][] board;
  private final int boardSize=15;

  public WordFindBoard() { /* Constructor */
    initBoard();
    addMouseMotionListener(this);
    addMouseListener(this);
  }
  
  public WFWord[] getWordList() {return wordlist;}
  
  private void initBoard() {
    wordlist=WordList.getWords(numWords);
    board=new char[boardSize][boardSize];
    for(int i=0;i<numWords;i++) {
       addWord(wordlist[i]);
    }
    for(int i=0;i<boardSize;i++) {
      for(int j=0;j<boardSize;j++) {
        if(board[i][j]==0) {
	  board[i][j]=(char)('A'+Math.random()*25);
	}
      }
    }
  }

  private void addWord(WFWord myWord) {
    /* Our algorithm to add words is as follows:
       1) Pick a direction, 1 to 8
       2) Give each space a value - -1 if off board/wrong letters, rand(2) if empty, 
          2+rand(3) for each intersection
       3) Pick the highest square
       4) Update the board with the new word
       5) If all squares are -1, we try the next direction up to 7 times
       6) If the word CANNOT fit, remove it from the wordlist
    */
    String word=myWord.getWord();
    int wLen=word.length();
    int dir=(int)(Math.random()*8)+1;
    int bestI=-1; int bestJ=-1; double bestScore=-1;
    for(int t=0;t<7;t++) {
      for(int i=0;i<boardSize;i++) {
        for(int j=0;j<boardSize;j++) {
          double score=testWord(word, dir, i, j);
          if(score>bestScore) {bestI=i; bestJ=j; bestScore=score;}
        }
      }
      if(bestScore==-1) {
        dir++;dir=(dir-1)%8+1;
        continue;
      }
      else {
        for(int n=0;n<wLen;n++) {
          board[bestI+n*getDX(dir)][bestJ+n*getDY(dir)]=word.charAt(n);
	  myWord.placeWord(bestI, bestJ, dir);
        }
        return;
      }
    }
  }

  private int getDX(int dir) {
    if(dir==3 || dir==7) return 0;
    if(dir==4 || dir==5 || dir==6) return -1;
    return 1;
  }

  private int getDY(int dir) {
    if(dir==2 || dir==3 || dir==4) return -1;
    if(dir==1 || dir==5) return 0;
    return 1;
  }

  private double testWord(String word, int dir, int x, int y) {
    /* Give the score for placing the beginning of the word at this space */
    double score=0;
    if(x+word.length()*getDX(dir)<0 || x+word.length()*getDX(dir)>=boardSize) return -1;
    if(y+word.length()*getDY(dir)<0 || y+word.length()*getDY(dir)>=boardSize) return -1;
    for(int n=0;n<word.length();n++) {
      if(board[x+n*getDX(dir)][y+n*getDY(dir)]==0) continue;
      if(board[x+n*getDX(dir)][y+n*getDY(dir)]!=word.charAt(n)) return -1;
      score+=2+3*Math.random();
    }
    if(score==0) score=2*Math.random();
    return score;
  }

  public Dimension getMinimumSize() {
    return new Dimension(350,350);
  }
  public Dimension getPreferredSize() {
    return new Dimension(350,350);
  }
  public Dimension getMaximumSize() {
    return new Dimension(350,350);
  }

  public void newGame() {
    initBoard();
    wlPanel.updatePanel(wordlist);
  }
  
  public void showSol() {
    for(int i=0;i<numWords;i++) 
      wordlist[i].markAsFound();
    wlPanel.updatePanel(wordlist);
  }
  
  private void drawCircle(Graphics g, WFWord w) {
    int x=w.getStartX();
    int y=w.getStartY();
    int dir=w.getDir();
    int len=w.getLen();
    drawCircle(g,x,y,dir,len);
  }
  
  private void drawCircle(Graphics g, int x, int y, int dir, int len) {
    g.setColor(Color.ORANGE);
    int xDiff=getDX(dir);
    int yDiff=getDY(dir);
    for(int i=0;i<len;i++) {
      g.drawOval(4+22*(x+i*xDiff),8+22*(y+i*yDiff),22,22);
      g.drawOval(5+22*(x+i*xDiff),9+22*(y+i*yDiff),20,20);
    }
    g.drawLine(15+22*x,19+22*y,15+22*(x+(len-1)*xDiff),19+22*(y+(len-1)*yDiff));
    g.setColor(Color.BLACK);
  }

  public void paintComponent(Graphics g) { /* Paints */
    super.paintComponent(g);
    g.fillRect(1,1,349,4);
    g.fillRect(1,1,4,349);
    g.fillRect(1,345,349,4);
    g.fillRect(345,1,4,349);
    g.setFont(new Font("Monospaced",Font.BOLD,18));
    for(int i=0;i<boardSize;i++) {
      for(int j=0;j<boardSize;j++) {
        g.drawString(Character.toString(board[i][j]), 10+22*i, 25+22*j);
	for(int k=0;k<numWords;k++) if(wordlist[k].isFound()) drawCircle(g,wordlist[k]);
      }
    }
    if(dragging) drawCircle(g,dragStartX,dragStartY,dragDir,dragLen);
  }
  
  /* MouseListener */
  private int dragStartX; private int dragStartY;
  private int dragDir; private int dragLen;
  private boolean dragging;
  public void mousePressed(MouseEvent e) { /* Start drag */
    dragStartX=(int)((e.getX()-4)/22);
    dragStartY=(int)((e.getY()-8)/22);
    dragging=true;dragDir=1;dragLen=1;
  }
  public void mouseReleased(MouseEvent e) { /* End drag */
    dragging=false;
    for(int i=0;i<numWords;i++) {
      if(wordlist[i].matches(dragStartX, dragStartY, dragDir, dragLen)) 
        wordlist[i].markAsFound();
    }
    wlPanel.updatePanel(wordlist);
    repaint();
  }
  
  public void mouseClicked(MouseEvent e) {}
  public void mouseEntered(MouseEvent e) {}
  public void mouseExited(MouseEvent e) {}
  
  /* MouseMotionListener */
  public void mouseDragged(MouseEvent e) { /* Move around */
    int curX=(int)((e.getX()-4)/22);
    int curY=(int)((e.getY()-8)/22);
    int dX=curX-dragStartX;
    int dY=curY-dragStartY;
    if(dX*dY!=0 && dX*dX!=dY*dY) {dragDir=1;dragLen=1;repaint();return;}
    if(dX>0 && dY==0) {dragDir=1;dragLen=dX+1;}
    if(dX>0 && dY<0) {dragDir=2;dragLen=dX+1;}
    if(dX==0 && dY<0) {dragDir=3;dragLen=-dY+1;}
    if(dX<0 && dY<0) {dragDir=4;dragLen=-dX+1;}
    if(dX<0 && dY==0) {dragDir=5;dragLen=-dX+1;}
    if(dX<0 && dY>0) {dragDir=6;dragLen=-dX+1;}
    if(dX==0 && dY>0) {dragDir=7;dragLen=dY+1;}
    if(dX>0 && dY>0) {dragDir=8;dragLen=dX+1;}
    repaint();
  }
  public void mouseMoved(MouseEvent e) {}

}