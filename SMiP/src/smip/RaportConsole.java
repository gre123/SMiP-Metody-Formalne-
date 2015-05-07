
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
    public void printMatrix(int [][] matrix)
    {
        String printLine = raportArea.getText()+"\n"+date.getHours()+":"+date.getMinutes()+">\n";
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(printLine);
        for(int i=0;i<matrix.length;i++)
        {
            for(int j=0;j<matrix[i].length;j++)
            {
                stringBuffer.append(matrix[i][j]+" ");
            }
            stringBuffer.append("\n");
        }
        stringBuffer.append("\n");
        raportArea.setText(stringBuffer.toString());
        SMiP.viewer.repaint();
    }
}
