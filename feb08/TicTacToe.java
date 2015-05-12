import java.applet.*;
import java.awt.*;
import java.awt.event.*;
import java.text.*;

public class TicTacToe extends Applet
  implements MouseListener {

  private Board gameBoard;

  public void init() { /* Initializes the Applet */
    gameBoard=new Board();
    this.addMouseListener(this);
  }

  private void resetGame() {
    gameBoard.resetBoard();
    showStatus("Good Luck!");
  }

  private void drawBoard(Graphics g) {
    for(int i=0;i<4;i++) {
      for(int j=-1;j<4;j++) {
        g.drawLine(45+25*j,5+110*i,65+25*j,105+110*i); /* Ver */
        g.drawLine(25+5*j,110*i+30+25*j,125+5*j,110*i+30+25*j); /* Horiz */
      }
    }
    g.drawRoundRect(30,450,60,30,2,2);
    g.drawString("Reset",40,470);
    g.drawRoundRect(30,490,60,30,2,2);
    if(gameBoard.aiPlaysO) {g.drawString("Human O",35,510);}
    else {g.drawString("AI O",40,510);}
    if(!gameBoard.isOver()) {
      g.drawString("Next Move",100,460);
      if(gameBoard.getMove()==Board.PLAYER_X) {drawX(120,460,g);}
      if(gameBoard.getMove()==Board.PLAYER_O) {drawO(120,460,g);}
      g.drawString("Score: "+gameBoard.score(),100,500);
      g.drawString("O is "+(gameBoard.aiPlaysO?"AI":"Human"),100,515);
    }
    else {
      if(gameBoard.winner()==gameBoard.PLAYER_X) g.drawString("X wins!",100,460);
      if(gameBoard.winner()==gameBoard.PLAYER_O) g.drawString("O wins!",100,460);
      g.setColor(new Color(215,90,90));
      for(int i=0;i<4;i++) {
         int squareNum=gameBoard.getWinSquare(i);
         int sI=squareNum/16;int sJ=(squareNum/4)%4;int sK=squareNum%4;
         int tX=getSquareX(sI,sJ,sK); int tY=getSquareY(sI,sJ,sK);
         int[] xCoords={tX+1,tX+24,tX+29,tX+6};
         int[] yCoords={tY+1,tY+1,tY+25,tY+25};
         g.fillPolygon(xCoords, yCoords,4);
      }
      g.setColor(Color.BLACK);
    }
  }

  private void drawO(int i, int j, int k, Graphics g) {
    /* i is board number, j is row number, k is column */
    int x,y;
    y=getSquareY(i,j,k);
    x=getSquareX(i,j,k)+5;
    drawO(x,y,g);
  }

  private void drawO(int x, int y, Graphics g) {
    g.drawOval(x+3,y+4,14,14);
    g.drawOval(x+4,y+5,12,12);
    g.drawOval(x+5,y+6,10,10);
  }

  private void drawX(int i, int j, int k, Graphics g) {
    /* i is board number, j is row number, k is column */
    int x,y;
    y=getSquareY(i,j,k)+3;
    x=getSquareX(i,j,k)+5;
    drawX(x,y,g);
  }

  private void drawX(int x, int y, Graphics g) {
    g.drawLine(x+3,y+4,x+15,y+16);
    g.drawLine(x+4,y+4,x+16,y+16);
    g.drawLine(x+4,y+3,x+16,y+15);
    g.drawLine(x+15,y+3,x+3,y+15);
    g.drawLine(x+16,y+3,x+3,y+16);
    g.drawLine(x+16,y+4,x+4,y+16);
  }

  private int getSquareX(int i, int j, int k) {
    return 5*j+25*k+20; }
  private int getSquareY(int i, int j, int k) {
    return 110*i+25*j+5; }

  private int getCoords(int x, int y) {
    int i=y/110;
    int j=(y%110-5)/25;
    int k=(x-20-(y%110-5)/5)/25;
    if(i<0 || j<0 || k<0 || i>3 || j>3 || k>3) return -1;
    return 16*i+4*j+k;
  }

  public void stop() { /* Terminates the Applet */

  }

  /* MouseListener Functions */
  public void mouseEntered(MouseEvent e) { }
  public void mouseReleased(MouseEvent e) { }
  public void mouseExited(MouseEvent e) { }
  public void mousePressed(MouseEvent e) { }

  public void mouseClicked(MouseEvent e) {
    int coords=getCoords(e.getX(), e.getY());
    if(coords!=-1 && !gameBoard.isOver()) { 
      /* Yes, it's an error code, not an exception,
      but I feel like doing it this way for simplicity */
    int i=coords/16;
    int j=(coords%16)/4;
    int k=coords%4;
    gameBoard.move(i,j,k);
    repaint();
    }
    else { /* Not on the board */
      if(e.getX()>30 && e.getX()<90 && e.getY()<480 && e.getY()>450) {
        resetGame();
        repaint();
      }
      if(e.getX()>30 && e.getX()<90 && e.getY()<520 && e.getY()>490) {
        gameBoard.aiPlaysO=!gameBoard.aiPlaysO;
        repaint();
      }
    }
  } 

  public void paint(Graphics g) { /* Paints the screen */
    drawBoard(g);
    for(int i=0;i<4;i++) {
      for(int j=0;j<4;j++) {
        for(int k=0;k<4;k++) {
          int t=gameBoard.getStatusAt(i,j,k);
          if(t==gameBoard.PLAYER_X) drawX(i,j,k,g);
          if(t==gameBoard.PLAYER_O) drawO(i,j,k,g);
        }}}
  }

}