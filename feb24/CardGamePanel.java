import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.MemoryImageSource;
import java.util.*;

public class CardGamePanel extends JPanel 
  implements ActionListener, MouseListener, MouseMotionListener {

  private javax.swing.Timer gameTimer;
  int tickCount;

  private Font headlineFont;
  private Font subheadFont;
  private Font plaintextFont;

  private CardDeck myDeck;
  private CardHand myHand;
  
  public CardGamePanel() {
    myDeck=new CardDeck();
    myHand=new CardHand(myDeck,5);
    this.addMouseListener(this);
    this.addMouseMotionListener(this);
    /* initCursor(); */
    initFonts();
    /* initTimer(); */
  }
  
  private void initCursor() {
    int[] pix={0};
    Toolkit t=Toolkit.getDefaultToolkit();
    Cursor blankPixel=t.createCustomCursor(createImage(new MemoryImageSource(1,1,pix,0,1)),new Point(0,0),"Hidden");
    setCursor(blankPixel);
  }
  
  private void initFonts() {
    headlineFont=new Font("Monospaced",Font.BOLD,24);
    subheadFont=new Font("Monospaced",Font.PLAIN,18);
    plaintextFont=new Font("Dialog",Font.PLAIN,14);
  }
  
  private void initTimer() {
    int delay=500; /* Milliseconds */
    gameTimer=new javax.swing.Timer(delay,this);
    gameTimer.start();
  }
  
  public Dimension getPreferredSize() {return new Dimension(500,500);}
  public Dimension getMinimumSize() {return new Dimension(500,500);}
  public Dimension getMaximumSize() {return new Dimension(500,500);}
  
  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    g.setColor(Color.BLACK);
    g.fillRect(0,0,500,500);
    
    drawStringCentered(g,headlineFont,Color.WHITE,"Card Game",30,500);
    drawStringCentered(g,subheadFont,Color.WHITE,"by Alex Power",60,500);
    
    myHand.paintHand(g,100,100);
    drawStringCentered(g,plaintextFont, Color.WHITE, myHand.getPokerValue(),160,500);
    drawStringLeft(g,plaintextFont,Color.WHITE,"High Score: 8000",465,500);
    drawStringRight(g,plaintextFont,Color.WHITE,"Level: 9",465,500);
    g.setColor(Color.BLUE);
    g.fillRect(200,200,100,50);
    g.fillRect(200,300,100,50);
    drawStringCentered(g,plaintextFont,Color.WHITE, "Replace Cards", 212,500);
    drawStringCentered(g,plaintextFont,Color.WHITE, "New Hand", 312,500);
  }
  
  private void drawStringCentered(Graphics g, Font textFont, Color textColor, String text, int y, int xSize) {
    g.setColor(textColor);
    g.setFont(textFont);
    int width=g.getFontMetrics().stringWidth(text);
    int height=g.getFontMetrics().getHeight();
    int left=(xSize-width)/2;
    int bottom=y+height;
    g.drawString(text,left,bottom);
  }
  
  private void drawStringLeft(Graphics g, Font textFont, Color textColor, String text, int y, int xSize) {
    g.setColor(textColor);
    g.setFont(textFont);
    int width=g.getFontMetrics().stringWidth(text);
    int height=g.getFontMetrics().getHeight();
    int left=5;
    int bottom=y+height;
    g.drawString(text,left,bottom);
  }
    
  private void drawStringRight(Graphics g, Font textFont, Color textColor, String text, int y, int xSize) {
    g.setColor(textColor);
    g.setFont(textFont);
    int width=g.getFontMetrics().stringWidth(text);
    int height=g.getFontMetrics().getHeight();
    int left=xSize-width-5;
    int bottom=y+height;
    g.drawString(text,left,bottom);
  }
  

  
  /* ActionListener for clock or single-step mode */
  public void actionPerformed(ActionEvent evt) {
    tickCount++;
    
    repaint();
  }
  

  /* MouseListener */
  public void mouseClicked(MouseEvent e) {
    if(e.getButton()==MouseEvent.BUTTON1) {
      if(e.getX()>95 && e.getX()<380 && e.getY()>95 && e.getY()<165) myHand.changeStatus((e.getX()-95)/55);
      else if (e.getX()>200 && e.getX()<300 && e.getY()>200 && e.getY()<250) myHand.reDraw();
      else if (e.getX()>200 && e.getX()<300 && e.getY()>300 && e.getY()<350) {
      myDeck=new CardDeck();
      myHand=new CardHand(myDeck,5); }
    } /* Left Click */
    else {} /* Other Click */
    repaint();
  }
  
  public void mousePressed(MouseEvent e) { }
  public void mouseReleased(MouseEvent e) { }
  public void mouseEntered(MouseEvent e) { }
  public void mouseExited(MouseEvent e) { }
  
  /* MouseMotionListener */
  public void mouseDragged(MouseEvent e) { }
  public void mouseMoved(MouseEvent e) { /* Move the ship */
    repaint();
  }
  
}
