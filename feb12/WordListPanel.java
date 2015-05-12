import javax.swing.*;
import java.awt.*;

public class WordListPanel extends JPanel {

  public Dimension getMinimumSize() {
    return new Dimension(120,450);
  }
  public Dimension getPreferredSize() {
    return new Dimension(120,450);
  }
  public Dimension getMaximumSize() {
    return new Dimension(120,450);
  }
  public WordListPanel(WFWord[] w) {
    updatePanel(w);
  }
  public void updatePanel(WFWord[] w) {
    removeAll();
    setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
    for(int i=0;i<w.length;i++)
    if(!w[i].isFound() && w[i].getDir()!=0) add(new JLabel(w[i].getWord()));
    validate();
    repaint();
  }
  
}