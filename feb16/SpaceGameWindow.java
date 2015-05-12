import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.MemoryImageSource;
import java.util.*;

public class SpaceGameWindow extends JPanel 
  implements ActionListener, MouseListener, MouseMotionListener {

  private boolean gameStarted=false;
  private int timeThisLevel=0;
  private int levelNum;
  private int numKills;
  private int heroTimesHit;
  private int heroScore;
  private javax.swing.Timer gameTimer;

  private Font headlineFont;
  private Font subheadFont;
  private Font plaintextFont;
  
  private PlayerShip heroShip; /* Only one hero ship at once */
  private Vector heroShots;
  private int heroShotsFired;
  private Vector enemyShips;
  private Vector enemyShots;
  
  public SpaceGameWindow() {
    heroShip=new PlayerShip(this);
    heroTimesHit=0;
    heroShots=new Vector();
    heroShotsFired=0;
    enemyShips=new Vector();
    numKills=0;
    enemyShots=new Vector();
    
    levelNum=1;
    createEnemies(levelNum);
    this.addMouseListener(this);
    this.addMouseMotionListener(this);
    initCursor();
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
    headlineFont=new Font("Monospaced",Font.BOLD,24);
    subheadFont=new Font("Monospaced",Font.PLAIN,18);
    plaintextFont=new Font("Dialog",Font.PLAIN,14);
  }
  
  private void initTimer() {
    int delay=25; /* Milliseconds */
    gameTimer=new javax.swing.Timer(delay,this);
    gameTimer.start();
  }
  
  public Dimension getPreferredSize() {return new Dimension(400,520);}
  public Dimension getMinimumSize() {return new Dimension(400,520);}
  public Dimension getMaximumSize() {return new Dimension(400,520);}
  
  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    g.setColor(Color.BLACK);
    g.fillRect(0,0,400,520);
    g.setColor(Color.RED);
    g.fillRect(5,440,390,5);
    heroShip.draw(g);
    for (ListIterator it=heroShots.listIterator();it.hasNext();) {
      PlayerMissile s=(PlayerMissile)it.next();
      s.draw(g); }
      
    /* Draw the enemy ships */
    for (ListIterator it=enemyShips.listIterator();it.hasNext();) {
      EnemyShip s=(EnemyShip)it.next();
      s.draw(g); }
      
    for (ListIterator it=enemyShots.listIterator();it.hasNext();) {
      EnemyMissile s=(EnemyMissile)it.next();
      s.draw(g); }
    
    drawStringCentered(g,headlineFont,Color.WHITE,"SPACE WARS 5959",30,400);
    drawStringCentered(g,subheadFont,Color.WHITE,"by Alex Power",60,400);
    
    drawStringLeft(g,plaintextFont,Color.WHITE,Integer.toString(numKills)+" Ships Destroyed",445,400);
    drawStringRight(g,plaintextFont,Color.WHITE,Integer.toString(heroShotsFired)+" Shots Fired",445,400);
    drawStringLeft(g,plaintextFont,Color.WHITE,"Level Time: "+Double.toString(timeThisLevel/40)+" seconds",465,400);
    drawStringRight(g,plaintextFont,Color.WHITE,"Level: "+Integer.toString(levelNum),465,400);
    drawStringLeft(g,plaintextFont,Color.WHITE,"Times Hit: "+Integer.toString(heroTimesHit),485,400);
    drawStringRight(g,plaintextFont,Color.WHITE,"Score: "+Integer.toString(heroScore),485,400);
    
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
  
  public void addPlayerMissile(PlayerMissile s) {
    heroShots.listIterator().add(s);
    heroShotsFired++;
    heroScore--;
  }
  
  public void addEnemyShip(EnemyShip s) {
    enemyShips.listIterator().add(s);
  }
  public void addEnemyMissile(EnemyMissile s) {
    enemyShots.listIterator().add(s);
  }
  
  private void createEnemies(int level) {
    for(int i=80;i<320;i+=20) for(int j=120;j<240;j+=20)
    addEnemyShip(new EnemyShip(this,i,j,level));
  }
  
  public void findCollisions() {
    mainloop: 
    for (ListIterator it=heroShots.listIterator();it.hasNext();) {
      PlayerMissile pM=(PlayerMissile)it.next(); 
      for (ListIterator jt=enemyShips.listIterator();jt.hasNext();) {
        EnemyShip eS=(EnemyShip)jt.next();
	if (eS.intersect(pM)) {it.remove();jt.remove();heroScore+=2*levelNum+3;numKills++;continue mainloop;}
      }
    }
    
    for (ListIterator it=enemyShots.listIterator();it.hasNext();) {
      EnemyMissile s=(EnemyMissile)it.next(); 
      if(heroShip.intersect(s)) {it.remove();heroScore-=10*levelNum;if(heroScore>0) heroScore-=heroScore/6;heroTimesHit++;}
    }
  }
  
  /* ActionListener for clock or single-step mode */
  public void actionPerformed(ActionEvent evt) {
    timeThisLevel++;
    /* Move all the hero's shots up */
    for (ListIterator it=heroShots.listIterator();it.hasNext();) {
      PlayerMissile s=(PlayerMissile)it.next(); if(!s.move()) {it.remove();} }
    
    /* Move the enemy ships */
    for (ListIterator it=enemyShips.listIterator();it.hasNext();) {
      EnemyShip s=(EnemyShip)it.next(); s.runTime(); }
    
    for (ListIterator it=enemyShots.listIterator();it.hasNext();) {
      EnemyMissile s=(EnemyMissile)it.next(); if(!s.move()) {it.remove();} }
    
    findCollisions();
    
    /* Decrease the hero's shooting timeout */
    heroShip.runTime();
    
    if(enemyShips.size()==0) {
      if(timeThisLevel<3200) heroScore+=15*levelNum*Math.sqrt(3200-timeThisLevel);
      timeThisLevel=0;
      levelNum++;
      createEnemies(levelNum);
    }
    
    repaint();
  }
  
  /* MouseListener */
  public void mouseClicked(MouseEvent e) { /* FIRE THE LASER */
    if(e.getButton()==MouseEvent.BUTTON1) heroShip.fire();
    else heroShip.autoFire(); 
    repaint();
  }
  
  public void mousePressed(MouseEvent e) { }
  public void mouseReleased(MouseEvent e) { }
  public void mouseEntered(MouseEvent e) { }
  public void mouseExited(MouseEvent e) { }
  
  /* MouseMotionListener */
  public void mouseDragged(MouseEvent e) { }
  public void mouseMoved(MouseEvent e) { /* Move the ship */
    heroShip.moveTo(e.getX(),e.getY());
    findCollisions();
    repaint();
  }
  
}