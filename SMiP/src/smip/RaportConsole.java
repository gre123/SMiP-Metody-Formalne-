
package smip;

import java.util.Date;
import javax.swing.JTextArea;

public class RaportConsole {
    
    private JTextArea raportArea;
    private Date date;
    
    public RaportConsole(JTextArea raportArea)
    {
        this.raportArea = raportArea;
        this.date = new Date();
    }
    public void printLine(String line)
    {
        String printLine = raportArea.getText()+"\n"+date.getHours()+":"+date.getMinutes()+">"+line;
        raportArea.setText(printLine);
        SMiP.viewer.repaint();
    }
}
