import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.text.*;

public class WordFind extends JApplet 
       implements ActionListener {

  private JButton newGameButton;
  private JButton showSolButton;
  private WordFindBoard wfBoard;
  private WordListPanel wlPanel;

  public void init() { /* Initializes */
    SpringLayout s=new SpringLayout();
    this.getContentPane().setLayout(s);

    /* The Board */
    wfBoard=new WordFindBoard();
    this.getContentPane().add(wfBoard);
    s.putConstraint(SpringLayout.WEST, wfBoard, 5, SpringLayout.WEST, this.getContentPane());
    s.putConstraint(SpringLayout.NORTH, wfBoard, 5, SpringLayout.NORTH, this.getContentPane());

    /* Left Side Buttons */
    newGameButton=new JButton("New Game");
    newGameButton.addActionListener(this);
    this.getContentPane().add(newGameButton);
    s.putConstraint(SpringLayout.WEST, newGameButton, 5, SpringLayout.EAST, wfBoard);
    s.putConstraint(SpringLayout.NORTH, newGameButton, 5, SpringLayout.NORTH, this.getContentPane());

    showSolButton=new JButton("Show Solution");
    showSolButton.addActionListener(this);
    this.getContentPane().add(showSolButton);
    s.putConstraint(SpringLayout.WEST, showSolButton, 5, SpringLayout.EAST, wfBoard);
    s.putConstraint(SpringLayout.NORTH, showSolButton, 5, SpringLayout.SOUTH, newGameButton);
    
    wlPanel=new WordListPanel(wfBoard.getWordList());
    this.getContentPane().add(wlPanel);
    s.putConstraint(SpringLayout.WEST, wlPanel, 5, SpringLayout.EAST, wfBoard);
    s.putConstraint(SpringLayout.NORTH, wlPanel, 5, SpringLayout.SOUTH, showSolButton);
    
    wfBoard.setWLPanel(wlPanel);

  }

  public void stop() { /* Terminates */
  }

  public void actionPerformed(ActionEvent evt) {
    Object S=evt.getSource();
    if(S==showSolButton) {wfBoard.showSol();repaint();}
    if(S==newGameButton) {wfBoard.newGame();repaint();}
  }

}