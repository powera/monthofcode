import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.MemoryImageSource;
import java.util.*;

public class RacingWindow extends JPanel 
  implements ActionListener, MouseListener, MouseMotionListener {

  private javax.swing.Timer gameTimer;
  int tickCount;

  private Font headlineFont;
  private Font subheadFont;
  private Font plaintextFont;
  
  private RacingTrack mainCourse;
  
  public RacingWindow() {
    mainCourse=new RacingTrack();
    this.addMouseListener(this);
    this.addMouseMotionListener(this);
    /* initCursor(); */
    initFonts();
    initTimer();
  }
  
  private void initCursor() {
    int[] pix={0};
    Toolkit t=Toolkit.getDefaultToolkit();
    Cursor blankPixel=t.createCustomCursor(createImage(new MemoryImageSource(1,1,pix,0,1)),new Point(0,0),"Hidden");
    setCursor(blankPixel);
  }
  
  private void initFonts() {
    headlineFont=new Font("Dialog",Font.BOLD,24);
    subheadFont=new Font("Dialog",Font.PLAIN,18);
    plaintextFont=new Font("Dialog",Font.PLAIN,14);
  }
  
  private void initTimer() {
    int delay=50; /* Milliseconds */
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
    
    drawStringCentered(g,headlineFont,Color.WHITE,"Java Town",5,500);
    drawStringCentered(g,subheadFont,Color.WHITE,"by Alex Power",32,500);
    
    drawStringLeft(g,plaintextFont,Color.WHITE,"Right Mouse Click to Pause",465,500);
    drawStringRight(g,plaintextFont,Color.WHITE,"Sit Back and Watch",465,500);
    
    mainCourse.drawTrack(g,60,440,60,440);
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
    mainCourse.tick();
    repaint();
  }
  

  /* MouseListener */
  public void mouseClicked(MouseEvent e) {
    if(e.getButton()==MouseEvent.BUTTON1) {} /* Left Click */
    else { if (gameTimer.isRunning()) gameTimer.stop(); else gameTimer.start();} /* Other Click */
    repaint();
  }
  
  public void mousePressed(MouseEvent e) { }
  public void mouseReleased(MouseEvent e) { }
  public void mouseEntered(MouseEvent e) { }
  public void mouseExited(MouseEvent e) { }
  
  /* MouseMotionListener */
  public void mouseDragged(MouseEvent e) { }
  public void mouseMoved(MouseEvent e) { 
    /* mainCourse.setCoords(e.getX(),e.getY());
    repaint(); */
  }
  
}