import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.text.*;

public class ChatWindow extends JPanel
  implements ActionListener {
  WorldViewer myWV;
  JScrollPane outputSP;
  JTextArea outputTA;
  JTextField inputTF;
  
  public ChatWindow(WorldViewer w) {
    myWV=w;
    outputTA=new JTextArea(6,36);
    outputTA.setLineWrap(true);
    outputTA.setWrapStyleWord(true);
    outputSP=new JScrollPane(outputTA);
    this.add(outputSP);
    inputTF=new JTextField(36);
    this.add(inputTF);
    inputTF.addActionListener(this);
  }
  
  public void actionPerformed(ActionEvent e) {
    String textTF=inputTF.getText();
    outputTA.append("\nUser: "+textTF+"\n");
    inputTF.setText("");
    String result=myWV.doAction(textTF);
    if(result!=null) outputTA.append("ULDRHS: "+result);
  }
}